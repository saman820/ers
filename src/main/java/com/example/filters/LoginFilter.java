package com.example.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servlet.RequestDispatcher;

@WebFilter("/loginFilter")
public class LoginFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		System.out.println(uri);
		String session = (String) req.getSession().getAttribute("userName");
		
//		if (session == null && !(uri.endsWith("index.html") 
//		|| uri.endsWith("home.ers")) && !(uri.endsWith("html") || uri.endsWith("ers")
//		)){ res.sendRedirect("index.html"); }else{ chain.doFilter(request, response);
//		}
		if (session == null 
				&& !(uri.endsWith("index.html")) 
				&& !(uri.endsWith("home.ers"))
				) {
			res.sendRedirect("/ers/logout.ers");
		}else {
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// we can close resources here
	}
}
