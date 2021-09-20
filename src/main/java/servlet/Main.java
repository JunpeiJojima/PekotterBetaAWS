package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Account;
import entity.Tweet;
import logic.GetTweetListLogic;
import logic.PostTweetLogic;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// アカウント情報取得して、main jspへフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//つぶやきリストを取得してリクエストスコープに保存
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);
		
		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		Account loginUser = (Account) session.getAttribute("account");
		
		if(loginUser == null) { //ログインしてない場合は
			//リダイレクト
			response.sendRedirect("/PekotterBetaAWS/");
		}else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	
//     mainjspで呟いたらここに来る、
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		//入力値チェック（未入力かどうか）
		if(text != null && text.length() != 0) {
			//強制的に語尾にぺこをつける
			text = text.concat("ぺこ");
			
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account loginUser = (Account) session.getAttribute("account");
			
			//つぶやきをデータベースに追加
			Locale japan = new Locale("ja","JP","JP");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm",japan);
			Tweet tweet = new Tweet(loginUser.getName(),text,df.format(new Date()),loginUser.getUserId());
			PostTweetLogic postTweetLogic = new PostTweetLogic();
			postTweetLogic.execute(tweet);
			
		}else {
			// エラーメッセージをrequestスコープに保存
			request.setAttribute("errorMsg", "ぽえむが未入力ぺこよ");
		}
		//つぶやきリストを取得してリクエストスコープに保存
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);
		
		//メイン画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

}
