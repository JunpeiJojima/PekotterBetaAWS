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
import logic.DeleteTweetLogic;
import logic.EditTweetLogic;
import logic.GetTweetListLogic;

@WebServlet("/DeleteTweetServlet")
public class DeleteTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		EditTweetLogic edit = new EditTweetLogic();
		Tweet tweet = edit.SearchTweet(id);
		
		request.setAttribute("deleteTweet", tweet);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/deleteTweet.jsp");	
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
			
		DeleteTweetLogic deletetweetLogic = new DeleteTweetLogic();
		deletetweetLogic.deleteTweet(id);
			
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

}
