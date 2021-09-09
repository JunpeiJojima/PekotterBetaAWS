package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.LoginEntity;
import model.RegistUser;

public class AccountDAO {
	
	private final String jdbc_url = "jdbc:postgresql://localhost:5432/pekotter";
	private final String db_user = "postgres";
	private final String db_password = "Dega4prog";
	
	// loginEntityのログイン情報を引数に入れてデータベースで照合する
	//  見つかれば登録情報をAccountインスタンスに、なければnullで返す
	public Account findByLogin(LoginEntity loginEntity) {
		Account account=null;
		
		// データベースへ接続
		try(Connection conn =DriverManager.getConnection(jdbc_url, db_user, db_password)){
			
			//select文を準備
			String sql = 
					"select user_id, pass, mail, name, age from account where user_id = ? and pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginEntity.getUserId());
			pStmt.setString(2, loginEntity.getPass());
			
			// select文を実行し、結果票を取得
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
			
			// データベースへ接続
			try(Connection conn =DriverManager.getConnection(jdbc_url, db_user, db_password)){
				
				//select文を準備
				String sql = 
						"select user_id, pass, mail, name, age from account where user_id = ? or mail = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, registUser.getUserId());
				pStmt.setString(2, registUser.getMail());
				
				// select文を実行し、結果票を取得
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
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			// 見つかったユーザーまたはnullを返す
			return account;
		}
		
		//
		public boolean newRegist(RegistUser registUser) {
			//postgreSQLへの接続
			try (Connection con =DriverManager.getConnection(jdbc_url, db_user, db_password)){
					
			//select文の実行
			String sql = 
					"INSERT INTO account(user_id, pass, mail, name, age) values(?,?,?,?,?) ";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			//INSERTの??に使用する値を設定する
			pStmt.setString(1, registUser.getUserId());
			pStmt.setString(2, registUser.getPass());
			pStmt.setString(3, registUser.getMail());
			pStmt.setString(4, registUser.getName());
			pStmt.setInt(5, registUser.getAge());
			
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
