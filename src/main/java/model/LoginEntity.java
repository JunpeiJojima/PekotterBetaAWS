package model;

// ログインで入力された情報
public class LoginEntity {
	
	private String userId;
	private String pass;
	public LoginEntity(String userId,String pass) {
		this.setUserId(userId);
		this.setPass(pass);
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
