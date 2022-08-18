package command;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	//DB연동
	public Connection getConnection() throws Exception {
		
		Class.forName("org.mariadb.jdbc.Driver");		
		
		String url = "jdbc:mariadb://localhost:3306/m2board"; // mariadb안에있는 m2board 데이터베이스랑 연결함
		String dbuser = "root";
		String dbpw = "1234";
		
		Connection conn= DriverManager.getConnection(url, dbuser, dbpw);
		
		return conn;
		}
}
