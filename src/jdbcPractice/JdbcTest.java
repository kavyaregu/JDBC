package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest {
public static void main(String[] args) throws SQLException{
	String url="jdbc:mysql://localhost:3306/java";
	String user="root";
	String password="Nikshith@2723";
	String query="insert into sample values(?,?)";
	String query2="update sample set name=? where id=?";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	}catch(ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	try(Connection con=DriverManager.getConnection(url, user, password);
			Statement stmt=con.createStatement();
			PreparedStatement pst=con.prepareStatement(query);
			PreparedStatement pst1=con.prepareStatement(query2);
			Scanner scan=new Scanner(System.in);
			){
		ResultSet rs=stmt.executeQuery("select * from sample");
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("name");
			System.out.println("name is"+name+"id is"+id);
		}
		/*String query1="insert into sample values(600,'kavya')";
		stmt.executeUpdate(query1);
		System.out.println("inserted");*/
		System.out.println("enter id anmd new name");
		int id=scan.nextInt();
		String name=scan.next();
		pst.setInt(1, id);
		pst.setString(2, name);
		//pst.execute();
		System.out.println("inserted");
		pst1.setInt(2, id);
		pst1.setString(1, name);pst1.execute();
		System.out.println("updated");
		
		
	}
}
}
