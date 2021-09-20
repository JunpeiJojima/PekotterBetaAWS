package logic;

import dao.TweetDAO;

public class DeleteTweetLogic extends FunctionTweetLogic{
	
	public void deleteTweet(int id) {
		TweetDAO dao = new TweetDAO();
		dao.delete(id);
	}

}
