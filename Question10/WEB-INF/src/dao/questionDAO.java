package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Question;

public class questionDAO {

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

	/**
	 * @author harashima
	 * @return list お問合せ内容の全検索
	 */
	public ArrayList<Question> selectAll() {
		Connection con = null;
		Statement smt = null;

		try {

			// データ接続→SQL文送信（結果受け取り）
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT * FROM question";
			ArrayList<Question> list = new ArrayList<Question>();

			ResultSet rs = smt.executeQuery(sql);

			// 検索結果をDTOに格納→ArrayListに格納
			while (rs.next()) {
				Question qObj = new Question();
				qObj.setNum(rs.getInt("num"));
				qObj.setName(rs.getString("name"));
				qObj.setAge(rs.getString("age"));
				qObj.setSex(rs.getString("sex"));
				qObj.setAdress(rs.getString("adress"));
				qObj.setMail(rs.getString("mail"));
				qObj.setSelected(rs.getString("selected"));
				qObj.setText(rs.getString("text"));
				qObj.setDate(rs.getString("date"));
				list.add(qObj);
			}

			// 返却
			return list;

		} catch (SQLException e) {
			throw new IllegalStateException(e);

		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}

	}

	/**
	 * @author harashima
	 * @return list お問合せ内容の一部検索
	 */
	public ArrayList<Question> search() {
		Connection con = null;
		Statement smt = null;

		try {

			// データ接続→SQL文送信（結果受け取り）
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT num, name, sex, text, date FROM question";
			ArrayList<Question> list = new ArrayList<Question>();

			ResultSet rs = smt.executeQuery(sql);

			// 検索結果をDTOに格納→ArrayListに格納
			while (rs.next()) {
				Question qObj = new Question();
				qObj.setNum(rs.getInt("num"));
				qObj.setName(rs.getString("name"));
				qObj.setSex(rs.getString("sex"));

				//字数判定
				if (rs.getString("text").length() > 20) {
					String text = rs.getString("text").substring(0, 20);
					qObj.setText(text);
				} else {
					qObj.setText(rs.getString("text"));
				}

				qObj.setDate(rs.getString("date"));
				list.add(qObj);
			}

			// 返却
			return list;

		} catch (SQLException e) {
			throw new IllegalStateException(e);

		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}

	}
}
