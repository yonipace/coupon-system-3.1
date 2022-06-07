package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.login.LoginManagerInterface.ClientType;
import app.core.utilities.JwtUtil;

public class ClientFilter implements Filter {

	private JwtUtil jwtUtil;
	private ClientType client;

	public ClientFilter(JwtUtil jwtUtil, ClientType client) {
		this.jwtUtil = jwtUtil;
		this.client = client;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String token = request.getHeader("token");

		try {

			if (jwtUtil.extractClientType(token).equals(this.client)) {
				chain.doFilter(request, response);
			}
			// if not logged in - block the request
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not logged in");

		} catch (

		Exception e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());

		}
	}
}