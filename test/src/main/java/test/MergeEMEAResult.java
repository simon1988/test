package test;

import java.io.*;
import java.util.*;

public class MergeEMEAResult {

	ArrayList<String> lists[] = new ArrayList[7];
	
	public boolean contains2Letter(String s){
		boolean one = false;
		char c = '\0';
		for(int i=0;i<s.length();i++){
			c = s.charAt(i);
			if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
				if(one)return true;
				else one = true;
			}
		}
		return false;
	}
	public void analysis(String path,String filename) throws Exception{
		boolean isDS=false,is1stSame=true;
		if(filename.contains("EN_DS"))isDS=true;
		for(int i=0;i<lists.length;i++)lists[i].clear();
		int curNumber = -1,curListNumber=0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\"+filename),"Unicode"));
		String line=null;
		while((line=reader.readLine())!=null){
			if(line.equals("0") && curNumber!=0){
				curListNumber++;
				curNumber = 0;
				is1stSame=true;
			}
			if(line.equals(curNumber+"")){
				if(isDS){
					if(is1stSame)is1stSame=false;
					else {
						reader.readLine();
						reader.readLine();
						continue;
					}
				}
				
			}
			else if(line.equals((curNumber+1)+"")){
				curNumber++;
				is1stSame=true;
			}
			else{
				continue;
			}
			line = reader.readLine();
			if(this.contains2Letter(line)){
				if(curListNumber==1)lists[0].add(line);
				lists[curListNumber].add(reader.readLine());
			}
			else{
				reader.readLine();
			}
		}
		reader.close();
		int listLength = lists[0].size();
		System.out.println("---------------");
		for(int i=0;i<7;i++){
			//if(lists[i].size()!=listLength)throw new Exception("assert error!"+
				//	listLength+":"+i+"-"+lists[i].size());
			System.out.println(lists[i].size());
		}
		System.out.println("---------------");
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"\\merge.txt",true),"Unicode"));
		StringBuilder str;
		for(int iElement=0;iElement<listLength;iElement++){
			str = new StringBuilder("");
			for(int iList=0;iList<7;iList++){
				str.append(lists[iList].get(iElement));
				str.append(iList==6?"\n":"\t");
			}
			writer.write(str.toString());
		}
		writer.close();
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) throws Exception{	
		MergeEMEAResult mer = new MergeEMEAResult();
		for(int i=0;i<7;i++){
			mer.lists[i] = new ArrayList<String>(1000);
		}
		File basedir = new File("D:\\ABCD_data\\");
		for(File dir : basedir.listFiles()){
			mer.analysis(dir.getAbsolutePath(),"EN_DS.DB_resource.txt");
			mer.analysis(dir.getAbsolutePath(),"EN_DTC.DB_resource.txt");
			mer.analysis(dir.getAbsolutePath(),"EN_TXT.DB_resource.txt");
		}
	}

}
