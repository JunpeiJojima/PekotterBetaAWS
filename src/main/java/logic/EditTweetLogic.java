package logic;

import dao.TweetDAO;

public class EditTweetLogic extends FunctionTweetLogic{
	
	public void editTweet(String text , int id) {
		TweetDAO dao = new TweetDAO();
		dao.edit(text,id);
	}

}
