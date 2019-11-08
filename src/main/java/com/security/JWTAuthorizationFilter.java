package com.security;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, authorization");;
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");
		if(request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else if (request.getRequestURI().equals("login")) {
			//response.addHeader("Access-Control-Allow-Origin", "*");
			filterChain.doFilter(request, response);
			return;
		}
		else {
		String jwt = request.getHeader(SecurityParms.HEADE_NAME);
		if (jwt == null || !jwt.startsWith(SecurityParms.HEADER_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		JWTVerifier verifer = JWT.require(Algorithm.HMAC256(SecurityParms.SECRET)).build();
		DecodedJWT decodedJWT = verifer.verify(jwt.substring(SecurityParms.HEADER_PREFIX.length()));
		String usrename = decodedJWT.getSubject();
		
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usrename,null, new ArrayList<>());
		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request, response);
		}

		
	}

}
