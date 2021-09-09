package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.GetTweetListLogic;
import model.PostTweetLogic;
import model.Tweet;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// アカウント情報取得して、main jspへフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
//		//つぶやきリストをアプリケーションスコープから取得
//		ServletContext application = this.getServletContext();
//		List<Tweet> tweetList = (List<Tweet>)application.getAttribute("mutterList");
//		//取得できなかった場合は、つぶやきリストを新規作成して
//		//アプリケーションスコープに保存
//		if(tweetList == null) {
//			tweetList = new ArrayList<>();
//			application.setAttribute("tweetList", tweetList);
//		}
		
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
			response.sendRedirect("/example16/");
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
			//アプリスコに保存されたつぶやきリストを取得
//			ServletContext application = this.getServletContext();
//			List<Tweet> tweetList =
//					(List<Tweet>) application.getAttribute("tweetList");
			
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account loginUser = (Account) session.getAttribute("account");
			
			
			//つぶやきをデータベースに追加
			// PostTweet.execute => TweetDao.create(ここでインサート)
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			Tweet tweet = new Tweet(loginUser.getName(),text,df.format(new Date()));
			PostTweetLogic postTweetLogic = new PostTweetLogic();
			//postTweetLogic.execute(tweet, tweetList);
			postTweetLogic.execute(tweet);
			
//			//アプリケーションスコープにつぶやきリストを保存
//			application.setAttribute("tweetList", tweetList);
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
