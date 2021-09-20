package logic;

import dao.AccountDAO;
import entity.RegistUser;

//ユーザー登録の処理
public class NewRegistLogic {
	public boolean regist(RegistUser registUser) {
		AccountDAO dao = new AccountDAO();
		boolean registResult = dao.newRegist(registUser);
		return registResult;
	}
}
