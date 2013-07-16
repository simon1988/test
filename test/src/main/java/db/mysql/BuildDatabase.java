package db.mysql;

import java.io.FileInputStream;
import java.sql.*;
public class BuildDatabase {

	final static String MYSQL_USER="root";
	final static String MYSQL_PASSWD="simon1988";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			FileInputStream is = new FileInputStream("J_Cdata.dic");
			byte[] buffer=new byte[12];
			byte[] b=new byte[1];
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test",MYSQL_USER,MYSQL_PASSWD);  
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"create table dics (ind integer NOT NULL AUTO_INCREMENT, jp varchar(12), cn varchar(12), adv char(1),primary key(ind))");
			stmt.close();
			
			PreparedStatement insertStatement = con.prepareStatement("insert into dics(jp,cn,adv) values(?,?,?)");
			
			while(is.read(buffer)!=-1){
				String jpWord=new String(buffer).trim();
				is.read(buffer);
				String cnWord=new String(buffer).trim();
				is.read(b,0,1);
				String adj=new String(b);
				insertStatement.setString(1, jpWord);
				insertStatement.setString(2, cnWord);
				insertStatement.setString(3, adj);
				//System.out.println();
				insertStatement.executeUpdate();
			}
			insertStatement.close(); 
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
