package fragments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBC {
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost/test?characterEncoding=GBK","USERNAME","PASSWD");
			con.setAutoCommit(false);
			
			PreparedStatement ps =con.prepareStatement("select * from dics limit 10");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			ps.close();
			con.commit();
		}catch(SQLException e){
			con.rollback();
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

}
