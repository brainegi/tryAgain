package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class userDAO {

	// DB情報
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/kandadb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	// DB情報からDBへ接続するメソッド
	private Connection getConnection() {
		try {
			// ドライバーのロード Connectionオブジェクト生成
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
