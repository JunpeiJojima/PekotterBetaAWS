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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);
		
		HttpSession session = request.getSession();
		Account loginUser = (Account) session.getAttribute("account");
		
		if(loginUser == null) { 
			response.sendRedirect("/PekotterBetaAWS/");
		}else { 
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		if(text != null && text.length() != 0) {
			text = text.concat("ぺこ");
			
			HttpSession session = request.getSession();
			Account loginUser = (Account) session.getAttribute("account");
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.JAPAN);
			Tweet tweet = new Tweet(loginUser.getName(),text,df.format(new Date()),loginUser.getUserId());
			PostTweetLogic postTweetLogic = new PostTweetLogic();
			postTweetLogic.execute(tweet);
			
		}else {
			request.setAttribute("errorMsg", "ぽえむが未入力ぺこよ");
		}
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

}
