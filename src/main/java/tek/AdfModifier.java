package tek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdfModifier {

	private final static String PATH = "C:\\Users\\xniu1\\workspaceIris\\codes\\models\\ipi\\adf\\reports\\";
	private final static String files[] = {
			"CoreInfrastructuralDHCPProcedureReports.xml",
			"CoreInfrastructuralDNSProcedureReports.xml",
			"CoreInfrastructuralRADIUSProcedureReports.xml",
			"GNAccessibilityCoreReports.xml", "GNProcedureReportsGTP.xml",
			"GNRetainabilityCoreReports.xml",
			"S10GTPControlPlaneProcedureReports.xml",
			"S11GTPControlPlaneProcedureReports.xml",
			"S11GTPCoreAccessibilityReports.xml",
			"S1MMEAPControlPlaneProcedureByENBNGrpReports.xml",
			"S1MMEAPControlPlaneProcedureReports.xml",
			"S1MMEAPHandoverRetainabilityReports.xml",
			"S1MMEAPProcedureByQCIReports.xml",
			"S1MMENASAccessibilityReports.xml",
			"S1MMENASProcedureByENBNGrpReports.xml",
			"S1MMENASProcedureByQCIReports.xml",
			"S1MMENASProcedureReports.xml",
			"S1MMENASRetainabilityByENBNGrpReports.xml",
			"S1MMENASRetainabilityByMMEReports.xml",
			"S1MMENASRetainabilityByULIReports.xml",
			"S5S8GTPControlPlaneProcedureReports.xml",
			"S5S8GTPCoreAccessibiltyReports.xml",
			"S5S8GTPCoreRetainabilityReports.xml",
			"S5S8UPProcedureReportsEmailMMS.xml",
			"S5S8UPProcedureReportsforDNS.xml",
			"S5S8UPProcedureReportsWebBrowsing.xml",
			"S6aControlPlaneProcedureReports.xml" };
	private ArrayList<String> list = new ArrayList<String>(2000);

	public void modifyAdf(String adfFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(adfFile));
			String line = "";
			list.clear();
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
			int flag = 0;
			Pattern avgPattern = Pattern
					.compile("\\[\\w+?\\$\\(date\\)_USER\\]");
			String tablePrefix = "";
			for (int i = 0; i < list.size(); i++) {
				line = list.get(i);
				if (flag == 0) {
					if (line.contains("label=\"Avg Latency (msec)\"")) {
						flag = 1;
						Matcher mat = avgPattern.matcher(line);
						if (!mat.find()) {
							mat = avgPattern.matcher(list.get(i + 2));
							if (!mat.find()) {
								throw new Exception(adfFile
										+ ": Avg is not found!");
							}
						}
						tablePrefix = mat.group();
						System.out.println(mat.group());
					}
				} else if (flag == 1) {
					if (line.contains("</DataItem>")) {
						list.set(
								i,
								line
										+ "\n\t\t\t\t<DataItem name=\"PDP_MAX_LAT_1\" label=\"Max Latency (msec)\" expression=\"[Reporting]."
										+ tablePrefix
										+ ".[MAXPROCEDURELATENCY]\"/>\n\t\t\t\t<DataItem name=\"PDP_MIN_LAT_1\" label=\"Min Latency (msec)\" expression=\"[Reporting]."
										+ tablePrefix
										+ ".[MINPROCEDURELATENCY]\"/>");
						flag = 2;
					}
				} else if (flag == 2) {
					if (line.contains("<Lists>")) {
						flag = 3;
					}
				} else if (flag == 3) {
					if (line.contains("AVG_LAT")) {
						list.set(
								i,
								list.get(i)
										+ "\n\t\t\t<DataItem ref=\"PDP_MAX_LAT_1\"/>\n\t\t\t<DataItem ref=\"PDP_MIN_LAT_1\"/>");
						break;
					}
					// if (line.contains("<SortBy")) {
					// list.set(
					// i - 1,
					// "\t\t\t<DataItem ref=\"PDP_MAX_LAT_1\"/>\n\t\t\t<DataItem ref=\"PDP_MIN_LAT_1\"/>\n"
					// + list.get(i - 1));
					// break;
					// }
				}
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(adfFile));
			for (String s : list) {
				writer.write(s + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AdfModifier modifier = new AdfModifier();
		for (String file : files) {
			String adfModelFile = PATH + file;
			modifier.modifyAdf(adfModelFile);
		}
	}

}
