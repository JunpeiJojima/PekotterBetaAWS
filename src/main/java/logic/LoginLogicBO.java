package logic;

import dao.AccountDAO;
import entity.Account;
import entity.LoginEntity;

public class LoginLogicBO {
	
	public Account execute(LoginEntity loginEntity) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(loginEntity);
		return account;
	}

}
