
package com.inventiory.Api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author jonii
 */
@Component
@RequiredArgsConstructor
public class JwtAthuenticationFilter extends OncePerRequestFilter{
    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, 
                                    @NotNull HttpServletResponse response, 
                                    @NotNull FilterChain filterChain) 
                                    throws ServletException, IOException
    {
        final String authHeader = request.getHeader("Authorization") ;
        final String jwt;
        final String userEmail;
        
        if(authHeader ==null ||  !authHeader.startsWith("Bearer ") ){
            filterChain.doFilter(request, response);
            return;
        } 
        jwt= authHeader.substring(7);
        userEmail =jwtService.extractUsername(jwt);
        if(userEmail!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);
            
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( userDetails, 
                                    null,
                                    userDetails.getAuthorities());

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            
            filterChain.doFilter(request, response);
        }
        
                
        
    }
 
    
    
    
}
