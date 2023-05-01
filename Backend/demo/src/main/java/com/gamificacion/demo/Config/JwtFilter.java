package com.gamificacion.demo.Config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Repository.IUserRepository;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private IUserRepository userRepository;


	public JwtFilter(JwtUtils jwtUtils, IUserRepository userRepository) {
		super();
		this.jwtUtils = jwtUtils;
		this.userRepository = userRepository;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String autHeader = request.getHeader("Authorization");
		final String userEmail;
		final String jwToken;		
		request.getHeaderNames();
		if(autHeader == null || !autHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwToken = autHeader.substring(7);
		userEmail = jwtUtils.extraerCorreo(jwToken);
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			Usuario u = userRepository.findByCorreo(userEmail);
			UserDetails usuario = new User(u.getCorreo(), u.getPassword(),Collections.singleton(new SimpleGrantedAuthority(u.getRol().getNombre())) ); 
			if(jwtUtils.isTokenValid(jwToken, usuario)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario,usuario.getPassword(),usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authToken);				
			}
		}
		filterChain.doFilter(request, response);
	}

}
