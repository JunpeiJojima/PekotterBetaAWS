package logic;

import dao.TweetDAO;

public class GoodLogic {
	
	public void goodNumPlus(int id) {
		TweetDAO dao = new TweetDAO();
		dao.goodNum(id);
	}

}
