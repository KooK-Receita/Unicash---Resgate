package io.swagger.utils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        HashSet<String> whiteList = new HashSet<>();
        whiteList.add("/v2");
        whiteList.add("/v2/");
        whiteList.add("/v2/api-docs");

        return whiteList.contains(path) || path.startsWith("/v2/webjars/springfox-swagger-ui/") || path.startsWith("/v2/swagger-ui.html")
                || path.startsWith("/v2/swagger-resources");
    }
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
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                unauthorize(response, request);
                return;
            }
        } else {
            unauthorize(response, request);
            return;
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // if token is valid configure Spring Security to manually set
            // authentication
//            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken(username, jwtToken, Arrays.asList(new SimpleGrantedAuthority("user"))));

            chain.doFilter(request, response);

        } else {
            unauthorize(response, request);
        }
    }

    private void unauthorize(HttpServletResponse response, HttpServletRequest request) throws IOException {
        logger.warn("Unauthorized: " + request.getRequestURI());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}