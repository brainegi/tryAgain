package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Question;
import dao.questionDAO;

/**
 * @author harashima
 * @version 10 一覧表示をする為のサーブレット
 *
 */
public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String error = "";
		String cmd = "";

		try {

			// DAOクラスオブジェクト化
			questionDAO qDao = new questionDAO();

			// ArrayList←DAOクラス検索結果
			ArrayList<Question> list = qDao.search();

			// リクエストスコープに登録する
			request.setAttribute("list", list);

		} catch (IllegalStateException e) {
			error = "DBに接続できませんでした。";
			cmd = "";

		} finally {

			// エラーがあるかないかで処理を分ける
			if (error.equals("")) {
				// エラーが無い場合→リストjsp
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			} else {
				// エラーがある場合→エラーjsp
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}
