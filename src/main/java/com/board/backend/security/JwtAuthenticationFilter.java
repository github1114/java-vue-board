package com.board.backend.security;

import com.board.backend.domain.User.User;
import com.board.backend.exception.AuthException;
import com.board.backend.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtTokenUtil jwtTokenUtil;
    private static final String prefix = "Bearer ";
    private static final String headerName = "Authorization";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.jwtTokenUtil= jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = getAuthentication(request);
        if (authentication != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request)  {

        String authorizationHeader = request.getHeader(headerName);
        if (authorizationHeader == null) {
            return null;
        }

        String token = authorizationHeader.substring(prefix.length());
        Claims claims;
        try {
            claims = jwtTokenUtil.getClaims(token);
        } catch (JwtException e) {
            System.out.println(e.getMessage());
            throw new AuthException.MalformedJwt(token);
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        String role = (String) claims.get("role");
        roles.add(new SimpleGrantedAuthority("ROLE_" + role));

        return new UsernamePasswordAuthenticationToken(new User(claims), null, roles);
    }

}