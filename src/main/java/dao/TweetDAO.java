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

import entity.Tweet;

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
						+ ",USER_ID "
						+ ",GOOD_NUM "
						+ "FROM tweet ORDER BY ID desc";
				PreparedStatement pStmt = connection.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("ID");
					String userName = rs.getString("NAME");
					String text = rs.getString("TEXT");
					String time = rs.getString("TIME");
					String user_id = rs.getString("USER_ID");
					int good = rs.getInt("GOOD_NUM");
					Tweet tweet = new Tweet(id, userName, text, time,user_id,good);
					tweetList.add(tweet);
				}
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return null;
		}
		return tweetList;
	}
	
	public Tweet findTweet(int tweet_id) {

		Tweet tweet = new Tweet();

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();

				String sql = "SELECT ID"
						+ ",NAME"
						+ ",TEXT"
						+ ",TIME "
						+ ",USER_ID "
						+ ",GOOD_NUM "
						+ "FROM tweet WHERE ID = ?";
				PreparedStatement pStmt = connection.prepareStatement(sql);
				pStmt.setInt(1, tweet_id);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("ID");
					String userName = rs.getString("NAME");
					String text = rs.getString("TEXT");
					String time = rs.getString("TIME");
					String user_id = rs.getString("USER_ID");
					int good = rs.getInt("GOOD_NUM");
					Tweet setTweet = new Tweet(id, userName, text, time,user_id, good);
					tweet = setTweet;
				
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return null;
		}
		return tweet;
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

			pStmt.setString(1, tweet.getUserName());
			pStmt.setString(2, tweet.getText());
			pStmt.setString(3, tweet.getTime());
			pStmt.setString(4, tweet.getUser_id());

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
	public boolean goodNum(int id) {

		String sql = "UPDATE tweet SET good_num = (good_num + 1) where id = ?";

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);

			pStmt.setInt(1, id);

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
	public boolean edit(String text ,int id) {

		String sql = "UPDATE tweet SET text = ? where id = ?";

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);

			pStmt.setString(1, text);
			pStmt.setInt(2, id);

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
	public boolean delete(int id) {
		
		String sql = "DELETE FROM tweet WHERE id = ?";
		
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
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
