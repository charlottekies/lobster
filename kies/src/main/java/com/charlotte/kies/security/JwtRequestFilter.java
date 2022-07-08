package com.charlotte.kies.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.charlotte.kies.model.GoogleUser;
import com.charlotte.kies.model.User;
import com.charlotte.kies.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUserDetailService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private UserDetails userdetails;
    @Autowired
    private GoogleTokenUtil googleTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                /** get user details from GoogleTokenUtil instead of JwtTokenUtil ***/
                username = googleTokenUtil.getUsernameFromToken(jwtToken);
//                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            } catch (GeneralSecurityException e) {
                System.out.println("Unable to verify google token");
                throw new RuntimeException(e);
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                setUserdetails(this.jwtUserDetailsService.loadUserByUsername(username));
            } catch(Exception e) {
                GoogleUser googleUser = googleTokenUtil.getGoogleUserFromToken(jwtToken);
                User user = new User();
                user.setEmail(googleUser.getEmail());
                user.setName(googleUser.getName());
                user.setFirstName(googleUser.getGiven_name());
                user.setLastName(googleUser.getFamily_name());
                user.setUsername(googleUser.getEmail());
                user.setRole("user");
                authService.loginGoogleUser(user);
                setUserdetails(this.jwtUserDetailsService.loadUserByUsername(username));
            }
            // if token is valid configure Spring Security to manually set
            // authentication
            // todo: validate the GOOGLE token with the userDetails
//        idToken = verifier.verify(jwtToken);)
            try {
                if (googleTokenUtil.validateToken(jwtToken, getUserdetails())) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            getUserdetails(), null, getUserdetails().getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (GeneralSecurityException e) {
                System.out.println("could not validate Google Token");
                throw new RuntimeException(e);
            }
        }
        chain.doFilter(request, response);
    }

    /*** getters and setters ***/

    public UserDetails getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(UserDetails userdetails) {
        this.userdetails = userdetails;
    }
}