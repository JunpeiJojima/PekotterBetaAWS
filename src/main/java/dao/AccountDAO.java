package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Account;
import model.LoginEntity;
import model.RegistUser;

public class AccountDAO {
	
	private final String SQL_PASS = "./sqlFile/AccountsData/accounts.db"; 
	private final String URL = "jdbc:sqlite:./sqlFile/AccountsData/accounts.db";
	private final String directoryPath = "./sqlFile/AccountsData";
	private final String CREATE_TABLE_SQL = "create table account("
			+ "user_id text not null primary key"
			+ ",pass text not null"
			+ ",mail text not null"
			+ ",name text not null"
			+ ",age integer not null);";
	
	private File file = new File(SQL_PASS);
	private File dirrectry = new File(directoryPath);
		
	// loginEntityのログイン情報を引数に入れてデータベースで照合する
	//  見つかれば登録情報をAccountインスタンスに、なければnullで返す
	public Account findByLogin(LoginEntity loginEntity) {
		
		Account account=null;
		
		dirCheck();
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(URL);
				Statement ps = conn.createStatement()){
			
			//テーブルがあるかないかのif 文 なければ新規作成
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "account", null);
			boolean tableCheck;
			if (tableCheck = !tables.next()) {
				// Table does not exist
				ps.execute(CREATE_TABLE_SQL);
			} else if(!tableCheck) {
			
			String sql = 
					"select user_id, pass, mail, name, age from account where user_id = ? and pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginEntity.getUserId());
			pStmt.setString(2, loginEntity.getPass());
			
			ResultSet rs = pStmt.executeQuery();
			
			// 一致したユーザーが存在した場合
			// そのユーザーを表すAccountインスタンスを作成
			if(rs.next()) {
				// 結果票からデータを取得
				String userId = rs.getString("user_id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				account = new Account(userId,pass,mail,name,age);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// 見つかったユーザーまたはnullを返す
		return account;
	}

	
	// RegistUserの新規登録情報を引数に入れて、IDとmailが使われてないかデータベースで照合する
		//  見つかれば既存の登録情報をAccountインスタンスに、なければnullで返す
		public Account findByIdMail(RegistUser registUser) {
			Account account=null;
			
			dirCheck();
			
			String sql = 
					"select user_id, pass, mail, name, age "
					+ "from account where user_id = ? or mail = ?";
			
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			try(Connection conNe = DriverManager.getConnection(URL);
					Statement ps = conNe.createStatement()){
				
				//テーブルがあるかないかのif 文 なければ新規作成
				DatabaseMetaData dbm = conNe.getMetaData();
				ResultSet tables = dbm.getTables(null, null, "account", null);
				boolean tableCheck;
				if (tableCheck = !tables.next()) {
					// Table does not exist
					ps.execute(CREATE_TABLE_SQL);
				} else if(!tableCheck) {
				
				PreparedStatement pStmt = conNe.prepareStatement(sql);
				pStmt.setString(1, registUser.getUserId());
				pStmt.setString(2, registUser.getMail());
				
				ResultSet rs = pStmt.executeQuery();
				
				// 一致したユーザーが存在した場合
				// そのユーザーを表すAccountインスタンスを作成
				if(rs.next()) {
					// 結果票からデータを取得
					String userId = rs.getString("user_id");
					String pass = rs.getString("pass");
					String mail = rs.getString("mail");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					account = new Account(userId,pass,mail,name,age);
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			// 見つかったユーザーまたはnullを返す
			return account;
		}
		
		public boolean newRegist(RegistUser registUser) {
			
			String sql = 
					"INSERT INTO account(user_id, pass, mail, name, age)"
					+ " values(?,?,?,?,?) ";
			
			try (Connection conNe = DriverManager.getConnection(URL);
					PreparedStatement pStmt = conNe.prepareStatement(sql)){
					
			pStmt.setString(1, registUser.getUserId());
			pStmt.setString(2, registUser.getPass());
			pStmt.setString(3, registUser.getMail());
			pStmt.setString(4, registUser.getName());
			pStmt.setInt(5, registUser.getAge());
			
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
	
		private void dirCheck() {
			//フォルダが無ければ作成する処理
			if (!file.exists()){
				dirrectry.mkdirs();
			}
		}

}
