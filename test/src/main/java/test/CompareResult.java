package test;

import java.io.*;
import java.util.*;

/**
 * This class is used to compare the content in 2 text file
 * It will generate 2 files to describe the common things, different things,
 * and 2 files indicating the things which are only in the separated files.
 * @author Simon Niu
 *
 */
public class CompareResult {

	private static String trim(String src){
		char c[]=new char[src.length()];
		int j=0;
		for(int i=0;i<src.length();i++){
			char cc=src.charAt(i);
			if(cc!='\''&&cc!=' '&&cc!='\t')
				c[j++]=cc;
		}
		return String.valueOf(c, 0, j);
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		String baseDir=".\\";//"D:\\ana\\";
		File oldDir=new File(baseDir+"old");
		String newDir=baseDir+"new\\";
		BufferedReader reader1=null,reader2=null;
		BufferedWriter writerBoth=null,writerOld=null,writerNew=null;
		LinkedList<String> list1=new LinkedList<String>();
		LinkedList<String> list2=new LinkedList<String>();
		for(File vehicle:oldDir.listFiles()){
			System.out.println("handling "+vehicle.getName()+"...");
			try{
				reader1=new BufferedReader(new FileReader(new File(vehicle.getAbsolutePath()+"\\Untranslated_"+vehicle.getName()+"_EN_to_PO.txt")));
				reader2=new BufferedReader(new FileReader(new File(newDir+vehicle.getName()+"\\Untranslated_"+vehicle.getName()+"_EN_to_PO.txt")));
				writerBoth=new BufferedWriter(new FileWriter(new File(baseDir+vehicle.getName()+"_Both.txt")));
				writerOld=new BufferedWriter(new FileWriter(new File(baseDir+vehicle.getName()+"_Old.txt")));
				writerNew=new BufferedWriter(new FileWriter(new File(baseDir+vehicle.getName()+"_New.txt")));
				String readline=null;
				while((readline=reader1.readLine())!=null)list1.add(readline);
				while((readline=reader2.readLine())!=null)list2.add(readline);
				reader1.close();
				reader2.close();
				
				Iterator<String> it1=null,it2=null;
				String s1=null,s2=null;
				
				it1=list1.iterator();
				while(it1.hasNext()){
					s1=it1.next();
					it2=list2.iterator();
					while(it2.hasNext()){
						s2=it2.next();
						if(s2.equals(s1)){
							writerBoth.write(s1);
							writerBoth.newLine();
							it1.remove();
							it2.remove();
							break;
						}
					}
				}
				
				//again
				it1=list1.iterator();
				while(it1.hasNext()){
					s1=it1.next();
					it2=list2.iterator();
					while(it2.hasNext()){
						s2=it2.next();
						if(trim(s2).equals(trim(s1))){
							writerBoth.write(s1);
							writerBoth.newLine();
							it1.remove();
							it2.remove();
							break;
						}
					}
				}
				
				it1=list1.iterator();
				it2=list2.iterator();
				while(it1.hasNext()){
					writerOld.write(it1.next());
					writerOld.newLine();
				}
				while(it2.hasNext()){
					writerNew.write(it2.next());
					writerNew.newLine();
				}

				list1.clear();
				list2.clear();
				
				writerBoth.close();
				writerOld.close();
				writerNew.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{

			}
		}

	}

}
