package com.charlotte.kies.configuration;

import com.charlotte.kies.model.CustomOAuth2User;
import com.charlotte.kies.repository.UserRepository;
import com.charlotte.kies.security.GoogleTokenUtil;
import com.charlotte.kies.security.JwtRequestFilter;
import com.charlotte.kies.security.JwtTokenUtil;
import com.charlotte.kies.security.JwtUserDetailService;
import com.charlotte.kies.service.AuthService;
import com.charlotte.kies.service.CustomOAuth2UserService;
import com.charlotte.kies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    GoogleTokenUtil googleTokenUtil;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login()
//                .loginPage("/")
//                .userInfoEndpoint()
//                .userService(oauthUserService);
//
//        // removes 403 error
//        http.csrf().disable();
//
//        http.oauth2Login()
//                .loginPage("/")
//                .userInfoEndpoint()
//                .userService(oauthUserService)
//                .and()
//                .successHandler(new AuthenticationSuccessHandler() {
//
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                        Authentication authentication) throws IOException, ServletException {
//
//                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//
//                        userService.processOAuthPostLogin(oauthUser.getEmail());
//
////                        response.sendRedirect("/list");
//                    }
//                });
//    }



    @Autowired
    private CustomOAuth2UserService oauthUserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/**/private/**").authenticated()
//                .antMatchers("/vote").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }

}