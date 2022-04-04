package util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBUtil {
	public static Connection getConnection() {
	Connection conn = null;
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		//()안에 오는 값들을 갖고 하는ㄴ것이므로 static임
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return conn; //connection을 리턴함
	}
}
