package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginEntity;
import model.LoginLogicBO;

// そのまま呼び出されたらGET　＝＞　ログイン画面へ
// POSTで呼び出されたら　＝＞　ログイン成功画面へ
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");	
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userName");
		String pass = request.getParameter("userPass");
		
		// ログイン処理の実行
		LoginEntity loginEntity = new LoginEntity(userId,pass);
		LoginLogicBO bo = new LoginLogicBO();
		//アカウント情報を取得
		Account account = bo.execute(loginEntity);
		
		
		//ログイン成否で処理分岐
		if(account != null) { //ログイン成功時
			//セッションスコープにアカウントを保存
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			
			//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginOK.jsp");	
		dispatcher.forward(request, response);
		}else {//ログイン失敗時
			//リダイレクト
			request.setAttribute("errorMsg", "※IDとパスワードが一致しません");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("/example16/LoginServlet");
		}
	}

}
