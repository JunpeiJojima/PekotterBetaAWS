package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Tweet;

public class TweetDAO {
	
	File fName = new File("tweet.db");
	String AbPath = fName.getAbsolutePath();

	private final String CREATE_TABLE_SQL = "create table tweet("
			+ "id serial "
			+ ",name varchar(50) not null" 
			+ ",text varchar(140) not null" 
			+ ",time varchar(16) not null" 
			+ ",user_id varchar(15) not null"
			+ ",primary key (id));";

	public List<Tweet> findALL() {

		List<Tweet> tweetList = new ArrayList<>();

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();
			Statement ps = connection.createStatement();

			// テーブルがあるかないかのif 文 なければ新規作成
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "tweet", null);
			boolean tableCheck;
			if (tableCheck = !tables.next()) {
				// Table does not exist
				ps.executeUpdate(CREATE_TABLE_SQL);
			} else if (!tableCheck) {

				String sql = "SELECT ID"
						+ ",NAME"
						+ ",TEXT"
						+ ",TIME "
						+ "FROM tweet ORDER BY ID desc";
				// SQLの送信
				PreparedStatement pStmt = connection.prepareStatement(sql);
				// 結果表をrsに取得
				ResultSet rs = pStmt.executeQuery();

				// 結果をListに格納
				while (rs.next()) {
					int id = rs.getInt("ID");
					String userName = rs.getString("NAME");
					String text = rs.getString("TEXT");
					String time = rs.getString("TIME");
					// ↑DBから各値を取得し、↓つぶやきインスタンスのフィールドに設定する
					Tweet tweet = new Tweet(id, userName, text, time);
					tweetList.add(tweet);
				}
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return null;
		}
		return tweetList;
	}

	public boolean create(Tweet tweet) {

		String sql = "INSERT INTO tweet("
				+ "NAME"
				+ ",TEXT"
				+ ",TIME"
				+ ",USER_ID) "
				+ "values(?,?,?,?) ";

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);

			// INSERTの??に使用する値を設定する
			pStmt.setString(1, tweet.getUserName());
			pStmt.setString(2, tweet.getText());
			pStmt.setString(3, tweet.getTime());
			pStmt.setString(4, tweet.getUser_id());

			// INSERT文を実行（resultには追加された行数が入る）
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
