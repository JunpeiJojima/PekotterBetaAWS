package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constant;
import model.Account;

/**
 * 認証フィルタ
 *
 */
//@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();
		

		uri = uri.replaceFirst("/FlareGynecologyClinic", "");
		

		if (uri.startsWith("/public/") || uri.startsWith("/static/")) {
			//ログイン認証しない
			
			chain.doFilter(request, response);
			
		} else {
			//ログイン認証する
			HttpSession session = httpRequest.getSession();
			Account accountEntity = (Account) session.getAttribute(Constant.SESSION_KEY_LOGIN_USER);
			System.out.println("ログイン認証する");
			
			if (accountEntity == null) {
				session.setAttribute("REQUEST_URL", uri);
				//ログインしていない => ログイン画面に遷移
				String contextPath = httpRequest.getContextPath();
				httpResponse.sendRedirect(contextPath + "/public/LoginServlet");
				System.out.println("ログインしていない => ログイン画面に遷移");
				return;
			
			}

			//ログインしている   => 次のサーブレット、JSPへ遷移する
			chain.doFilter(request, response);
			System.out.println("ログインしている   => 次のサーブレット、JSPへ遷移する");
		}
	}

}
