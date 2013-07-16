package db.mysql;
import java.sql.*;

public class QueryDatabase {

	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test?characterEncoding=GBK",BuildDatabase.MYSQL_USER,BuildDatabase.MYSQL_PASSWD);
			PreparedStatement ps =con.prepareStatement("select * from dics limit 10");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			ps.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
