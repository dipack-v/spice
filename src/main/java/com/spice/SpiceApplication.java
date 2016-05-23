package com.spice;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spice.dao.repository.BankRepository;
import com.spice.dao.repository.ContactRepository;
import com.spice.dao.repository.entity.AddressEntity;
import com.spice.dao.repository.entity.BankEntity;
import com.spice.dao.repository.entity.ContactEntity;

@SpringBootApplication
public class SpiceApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SpiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ContactRepository contactRepository, BankRepository bankRepository) {
		return args -> {

			ContactEntity contact = new ContactEntity(1L, 
					"UST Global", 
					"1010", 
					"Public", 
					 new SimpleDateFormat("dd/MM/yyyy").parse("25/05/1985"), 
					"9995419171", 
					"deepak@test.com"
					);
			contact.setAddresses(new HashSet<AddressEntity>(){{
				add(new AddressEntity(1L, "Bhavani", "Technopark", "Trivandrum", "Kerala", "695581", "IN", "COMM"));
			}});
			
			contactRepository.save(contact);
			
			BankEntity bank = new BankEntity(1L, "SBI", "Technopark", "Trivandrum","India", "01010", "67583354", "4234234", "645654", "INR");
			bankRepository.save(bank);
			
			
		};
	}
	
}


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("spice").password("spice").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic()
        .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        .and() 
        .authorizeRequests()
            .antMatchers(
            		"/assets/**",
                    "/css/**",
                    "/img/**",
                    "/js/**",
                    "/lib/**",
                    "/tpl/**",
                    "/").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .successHandler(authSuccessHandler)
            .failureHandler(authFailureHandler)
        .and()
	        .logout().permitAll()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
	        .clearAuthentication(true)
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID", "XSRF-TOKEN")
	        .logoutSuccessHandler(logoutSuccessHandler)
	        .and()
	        .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
            .sessionManagement().disable()
            //.maximumSessions(1)
         ;
           
    }
}


/**
 * Custom filter that will send the cookie. 
 * Browser wants the cookie name to be "XSRF-TOKEN" and Spring Security provides it as a request attribute, 
 * so we just need to transfer the value from a request attribute to a cookie:
 */
class CsrfHeaderFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain)throws ServletException, IOException {
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	    if (csrf != null) {
	    	Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
	    	String token = csrf.getToken();
	    	if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
	    		cookie = new Cookie("XSRF-TOKEN", token);
	    		cookie.setPath("/");
	    		//cookie.setSecure(true);
	    		response.addCookie(cookie);
	    	}
	    }
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	    filterChain.doFilter(request, response);
	}
}




@Component
class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

    private final ObjectMapper mapper;

    @Autowired
    AuthSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      

        LOGGER.info(userDetails.getUsername() + " got is connected ");

        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, userDetails.getUsername());
        writer.flush();
    }
}

@Component
class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter writer = response.getWriter();
        writer.write(exception.getMessage());
        writer.flush();
    }
}

@Component
class HttpLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
    
    	/*for(Cookie c: request.getCookies()){
    		c.setMaxAge(0);
    	}
    	request.getSession().invalidate();*/
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
}


@Component
class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}





