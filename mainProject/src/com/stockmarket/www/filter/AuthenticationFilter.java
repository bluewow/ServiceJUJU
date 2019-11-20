package com.stockmarket.www.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter{

	private static final String[] noAuthUrls = {
			//TODO
			"/card/search/search",
			"/card/trade/analysis", 
			"/card/board/news_board",
			};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String urlPath = httpRequest.getServletPath();
		boolean filterPass = false;
		
		if(session.getAttribute("loginId") == null) {
			//로그인 이전 권한 세팅
			for(String url : noAuthUrls) {
				// 1. /card 외에는 pass
				// 2. 1번 조건에 noAuthUrls path 는 pass
				if(!urlPath.substring(0, 5).equals("/card") || url.equals(urlPath)) {
					filterPass = true;
					break;
				}
			}
			
			if(filterPass == true) 
				chain.doFilter(request, response);
			else 
				httpRequest.getRequestDispatcher("/error/403.jsp").forward(httpRequest, httpResponse);
		} else {
			//로그인 이후
			chain.doFilter(request, response);
		}
	}
}
