package test;

import model.Account;
import model.LoginEntity;
import model.LoginLogicBO;

public class LoginLogicBOTest {
	public static void main(String[] args) {
		testExecute1(); // ログイン成功テスト
		testExecute2(); // ログイン失敗テスト
		
	}
	private static void testExecute1() {
		LoginEntity loginEntity = new LoginEntity("joji", "0418");
		LoginLogicBO bo = new LoginLogicBO();
		Account account = bo.execute(loginEntity);
		if(account != null) {
			System.out.println("testExecute1 : 成功しました");
		}else {
			System.out.println("testExecute1 : 失敗しました");
		}
	}	

	private static void testExecute2() {
		LoginEntity loginEntity = new LoginEntity("joji", "04185");
		LoginLogicBO bo = new LoginLogicBO();
		Account account = bo.execute(loginEntity);
		if(!(account != null)) {
			System.out.println("testExecute2 : 成功しました");
		}else {
			System.out.println("testExecute2 : 失敗しました");
		}
		
	}

}
