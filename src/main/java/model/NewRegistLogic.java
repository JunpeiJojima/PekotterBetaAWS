package model;

import dao.AccountDAO;

//ユーザー登録の処理
public class NewRegistLogic {
	public boolean regist(RegistUser registUser) {
		AccountDAO dao = new AccountDAO();
		boolean registResult = dao.newRegist(registUser);
		return registResult;
	}
}
