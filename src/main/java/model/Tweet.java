package model;

import java.io.Serializable;

//つぶやきの情報持つJavaBeans
public class Tweet implements Serializable{
	private int id; // ID
	private String userName;//ユーザー名
	private String text;    //つぶやき内容
	private String time; //つぶやき時間
	
	public Tweet() {}
	public Tweet(String userName,String text,String time) {
		this.setUserName(userName);
		this.setText(text);
		this.setTime(time);
	}
	public Tweet(int id,String userName,String text,String time) {
		this.setId(id);
		this.setUserName(userName);
		this.setText(text);
		this.setTime(time);
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

}
