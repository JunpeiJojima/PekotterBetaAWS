package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Tweet;
import logic.EditTweetLogic;
import logic.GetTweetListLogic;

@WebServlet("/EditTweetServlet")
public class EditTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		EditTweetLogic edit = new EditTweetLogic();
		Tweet tweet = edit.SearchTweet(id);
		
		request.setAttribute("editTweet", tweet);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/editTweet.jsp");	
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		
		//入力値チェック（未入力かどうか）
		if(text != null && text.length() != 0) {
			//強制的に語尾にぺこをつける
			if(!text.endsWith("ぺこ")) {
			text = text.concat("ぺこ");
			}
			
			EditTweetLogic editTweetLogic = new EditTweetLogic();
			editTweetLogic.editTweet(text,id);
			
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
