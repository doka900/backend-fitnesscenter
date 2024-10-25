package application.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService; // Use the custom implementation

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.headers().cacheControl().disable()
				.and()
				.cors() // Make sure CORS is enabled
				.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/user/register/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user/login/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user/verify-email/").permitAll()

				.antMatchers(HttpMethod.GET, "/api/user/trainers/").permitAll()

				.antMatchers(HttpMethod.GET, "/api/user/verify-email/{token}").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/verify-email/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/getAll/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/username/{username}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/checkUsername}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/checkEmail/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/user/{username}/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user/oldPasswordVerification/{username}/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/user/changePassword/{username}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/facility/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/facility/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/facility/{id}/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/facility/update/{id}/").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/facility/{id}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/facility/types/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/facilitySpace/types/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/facilitySpace/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/facilitySpace/{id}/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/facilitySpace/update/{id}/").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/facilitySpace/{id}/").permitAll()

				.antMatchers(HttpMethod.POST, "/api/program/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/program/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/program/{id}/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/program/update/{id}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/program/user/{id}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/program/user/{username}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/program/user/id/{id}/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/program/user/username/{username}/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/program/update/").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/program/{id}/").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/program/{programId}/user/{username}/").permitAll()


				.anyRequest().authenticated();

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
