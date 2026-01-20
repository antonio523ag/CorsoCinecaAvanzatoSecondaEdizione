package dev.antoniogrillo.esempiocinecaavanzato.filter;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter  extends OncePerRequestFilter {

    private final GestoreTokenService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader("Authorization");
        if(token!=null&&
                token.startsWith("Bearer ")&&
                SecurityContextHolder.getContext().getAuthentication()==null){
            Utente u=service.recuperaUtenteDaToken(token);
            if(u==null){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(upat);
        }
        filterChain.doFilter(request,response);
    }
}
