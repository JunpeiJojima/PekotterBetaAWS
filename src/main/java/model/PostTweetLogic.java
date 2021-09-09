package model;

import dao.TweetDAO;

//つぶやき投稿の処理
public class PostTweetLogic {
	public void execute(Tweet tweet) {
		TweetDAO dao = new TweetDAO();
		dao.create(tweet);
	}
}
