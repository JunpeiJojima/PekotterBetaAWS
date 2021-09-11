package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NewRegistLogic;
import model.RegistUser;

@WebServlet("/CreateNewUser")
public class CreateNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// ユーザー登録処理を行って、createResult jspへフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		RegistUser registUser = (RegistUser) session.getAttribute("registUser");
		//ユーザー登録処理
		NewRegistLogic newRegistLogic = new NewRegistLogic();
		boolean registResult = newRegistLogic.regist(registUser);
		
		//登録結果のbooleanをリクエストスコープに保存
		request.setAttribute("registResult", registResult);
		
		//登録結果画面にフォワード
		RequestDispatcher dispatcher =
						request.getRequestDispatcher("/WEB-INF/jsp/registResult.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}
