package tek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class KpiProvisioner {

	private final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String DBURL = "jdbc:oracle:thin:@sh-ipi-main08:1521:iris";
	private final String DBUSER = "tpowner1";
	private final String DBPASSWORD = "tpowner1";

	private final int STAND_FOR_STRING = -8888;

	private int[] lastValues;

	private int getTime(Connection con) {
		String timeSql = "select TIME_ID from MINUTE_TIME_DIMENSION where TIMESTAMP=?";

		int currentTime = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(timeSql);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			pstmt.setTimestamp(1, new Timestamp(calendar.getTimeInMillis()));
			rs = pstmt.executeQuery();
			rs.next();
			currentTime = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return currentTime;
	}

	private String getInsertSql(String tableName, String text) {
		String insertSql = "insert into " + tableName + " values(";
		String values[] = text.split(",");
		for (int i = 0; i < values.length - 1; i++) {
			insertSql += "?,";
		}
		insertSql += "?)";
		return insertSql;
	}

	private int[] getIntValues(String text, int time) {
		String values[] = text.split(",");
		int intValues[] = new int[values.length];
		Random random = new Random();

		for (int j = 0; j < values.length; j++) {
			String value = values[j];
			if (value.contains("~")) {
				String v[] = value.split("~");
				int min = Integer.parseInt(v[0]);
				int max = Integer.parseInt(v[1]);
				intValues[j] = random.nextInt(max) % (max - min + 1) + min;
			} else if (value.contains("|")) {
				String v[] = value.split("\\|");
				intValues[j] = Integer.parseInt(v[random.nextInt(v.length)]);
			} else if (value.contains("$")) {
				if (value.equals("$time")) {
					intValues[j] = time + 1;
				} else if (value.equals("$minus")) {
					intValues[j] = intValues[j - 2] - intValues[j - 1];
				} else if (value.matches("\\$\\d+")) {
					intValues[j] = intValues[Integer.parseInt(value
							.substring(1)) - 1]+intValues[Integer.parseInt(value
									.substring(1))];
				} else if (value.matches("\\$_\\d+")) {
					intValues[j] = lastValues[Integer.parseInt(value
							.substring(2)) - 1];
				}
			} else {
				intValues[j] = Integer.parseInt(value);
			}
		}
		this.lastValues = intValues;
		return intValues;
	}

	private int[] concatIntValues(int[] dimValues, int[] addValues) {
		int intValues[] = new int[dimValues.length + addValues.length];
		System.arraycopy(dimValues, 0, intValues, 0, dimValues.length);
		System.arraycopy(addValues, 0, intValues, dimValues.length,
				addValues.length);
		return intValues;
	}

	private void insertValues(String tableName, PreparedStatement pstmt,
			int[] intValues) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into " + tableName + " values(");
		for (int sbi = 0; sbi < intValues.length - 1; sbi++) {
			sb.append(intValues[sbi]);
			sb.append(", ");
		}
		sb.append(intValues[intValues.length - 1]);
		sb.append(");");
		System.out.println(sb);
		try {
			for (int k = 0; k < intValues.length; k++) {
				if (intValues[k] == STAND_FOR_STRING) {
					pstmt.setString(k + 1, "");
				} else {
					pstmt.setInt(k + 1, intValues[k]);
				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(tableName);
			System.out.println(intValues);
			e.printStackTrace();
		}
	}

	public void populateTable(String tableName, String columns1,
			String columns2, String columns3, int count1, int count2) {
		Connection con = null;
		try {
			Class.forName(DBDRIVER);
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);

			int currentTime = getTime(con);

			PreparedStatement pstmt1 = con.prepareStatement(getInsertSql(
					tableName, columns1 + "," + columns2));
			PreparedStatement pstmt2 = columns3.equals("") ? null : con
					.prepareStatement(getInsertSql(
							tableName.replace("_USER", "_O_USER"), columns1
									+ "," + columns3));

			for (int i = 0; i < count1; i++) {
				int dimValues[] = this.getIntValues(columns1, currentTime - 5
						* count1 + 5 * i);
				insertValues(
						tableName,
						pstmt1,
						concatIntValues(
								dimValues,
								this.getIntValues(columns2, currentTime
										- count1 + i)));
				if (pstmt2 != null) {
					for (int j = 0; j < count2; j++) {
						insertValues(
								tableName.replace("_USER", "_O_USER"),
								pstmt2,
								concatIntValues(
										dimValues,
										this.getIntValues(columns3, currentTime
												- 5 * count1 + 5 * i)));
					}
				}
			}
			pstmt1.close();
			if (pstmt2 != null)
				pstmt2.close();

			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void start(String kpiConfigFile) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(KpiProvisioner.class
					.getResource(kpiConfigFile));
			for (Object oTable : doc.getRootElement().elements()) {
				Element eTable = (Element) oTable;
				if ("false".equals(eTable.attributeValue("use"))) {
					continue;
				}
				String columns1 = "", columns2 = "", columns3 = "";
				for (Object oColumn : eTable.elements()) {
					Element eColumn = (Element) oColumn;
					if (eColumn.attributeValue("type").equals("0")) {
						columns1 = eColumn.getText();
					} else if (eColumn.attributeValue("type").equals("1")) {
						columns2 = eColumn.getText();
					} else if (eColumn.attributeValue("type").equals("2")) {
						columns3 = eColumn.getText();
					}
				}

				populateTable(eTable.attributeValue("name"), columns1,
						columns2, columns3,
						Integer.parseInt(eTable.attributeValue("count1")),
						Integer.parseInt(eTable.attributeValue("count2")));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		KpiProvisioner provisioner = new KpiProvisioner();
		provisioner.start("kpi.xml");
	}

}
