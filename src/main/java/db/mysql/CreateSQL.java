package db.mysql;
import java.io.*;
public class CreateSQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			FileInputStream is = new FileInputStream("J_Cdata.dic");
			byte[] buffer=new byte[12];
			byte[] b=new byte[1];
			BufferedWriter bw=new BufferedWriter(new FileWriter("jp.sql"));
			bw.write("create table dics (jp varchar(12), cn varchar(12), adv char(1),primary key(jp));\n");
			while(is.read(buffer)!=-1){
				String jp=new String(buffer).trim();
				is.read(buffer);
				String cn=new String(buffer).trim();
				is.read(b,0,1);
				String tmp=new String(b);
				String sql = "insert into dics values('"+jp+"','"+cn+"','"+tmp+"')";
				//System.out.println(sql);
				bw.write(sql+";\n");
			}
			bw.close(); 
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
