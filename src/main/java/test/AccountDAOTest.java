package test;

import dao.AccountDAO;
import model.Account;
import model.LoginEntity;
import model.RegistUser;

public class AccountDAOTest {
	public static void main(String[] args) {
		testFindByLogin1(); //ユーザーが見つかる場合のテスト
		testFindByLogin2(); //ユーザーが見つからない場合のテスト
		
		testFindByIdMail1(); //ユーザーがいた場合のテスト
		testFindByIdMail2(); //ユーザーがいない場合のテスト
	}
	
	public static void testFindByLogin1() {
		LoginEntity loginEntity = new LoginEntity("joji","0418");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(loginEntity);
		if(result != null &&
			result.getUserId().equals("joji") &&
			result.getPass().equals("0418") &&
			result.getMail().equals("joji@peko.com") &&
			result.getName().equals("城島 順平") &&
			result.getAge() == 111) {
			System.out.println("testFindByLogin1 : 成功しました");
		}else {
			System.out.println(result.getUserId().length());
			System.out.println(result.getUserId().equals("joji"));
			System.out.println(result.getPass().equals("0418"));
			System.out.println(result.getMail().equals("joji@peko.com"));
			System.out.println(result.getName().equals("城島 順平"));
			System.out.println(result.getAge() == 111);
			
			System.out.println("testFindByLogin1 : 失敗しました");
		}
	}
	private static void testFindByLogin2() {
		LoginEntity loginEntity = new LoginEntity("joji","04185");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(loginEntity);
		if(result == null) {
			System.out.println("testFindByLogin2 : 成功しました");
		}else {
			System.out.println("testFindByLogin2 : 失敗しました");
		}
		
		System.out.println();
		System.out.println("---------------------------");
		System.out.println();
		
	}
	
	
	
	public static void testFindByIdMail1() {
		RegistUser registUser = new RegistUser("jojii","04185","joji@peko.com","じょうじま",130);
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByIdMail(registUser);
		if(result != null &&
			result.getUserId().equals("joji")) {
			System.out.println(result.getUserId());
			System.out.println(result.getPass());
			System.out.println(result.getMail());
			System.out.println(result.getName());
			System.out.println(result.getAge()) ;
			System.out.println("testFindByIdMail1 : 成功しました");
		}else {
			System.out.println(result.getUserId());
			System.out.println(result.getUserId().equals("joji"));
			System.out.println(result.getPass().equals("0418"));
			System.out.println(result.getMail().equals("joji@peko.com"));
			System.out.println(result.getName().equals("城島 順平"));
			System.out.println(result.getAge() == 111);
			
			System.out.println("testFindByIdMail1 : 失敗しました");
		}
	}
	private static void testFindByIdMail2() {
		RegistUser registUser = new RegistUser("joj","04185","jojima@peko.com","じょうじま",130);
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByIdMail(registUser);
		if(result == null) {
			System.out.println("testFindByIdMail2 : 成功しました");
		}else {
			System.out.println("testFindByIdMail2 : 失敗しました");
		}
	}
}
