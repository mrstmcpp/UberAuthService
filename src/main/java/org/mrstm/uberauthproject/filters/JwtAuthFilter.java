package org.mrstm.uberauthproject.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mrstm.uberauthproject.services.JwtService;
import org.mrstm.uberauthproject.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    /**
     * why?
     * we will attach this filter to any request nd all requests will be automatically mapped by this filter.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/api/v1/auth/validate" , HttpMethod.GET.name());
    //above matcher is for allowing rest requests such as signin nd signup nd stopping validate method to have a valid token

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("JwtToken")) {
                    token = cookie.getValue();
                }
            }
        }

        if(token == null) {
            //user haven't provided any token
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String email = jwtService.extractEmailFromToken(token);
        if(email != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtService.isTokenValid(token, email)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null);
                //written to manage authentication by user . so , we do not have to write by ourselves.
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                //web authentication details class is a converter . it acts as a bridge b/w servlet classes nd spring classes.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // security context will remember the userdetails so that we do not have to tell it everytime.
            }
        }
        System.out.println("hiting filters");
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        RequestMatcher matcher = new NegatedRequestMatcher(requestMatcher);
        return matcher.matches(request);
    }
}
