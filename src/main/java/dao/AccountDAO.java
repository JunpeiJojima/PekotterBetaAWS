package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Account;
import model.LoginEntity;
import model.RegistUser;

public class AccountDAO {
	
	private final String CREATE_TABLE_SQL = "create table account("
			+ "user_id varchar(15) not null"
			+ ",pass varchar(32) not null"
			+ ",mail varchar(256) not null"
			+ ",name varchar(50) not null"
			+ ",age integer not null"
			+ ",primary key (user_id));";
	
	// loginEntityのログイン情報を引数に入れてデータベースで照合する
	//  見つかれば登録情報をAccountインスタンスに、なければnullで返す
	public Account findByLogin(LoginEntity loginEntity) {
		
		Account account=null;
		
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
			Connection connection = dataSource.getConnection();
			Statement ps = connection.createStatement();
			
			//テーブルがあるかないかのif 文 なければ新規作成
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "account", null);
			boolean tableCheck;
			if (tableCheck = !tables.next()) {
				// Table does not exist
				ps.executeUpdate(CREATE_TABLE_SQL);
			} else if(!tableCheck) {
			
			String sql = 
					"select "
					+ "user_id"
					+ ", pass"
					+ ", mail"
					+ ", name"
					+ ", age "
					+ "from account "
					+ "where user_id = ? and pass = ?";
			PreparedStatement pStmt = connection.prepareStatement(sql);
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
		} catch (SQLException | NamingException e) {
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
			
			String sql = 
					"select "
					+ "user_id, "
					+ "pass, "
					+ "mail, "
					+ "name, "
					+ "age "
					+ "from account "
					+ "where user_id = ? or mail = ?";
			
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context) initialContext.lookup("java:/comp/env");
				DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
				Connection connection = dataSource.getConnection();
				Statement ps = connection.createStatement();
				
				//テーブルがあるかないかのif 文 なければ新規作成
				DatabaseMetaData dbm = connection.getMetaData();
				ResultSet tables = dbm.getTables(null, null, "account", null);
				boolean tableCheck;
				if (tableCheck = !tables.next()) {
					// Table does not exist
					ps.executeUpdate(CREATE_TABLE_SQL);
				} else if(!tableCheck) {
				
				PreparedStatement pStmt = connection.prepareStatement(sql);
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
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				return null;
			}
			// 見つかったユーザーまたはnullを返す
			return account;
		}
		
		public boolean newRegist(RegistUser registUser) {
			
			String sql = 
					"INSERT INTO account("
					+ "user_id, "
					+ "pass, "
					+ "mail, "
					+ "name, "
					+ "age)"
					+ " values(?,?,?,?,?) ";
			
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context) initialContext.lookup("java:/comp/env");
				DataSource dataSource = (DataSource) envContext.lookup("jdbc/pekotter");
				Connection connection = dataSource.getConnection();
				PreparedStatement pStmt = connection.prepareStatement(sql);
					
			pStmt.setString(1, registUser.getUserId());
			pStmt.setString(2, registUser.getPass());
			pStmt.setString(3, registUser.getMail());
			pStmt.setString(4, registUser.getName());
			pStmt.setInt(5, registUser.getAge());
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
			}catch (SQLException | NamingException e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
	

}
