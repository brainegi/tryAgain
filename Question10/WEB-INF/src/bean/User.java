package bean;

public class User {

	private String userid;				//管理者ID
	private String password;		//管理者パスワード


	//コンストラクタ定義
	public User() {
		this.userid = "";
		this.password = "";
	}


	//管理者ID-get・setメソッド
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	//管理者パス-get・setメソッド
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
