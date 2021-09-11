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
import model.InputNullCheck;
import model.RegistCheckLogic;
import model.RegistUser;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// registInput jspに飛ばす
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registInput.jsp");	
		dispatcher.forward(request, response);
		
	}
	
	// ユーザーチェックして、OKならinput check jspへ　NGならregistInput jspに飛ばす
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメーターの取得
				request.setCharacterEncoding("UTF-8");
				String userId = request.getParameter("userId");
				String pass = request.getParameter("pass");
				String mail = request.getParameter("mail");
				String name = request.getParameter("name");
				int age;//数字かどうかのチェック
				if(request.getParameter("age").matches("[+-]?\\d*(\\.\\d+)?") && 
						(request.getParameter("age").length() != 0)) {
				  age = Integer.parseInt(request.getParameter("age"));
				}else {
					age = -1;
				}
				
				RegistUser registUser = new RegistUser(userId,pass,mail,name,age);
				Account account = null;
				
				//入力のnullチェック
				InputNullCheck inputNullCheck = new InputNullCheck();
				boolean nullResult = inputNullCheck.nullCheck(registUser);
				
				if(nullResult) {
				// IDとメールが使用済みかのチェック
				RegistCheckLogic bo = new RegistCheckLogic();
				//アカウント情報を取得
				account = bo.registCheck(registUser);
				}
				
				// ID、メール登録済みかで処理分岐
				if(account == null && age != -1 && nullResult) { //　チェック成功時（登録してないとき）
					//セッションスコープに登録情報（RegistUser)を保存
					HttpSession session = request.getSession();
					session.setAttribute("registUser", registUser);
					
					// input check jspへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/inputCheck.jsp");	
				dispatcher.forward(request, response);
				
				}else {//チェック失敗時（すでに使用されてる）
					
					errorMsg(request, age, registUser, account, nullResult);
					//入力画面（registInput）へフォワード
					RequestDispatcher dispatcher = 
							request.getRequestDispatcher("/WEB-INF/jsp/registInput.jsp");	
					dispatcher.forward(request, response);
				}
	}

	private void errorMsg(HttpServletRequest request, int age, RegistUser registUser, Account account,
			boolean nullResult) {
		if(account == null) {
			request.setAttribute("errorMsg", "※入力内容が正しくない可能性があります");
		// エラーメッセージをrequestスコープに保存
		}else if(account.getUserId().equals(registUser.getUserId())) {
			request.setAttribute("errorMsg", "※ユーザーIDが既に使用されています");
		}else if(account.getMail().equals(registUser.getMail())){
			request.setAttribute("errorMsg", "※メールアドレスが既に使用されています");
		}else if(age == -1){
			request.setAttribute("errorMsg", "※年齢の入力内容が正しくない可能性があります");
		}else if(nullResult){
			request.setAttribute("errorMsg", "※未入力があります　項目はすべて入力してください");
		}else {
			request.setAttribute("errorMsg", "※入力内容が正しくない可能性があります");
		}
	}

}
