package entity;

import java.io.Serializable;

//つぶやきの情報持つJavaBeans
public class Tweet implements Serializable{
	private int id; 
	private String userName;
	private String text; 
	private String time;
	private String user_id;
	private int goodNum;
	
	public Tweet() {}
	public Tweet(String userName,String text,String time,String user_id) {
		this.setUserName(userName);
		this.setText(text);
		this.setTime(time);
		this.setUser_id(user_id);
	}
	public Tweet(int id,String userName,String text,String time, String user_id, int good) {
		this.setId(id);
		this.setUserName(userName);
		this.setText(text);
		this.setTime(time);
		this.setUser_id(user_id);
		this.setGoodNum(good);
	}
	
	
	public String getUserName() {
		return userName;
	}
	private void setUserName(String userName) {
		this.userName = userName;
	}
	public String getText() {
		return text;
	}
	private void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

}
