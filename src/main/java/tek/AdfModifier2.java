package tek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdfModifier2 {

	private final static String PATH = "C:\\Users\\xniu1\\workspaceIris\\branches\\0717\\models\\ipi\\adf\\reports\\";
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
					if (line.contains("label=\"Min Latency (msec)\"")) {
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
						String toreplace="[Reporting]."+ tablePrefix+ ".[MINPROCEDURELATENCY]";
						list.set(
								i,
								line.replace(toreplace, 
										"case "+toreplace+" when 1000000000 then null else "+toreplace+" end")
										);
						break;
					}
					
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
		AdfModifier2 modifier = new AdfModifier2();
		for (String file : files) {
			String adfModelFile = PATH + file;
			modifier.modifyAdf(adfModelFile);
		}
	}

}
