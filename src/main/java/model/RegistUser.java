package model;

// accountテーブルのレコードを表すEntity
public class RegistUser {
	private String userId;
	private String pass;
	private String mail;
	private String name;
	private int age;
	
	public RegistUser(String userId,String pass
					,String mail,String name,int age) {
		this.setUserId(userId);
		this.setPass(pass);
		this.setMail(mail);
		this.setName(name);
		this.setAge(age);
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
