package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tweet;

public class TweetDAO {
	
	private final String url = "jdbc:postgresql://localhost:5432/pekotter";
	private final String user = "postgres";
	private final String password = "Dega4prog";
	
	public List<Tweet> findALL(){
		List<Tweet> tweetList = new ArrayList<>();
			
			//postgreSQLへの接続
		try (Connection con =DriverManager.getConnection(url, user, password)){
		
		//select文の準備
		String sql = 
			"SELECT ID,NAME,TEXT,TIME FROM tweet ORDER BY ID desc";
		//SQLの送信
		PreparedStatement pStmt = con.prepareStatement(sql);
		//結果表をrsに取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果をListに格納
		while(rs.next()) {
			int id = rs.getInt("ID");
			String userName = rs.getString("NAME");
			String text = rs.getString("TEXT");
			String time = rs.getString("TIME");
			//↑DBから各値を取得し、↓つぶやきインスタンスのフィールドに設定する
			Tweet tweet = new Tweet(id,userName,text,time);
			tweetList.add(tweet);
		}
		
		//クラスフォーマットでエラー
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tweetList;
	}

	public boolean create(Tweet tweet) {
		//postgreSQLへの接続
		try (Connection con =DriverManager.getConnection(url, user, password)){
				
		//select文の実行
		String sql = 
				"INSERT INTO tweet(NAME,TEXT,TIME) values(?,?,?) ";
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		//INSERTの??に使用する値を設定する
		pStmt.setString(1, tweet.getUserName());
		pStmt.setString(2, tweet.getText());
		pStmt.setString(3, tweet.getTime());
		
		// INSERT文を実行（resultには追加された行数が入る）
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
