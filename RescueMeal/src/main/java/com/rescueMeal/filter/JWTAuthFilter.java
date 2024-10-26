package com.rescueMeal.filter;

import com.rescueMeal.dao.UserDAO;
import com.rescueMeal.exceptionClasses.UserNotFoundException;
import com.rescueMeal.model.User;
import com.rescueMeal.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String jwt=authorizationHeader.substring(7);
        Long id=tokenService.extractId(jwt);
        if(id != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userDAO.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User is not present"));
            System.out.println(user.getAuthorities());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter( HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/auth/**");
    }
}
