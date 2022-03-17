package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.*;

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

	// 問い合わせ内容を登録
	public void insert(Question question) {

		Connection con = null;
		Statement smt = null;

		//SQL文
		String sql ="INSERT INTO question(name,age,sex,adress,mail,selected,text,date)"
				+ " VALUES ('"
				+ question.getName() + "','" + question.getAge() + "','"
				+ question.getSex() + "','" + question.getAdress() + "','"
				+ question.getMail() + "','" + question.getSelected() + "','"
				+ question.getText() + "','" + question.getDate() + "')";

		try {

			//送信準備
			con = getConnection();
			smt = con.createStatement();

			//SQLを用いて接続
			smt.executeUpdate(sql);

		} catch(Exception e) {
			throw new IllegalStateException(e);

		} finally {
			if(smt != null) {
				try {smt.close();} catch(SQLException ignore) {}
			}

			if(con != null) {
				try {con.close();} catch(SQLException ignore) {}
			}

		}

	}

	//詳細画面表示
	public Question selectBynum(String num) {

		//DB接続用
		Connection con = null;
		Statement smt = null;

		//返却用オブジェクト生成
		Question question = new Question();

		//SQL文
		String sql ="SELECT * FROM question WHERE num ='" + num + "'";

		try {

			//送信準備
			con = getConnection();
			smt = con.createStatement();

			//SQL文を用いて接続
			ResultSet rs = smt.executeQuery(sql);

			//返却用オブジェクトに取得した情報を格納
			while(rs.next()) {
				question.setNum(rs.getInt("num"));
				question.setName(rs.getString("name"));
				question.setAge(rs.getString("age"));
				question.setSex(rs.getString("sex"));
				question.setAdress(rs.getString("adress"));
				question.setMail(rs.getString("mail"));
				question.setSelected(rs.getString("selected"));
				question.setText(rs.getString("text"));
				question.setDate(rs.getString("date"));
			}

		} catch(Exception e) {
			throw new IllegalStateException(e);

		} finally {
			if(smt != null) {
				try {smt.close();} catch(SQLException ignore) {}
			}

			if(con != null) {
				try {con.close();} catch(SQLException ignore) {}
			}
		}

		//返却
		return question;
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


