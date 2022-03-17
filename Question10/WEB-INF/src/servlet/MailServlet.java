package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Question;
import bean.SendMail;
import bean.User;
import dao.questionDAO;

public class MailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String error = "";
		String cmd = "";

		//mail機能オブジェクト
		SendMail sendMail = new SendMail();

		//送信元と送信者名をセット
		sendMail.setFromInfo("info@kanda-it-school.com", "お問い合わせシステム");

		//送信先をセット
		Question qObj = new Question();
		sendMail.setRecipients(qObj.getMail());

		//件名セット
		sendMail.setSubject("");

		//本文セット
		sendMail.setText("");

		//送信するメソッド
		sendMail.forwardMail();
	}
}
