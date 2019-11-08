package com.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.AppUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		AppUser appuser = null;
		try {
			appuser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appuser.getUsername(), appuser.getPassword()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("problem req cont " +e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		User user = (User) authResult.getPrincipal();
		
		String jwt= JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+ SecurityParms.EXPERATION))
				.sign(Algorithm.HMAC256(SecurityParms.SECRET));
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter()
                .print("{\"email\": " + "\"" + user.getUsername() + "\", \"token\": " + "\"" + jwt + "\"}");
        response.getWriter()
                .flush();

		super.successfulAuthentication(request, response, chain, authResult);
	}

}
