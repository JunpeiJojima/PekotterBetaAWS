package model;

public class InputNullCheck {
	public boolean nullCheck(RegistUser registUser) {
		if(registUser.getUserId() == null || registUser.getUserId().length() == 0) {
			return false;
		}else if(registUser.getPass() == null || registUser.getPass().length() == 0) {
			return false;
		}else if(registUser.getMail() == null || registUser.getMail().length() == 0) {
			return false;
		}else if(registUser.getName() == null || registUser.getName().length() == 0) {
			return false;
		}
		return true;
	}
}
