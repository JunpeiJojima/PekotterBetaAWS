package test;

import model.Account;
import model.RegistCheckLogic;
import model.RegistUser;

public class RegistCheckTest {
	public static void main(String[] args) {
		testCheck1(); // チェック成功テスト（IDもメールも使われてない）
		testCheck2(); // チェック失敗テスト
		
	}
	private static void testCheck1() {
		RegistUser registUser = new RegistUser("jojii", "00418","jojii@peko.com","じょうじま",50);
		RegistCheckLogic bo = new RegistCheckLogic();
		Account account = bo.registCheck(registUser);
		if(account == null) {
			System.out.println("testExecute1 : 成功しました");
		}else {
			System.out.println("testExecute1 : 失敗しました");
		}
	}	

	private static void testCheck2() {
		RegistUser registUser = new RegistUser("jojii", "00418","joji@peko.com","じょうじま",50);
		RegistCheckLogic bo = new RegistCheckLogic();
		Account account = bo.registCheck(registUser);
		if(account != null) {
			System.out.println("testExecute2 : 成功しました");
		}else {
			System.out.println("testExecute2 : 失敗しました");
		}
		
	}

}
