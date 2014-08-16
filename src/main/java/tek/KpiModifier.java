package tek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class KpiModifier {

	private final static String PATH = "C:\\Users\\xniu1\\workspaceIris\\codes\\server\\ivModels\\ipi\\";
	
	private final static String KPI_FILES[] = {
		"LteS1MMEAPKpiModel.xml",
		"LteS1MMENASKpiModel.xml",
		"LteS10GtpKpiModel.xml",
		"LteS11GtpKpiModel.xml",
		"LteS5S8GtpKpiModel.xml",
		"LteS6aDiameterKpiModel.xml",
		"GnCpKpiModel.xml",
		"CoreInfrastructuralDNSKpiModel.xml",
		"CoreInfrastructuralDHCPKpiModel.xml",
		"CoreInfrastructuralRADIUSKpiModel.xml"
	};
	private final static String ADDITIONAL_SERVICE_FILES[] = {
		"LteS1MMEServiceModel.xml.example",
		"LteS6aDiameterDataNetworkServiceModel.xml",
		"GnCpNetworkServiceModel.xml",
		"CoreInfrastructuralNetworkServiceModel.xml"
	};
	
	private static Logger logger = Logger.getLogger(KpiModifier.class);

	private boolean hasMaxFormula = false;
	private ArrayList<String> list = new ArrayList<String>(2000);

	LinkedList<Integer> list1 = new LinkedList<Integer>();
	LinkedList<Integer> list2 = new LinkedList<Integer>();
	LinkedList<Integer> list3 = new LinkedList<Integer>();

	private Pattern measurePattern = Pattern
			.compile("column-number=\"(\\d+)\"");

	private String createMax(String att) {
		Matcher mat = measurePattern.matcher(att);
		if (!mat.find()) {
			return null;
		}
		String columnNumber = mat.group(1);
		return att
				.replace(columnNumber,
						"" + (Integer.parseInt(columnNumber) + 1))
				.replace("sum", "max")
				.replace("PROCEDURELATENCYATT", "MAXPROCEDURELATENCY");
	}

	private String createMin(String max) {
		Matcher mat = measurePattern.matcher(max);
		if (!mat.find()) {
			return null;
		}
		String columnNumber = mat.group(1);
		return max
				.replace(columnNumber,
						"" + (Integer.parseInt(columnNumber) + 1))
				.replace("max", "min")
				.replace("MAXPROCEDURELATENCY", "MINPROCEDURELATENCY");
	}

	private String increaseMeasure(String measure, int n) {
		Matcher mat = measurePattern.matcher(measure);
		if (!mat.find()) {
			return null;
		}
		String columnNumber = mat.group(1);
		return measure.replace(columnNumber,
				"" + (Integer.parseInt(columnNumber) + n));
	}

	public void modifyDbModel(String dbModelFile) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dbModelFile), "UTF-8"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(dbModelFile + ".cg"), "UTF-8"));
			String line = "", att = "", max = "";
			int flag = -1;
			Pattern pat = Pattern
					.compile("<MEASURE name=\"PROCEDURELATENCYATT\"");
			while ((line = reader.readLine()) != null) {

				if (flag == 0) {
					if (line.contains("MAXPROCEDURELATENCY")) {
						max = line;
						flag = 1;
						line = line + "\n" + createMin(max);
					} else {
						max = createMax(att);
						flag = 2;
						if (line.contains("MEASURE")) {
							line = increaseMeasure(line, 2);
						} else {
							flag = -1;
						}
						line = max + "\n" + createMin(max) + "\n" + line;
					}
				} else if (flag > 0) {
					if (!line.contains("MEASURE")) {
						flag = -1;
					} else {
						line = increaseMeasure(line, flag);
					}
				} else {
					if (pat.matcher(line).find()) {
						att = line;
						flag = 0;
					}
				}
				writer.write(line + "\n");
			}
			reader.close();
			writer.close();
			File oriFile = new File(dbModelFile);
			oriFile.delete();
			new File(dbModelFile + ".cg").renameTo(oriFile);
		} catch (FileNotFoundException e) {
			logger.error("File "+dbModelFile +" does not exist, ignored.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Process "+dbModelFile+" complete.");
		modifyServiceModel(dbModelFile);
	}

	private LinkedList<Integer> getRelevenceList(String kpiModelFile) {
		LinkedList<Integer> list;
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(kpiModelFile));

			this.hasMaxFormula = false;
			List<Element> formulas = doc.selectNodes("//FORMULA");
			for (Element formula : formulas) {
				if (formula.attributeValue("name").endsWith(
						"LatencyMax")) {
					this.hasMaxFormula = true;
				}
			}

			List<Element> kpis = doc.selectNodes("//KPI");
			for (Element kpi : kpis) {
				Element kf = (Element) kpi.elements("KPI-FORMULA").get(0);
				if (kf.attributeValue("name").endsWith("LatencyAvg")) {
					if (kpi.getParent().attributeValue("name")
							.equals("Accessibility")) {
						list = list1;
					} else if (kpi.getParent().attributeValue("name")
							.equals("Retainability")) {
						list = list2;
					} else {
						list = list3;
					}
					list.add(Integer.parseInt(kpi.attributeValue("relevance")));
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list1);
		Collections.sort(list2);
		Collections.sort(list3);
		return list1;
	}

	private int getIncrement(List<Integer> list, int n) {
		int i = 0;
		for (Integer j : list) {
			if (n > j) {
				i++;
			}
		}
		return 2 * i;
	}

	public void modifyKpiModel(String kpiModelFile) {
		try {
			LinkedList<Integer> relevanceList = getRelevenceList(kpiModelFile);

			System.out.println(list1);System.out.println(list2);System.out.println(list3);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(kpiModelFile), "UTF-8"));
			String line = "";
			list.clear();
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
			String[] copyList = list.toArray(new String[0]);

			Pattern formulaPatternAtt = Pattern
					.compile("<FORMULA name=\"(\\w+Latency)Att\"");
			Pattern formulaPatternMax = Pattern
					.compile("<FORMULA name=\"(\\w+Latency)Max\"");
			Pattern kpiPattern = Pattern
					.compile("<KPI name=\"\\w+_Latency_?Avg\"");
			Pattern kpiPattern2 = Pattern
					.compile("<KPI name=\"\\w+_Avg_?Latency\"");
			Pattern relevancePattern = Pattern.compile("relevance=\"(\\d+)\"");

			for (int i = 0; i < copyList.length; i++) {
				line = copyList[i];
				if (hasMaxFormula) {
					Matcher mat = formulaPatternMax.matcher(line);
					if (mat.find()) {
						list.add(
								i + 4,
								"    <FORMULA name=\""
										+ mat.group(1)
										+ "Min\" unit-type=\"counter\"\n             java-type=\"LONG\" alarmable=\"false\" formula=\"min(MINPROCEDURELATENCY)\"\n             closure-formula=\"$measures.get(&quot;MINPROCEDURELATENCY&quot;).doubleValue()\" />\n");
						break;
					}
				} else {
					Matcher mat = formulaPatternAtt.matcher(line);
					if (mat.find()) {
						list.add(
								i + 4,
								"    <FORMULA name=\""
										+ mat.group(1)
										+ "Max\" unit-type=\"counter\"\n             java-type=\"LONG\" alarmable=\"false\" formula=\"max(MAXPROCEDURELATENCY)\"\n             closure-formula=\"$measures.get(&quot;MAXPROCEDURELATENCY&quot;).doubleValue()\" />\n"
										+ "\n    <FORMULA name=\""
										+ mat.group(1)
										+ "Min\" unit-type=\"counter\"\n             java-type=\"LONG\" alarmable=\"false\" formula=\"min(MINPROCEDURELATENCY)\"\n             closure-formula=\"$measures.get(&quot;MINPROCEDURELATENCY&quot;).doubleValue()\" />\n");
						break;
					}
				}
			}

			copyList = list.toArray(new String[0]);

			String kpiAvg = "";
			boolean kpiMet = false;
			int value = 0;

			for (int i = 0; i < copyList.length; i++) {
				line = copyList[i];
				if (kpiMet) {
					Matcher mat = relevancePattern.matcher(line);
					if (mat.find()) {
						value = Integer.parseInt(mat.group(1));
						value += getIncrement(relevanceList, value);
						line = line.replace(mat.group(1), "" + (value));
						list.set(i, line);
					}
					
					if (line.contains("</KPI>")) {
						kpiAvg += line;
						kpiMet = false;
						String relevancePrefix = "relevance=\"";
						String kpiAvgMax = kpiAvg.replaceAll("kqi-alias=\"(\\w+)AL(\\w*)\"", "kqi-alias=\"$1MAXL$2\"");
						String kpiAvgMin = kpiAvg.replaceAll("kqi-alias=\"(\\w+)AL(\\w*)\"", "kqi-alias=\"$1MINL$2\"");
						if(kpiAvgMax.equals(kpiAvg)){
							kpiAvgMax = kpiAvg.replaceAll("kqi-alias=\"(\\w+)LA(\\w*)\"", "kqi-alias=\"$1MAXL$2\"");
							kpiAvgMin = kpiAvg.replaceAll("kqi-alias=\"(\\w+)LA(\\w*)\"", "kqi-alias=\"$1MINL$2\"");
							if(kpiAvgMax.equals(kpiAvg)){
								kpiAvgMax = kpiAvg.replaceAll("kqi-alias=\"(\\w+)ACL(\\w*)\"", "kqi-alias=\"$1MAXL$2\"");
								kpiAvgMin = kpiAvg.replaceAll("kqi-alias=\"(\\w+)ACL(\\w*)\"", "kqi-alias=\"$1MINL$2\"");
								if(kpiAvgMax.equals(kpiAvg)){
									throw new Exception("kqi can't be changed:\n"+kpiModelFile+"\n"+kpiAvg);
								}
							}
						}
						
						list.set(
								i,
								list.get(i)
										+ "\n\n"
										+ kpiAvgMax.replace("Avg", "Max").replace(
												relevancePrefix + value,
												relevancePrefix + (1 + value))
										+ "\n\n"
										+ kpiAvgMin.replace("Avg", "Min").replace(
												relevancePrefix + value,
												relevancePrefix + (2 + value)));
						kpiAvg = "";
					}else{
						kpiAvg += line + "\n";
					}
				} else {
					if (line.contains("</CATEGORY>")) {
						if (relevanceList == list1) {
							relevanceList = list2;
						} else if (relevanceList == list2) {
							relevanceList = list3;
						}
					} else if (kpiPattern.matcher(line).find()||kpiPattern2.matcher(line).find()||(line.endsWith("<KPI")&&copyList[i+1].contains("_Latency_Avg"))) {
						kpiMet = true;
						kpiAvg = line + "\n";

					} else {
						Matcher mat = relevancePattern.matcher(line);
						if (mat.find()) {
							value = Integer.parseInt(mat.group(1));
							value += getIncrement(relevanceList, value);
							list.set(i,
									line.replace(mat.group(1), "" + (value)));
						}
					}
				}
			}

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(kpiModelFile), "UTF-8"));
			for (String s : list) {
				writer.write(s + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			logger.error("File "+kpiModelFile +" does not exist, ignored.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			list1.clear();
			list2.clear();
			list3.clear();
		}
		logger.info("Process "+kpiModelFile+" complete.");
	}

	public void modifyServiceModel(String serviceModelFile) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(serviceModelFile), "UTF-8"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(serviceModelFile + ".cg"), "UTF-8"));
			String line = "";
			Pattern pat = Pattern.compile("<KPI name=\"\\w+_Latency_?Avg");
			Pattern pat2 = Pattern.compile("<KPI name=\"\\w+_Avg_?Latency\"");
			while ((line = reader.readLine()) != null) {

				if (pat.matcher(line).find()||pat2.matcher(line).find()) {
					line = line + "\n" + line.replace("Avg", "Max").replace("calculated", "max") + "\n"
							+ line.replace("Avg", "Min").replace("calculated", "min");
				}
				writer.write(line + "\n");
			}
			reader.close();
			writer.close();
			File oriFile = new File(serviceModelFile);
			oriFile.delete();
			new File(serviceModelFile + ".cg").renameTo(oriFile);
		} catch (FileNotFoundException e) {
			logger.error("File "+serviceModelFile +" does not exist, ignored.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Process "+serviceModelFile+" complete.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KpiModifier modifier = new KpiModifier();
//		String dbModelFile = PATH + "LteS10GtpDatabaseModel.xml";
//		modifier.modifyDbModel(dbModelFile);
//		String kpiModelFile = PATH + "GnCpKpiModel.xml";
//		modifier.modifyKpiModel(kpiModelFile);
//		String ServiceModelFile = PATH + "CoreInfrastructuralNetworkServiceModel.xml";
//		modifier.modifyServiceModel(ServiceModelFile);
		
		for(String kpiFile : KPI_FILES){
			modifier.modifyDbModel(PATH+kpiFile.replace("Kpi", "Database"));
			modifier.modifyKpiModel(PATH+kpiFile);
			modifier.modifyServiceModel(PATH+kpiFile.replace("Kpi", "Service")+".example");
		}
		modifier.modifyDbModel(PATH+"CoreInfrastructuralDatabaseModel.xml");
		for(String serviceFile: ADDITIONAL_SERVICE_FILES){
			modifier.modifyServiceModel(PATH+serviceFile);
		}
	}

}
