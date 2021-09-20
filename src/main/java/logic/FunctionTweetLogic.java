package logic;

import dao.TweetDAO;
import entity.Tweet;

public abstract  class FunctionTweetLogic {
	
	public Tweet SearchTweet(int id) {
		TweetDAO dao = new TweetDAO();
		Tweet tweet = dao.findTweet(id);
		return tweet;
	}
	
}
