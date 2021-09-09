package model;

import dao.AccountDAO;

public class RegistCheckLogic {
	public Account registCheck(RegistUser registUser) {
		Account result = null;
		AccountDAO dao = new AccountDAO();
		result = dao.findByIdMail(registUser);
		if(result == null) {
			return result;
		}
		return result;
	}

}
