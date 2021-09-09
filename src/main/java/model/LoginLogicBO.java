package model;

import dao.AccountDAO;

public class LoginLogicBO {
	
	public Account execute(LoginEntity loginEntity) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(loginEntity);
		return account;
	}

}
