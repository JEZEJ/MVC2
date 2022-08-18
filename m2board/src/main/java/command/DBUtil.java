package command;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	//DB연동
	public Connection getConnection() throws Exception {
		
		Class.forName("org.mariadb.jdbc.Driver");		
		
		String url = "jdbc:mariadb://localhost:3306/shop";
		String dbuser = "root";
		String dbpw = "1234";
		
		Connection conn= DriverManager.getConnection(url, dbuser, dbpw);
		
		return conn;
		}
}
