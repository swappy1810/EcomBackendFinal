package com.example.roleBased.config;

import com.example.roleBased.serviceImpl.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static String CURRENT_USER= "";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;


//default filter method to filterchain by passing header and token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        String jwtToken = null;
        String email = null;
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);
//checking expiration of token
            try {
                email = jwtUtil.getUserNameFromToken(jwtToken);
                System.out.println(email);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt Token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt Token get expired");
            }
        }
        else {
            System.out.println("Jwt Token does not start with Bearer");
        }
//get username from authentication classes
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtService.loadUserByUsername(email);
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
    }
