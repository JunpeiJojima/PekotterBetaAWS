package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Tweet;

public class TweetDAO {
	
	File fName = new File("tweet.db");
	String AbPath = fName.getAbsolutePath();

	private final String SQL_PASS = "/sqlFile/TweetsData/tweets.db";
	private final String URL = "jdbc:sqlite:" + AbPath;
	private final String directoryPath = "/sqlFile/TweetsData";
	private final String CREATE_TABLE_SQL = "create table tweet(" + "id integer primary key autoincrement "
			+ ",name text not null" + ",text text not null" + ",time text not null" + ",user_id text not null);";

	private File file = new File(SQL_PASS);
	private File dirrectry = new File(directoryPath);

	public List<Tweet> findALL() {

		// もしフォルダが無ければ作成
		if (!file.exists()) {
			dirrectry.mkdirs();
		}

		List<Tweet> tweetList = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(URL);
				Statement ps = conn.createStatement()) {

			// テーブルがあるかないかのif 文 なければ新規作成
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "tweet", null);
			boolean tableCheck;
			if (tableCheck = !tables.next()) {
				// Table does not exist
				ps.execute(CREATE_TABLE_SQL);
			} else if (!tableCheck) {

				// select文の準備
				String sql = "SELECT ID,NAME,TEXT,TIME FROM tweet ORDER BY ID desc";
				// SQLの送信
				PreparedStatement pStmt = conn.prepareStatement(sql);
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tweetList;
	}

	public boolean create(Tweet tweet) {

		String sql = "INSERT INTO tweet(NAME,TEXT,TIME,USER_ID) values(?,?,?,?) ";

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection conNe = DriverManager.getConnection(URL);
				PreparedStatement pStmt = conNe.prepareStatement(sql)) {

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
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
