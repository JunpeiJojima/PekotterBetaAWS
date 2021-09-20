package logic;

import java.util.List;

import dao.TweetDAO;
import entity.Tweet;

public class GetTweetListLogic {
	public List<Tweet>execute(){
		TweetDAO dao = new TweetDAO();
		List<Tweet> tweetList = dao.findALL();
		return tweetList;
	}

}
