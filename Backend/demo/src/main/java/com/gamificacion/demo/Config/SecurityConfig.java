package com.gamificacion.demo.Config;

import static org.mockito.ArgumentMatchers.nullable;

import java.util.Collections;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Repository.IUserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
	
	@Autowired
	private final JwtFilter jwtFilter;
	
	@Autowired
	private final IUserRepository userRepository;
	
	
	
	public SecurityConfig(JwtFilter jwtFilter,IUserRepository userRepository) {
		super();
		this.jwtFilter = jwtFilter;
		this.userRepository = userRepository;
	}




	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http)  throws Exception{
		http.cors().and().csrf().disable().
		authorizeRequests().
		antMatchers("/**/auth/**","/**/user/user","/**/rol/all","/**/correo/**").
		//antMatchers("/**").
		permitAll().
		anyRequest().
		authenticated().
		and().
		sessionManagement().
		sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().				
		authenticationProvider(authenticationProvider()).
		addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.getAllowedOriginPatterns();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(java.util.Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(java.util.Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws UsernameNotFoundException{		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Usuario u = userRepository.findByCorreo(username);				
				if(u != null ) {
					return new User(u.getCorreo(), u.getPassword(), Collections.singleton(new SimpleGrantedAuthority(u.getRol().getNombre())));
				}		
				throw new UsernameNotFoundException("user Not found");				
			}
		};
	}
	
	

}
