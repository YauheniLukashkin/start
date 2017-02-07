package com.library.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.library.service.impl.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*private settings*/
		http.
		authorizeRequests()
			.antMatchers("/", "/start", "/registration/**").permitAll()
			.antMatchers("/librarian/**").access("hasRole('LIBRARIAN')")
			.antMatchers("/reader/**").access("hasRole('READER')")
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").permitAll()
		.and();
	}

	@Autowired
	private UserService userService;
		@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
}
