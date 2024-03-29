package ro.spotify.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //in authHeader, we extract de Authorization Header
        final String authHeader = request.getHeader("Authorization");

        //if our authHeader is null or its name doesn't start with Bearer, we call the next filter
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response); //call next filter
            return;
        }

        //we extract de jwtToken from authHeader
        final String jwtToken = authHeader.substring(7);
        //we extract de userEmail from jwtToken using a service
        final String userEmail = jwtService.extractEmail(jwtToken);

        //we check if the userEmail is not null and if the user is NOT authenticated
        //if is authenticated then we skip the verification part
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

            if (jwtService.isTokenValid(jwtToken)){

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userEmail,null,jwtService.getAuthorities(jwtToken)
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request,response);
        }
    }
}
