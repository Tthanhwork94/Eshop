package com.eCommerce.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.eCommerce.util.SessionUtil;

@Component
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// session nam trong httpsession
		// httpsession nam trong httpservletRequest
		// ep kieu ServletRequest ve httpServletRequest
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session= (HttpSession) httpRequest.getSession();
		SessionUtil.validateCart(session);
		chain.doFilter(request, response);
	}

}
