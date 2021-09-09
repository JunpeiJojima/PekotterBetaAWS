package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 文字コード設定フィルタ
 */
//@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("CharacterEncodingFilter.doFilter() => start");
		
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		
		System.out.println("CharacterEncodingFilter.doFilter() => end");
	}

}
