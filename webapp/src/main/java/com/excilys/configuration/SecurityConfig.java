package com.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;

import com.excilys.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
		auth.inMemoryAuthentication().withUser("usertest").password(bCryptPasswordEncoder().encode("password")).roles("USER");
		auth.inMemoryAuthentication().withUser("admintest").password(bCryptPasswordEncoder().encode("password")).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable();
		
		http
		.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/dashboard").access("hasAnyRole('USER', 'ADMIN')")
			.antMatchers("/editComputer", "/addComputer").access("hasRole('ADMIN')");
			
		
		http
		.authorizeRequests().and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/j_spring_security_check")
			.defaultSuccessUrl("/dashboard")
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll();
		
		http
		.authorizeRequests().and().exceptionHandling().accessDeniedPage("/WEB-INF/views/403.jsp");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userService);
	    authProvider.setPasswordEncoder(bCryptPasswordEncoder());
	    return authProvider;
	}
	
    @Bean
    DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint bauth = new DigestAuthenticationEntryPoint();
        bauth.setRealmName("Digest WF Realm");
        bauth.setKey("MySecureKey");
        return bauth;
    }
}
