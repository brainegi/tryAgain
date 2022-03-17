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

		// mail機能オブジェクト
		SendMail sendMail = new SendMail();

		// 送信元と送信者名をセット
		sendMail.setFromInfo("info@kanda-it-school.com", "お問い合わせシステム");

		// 送信先をセット
		String mail = request.getParameter("mail");
		sendMail.setRecipients(mail);

		// 件名セット
		String textA = request.getParameter("textA");
		sendMail.setSubject(textA);

		// 本文セット
		String textB = request.getParameter("textB");
		sendMail.setText(textB);

		// 送信するメソッド
		sendMail.forwardMail();

		// フォワード
		request.getRequestDispatcher("/view/sendConfirm.jsp").forward(request, response);
	}
}
