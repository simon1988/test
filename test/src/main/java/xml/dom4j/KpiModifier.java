package xml.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class KpiModifier {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File("C:\\Users\\xniu1\\workspaceIris\\codes\\server\\ivModels\\ipi\\LteS10GtpDatabaseModel.xml"));
			List<Element> tables = doc.selectNodes("//TABLE");
			for(Element table : tables){
//				System.out.println(table.attribute("name").getText());
				List<Element> measures = table.elements();
				int insertIndex = 0;
				Element max = null;
				for(Element measure : measures){
					insertIndex++;
					if(measure.getName().equals("MEASURE") && measure.attribute("name").getText().equals("PROCEDURELATENCYATT")){
						if(!(insertIndex < measures.size() && measures.get(insertIndex).attribute("name").getText().equals("MAXPROCEDURELATENCY"))){
							max = measure.createCopy();
							max.addAttribute("name", "MAXPROCEDURELATENCY");
							if(max.attribute("cognos-agg-type")!=null)max.addAttribute("cognos-agg-type", "max");
							max.addAttribute("column-number", ""+(Integer.parseInt(max.attributeValue("column-number"))+1));
							for(int i=insertIndex;i<measures.size();i++){
								if(measures.get(i).getName().equals("MEASURE"))measures.get(i).addAttribute("column-number", ""+(Integer.parseInt(measures.get(i).attributeValue("column-number"))+1));
							}
							measures.add(insertIndex, max);
						}
						break;
					}
				}
				
			}
			FileWriter fw =new FileWriter("C:\\Users\\xniu1\\workspaceIris\\codes\\server\\ivModels\\ipi\\LteS10GtpDatabaseModel.xml");
			XMLWriter writer = new XMLWriter(fw,OutputFormat.createPrettyPrint()); 
			writer.write(doc);
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
