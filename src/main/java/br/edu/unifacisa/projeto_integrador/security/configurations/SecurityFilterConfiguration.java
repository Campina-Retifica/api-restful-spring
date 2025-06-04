package br.edu.unifacisa.projeto_integrador.security.configurations;

import br.edu.unifacisa.projeto_integrador.token.TokenService;
import br.edu.unifacisa.projeto_integrador.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilterConfiguration extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = recoverToken(request);

            if (token != null) {
                var authorities = checkAuthorizations(token);
                SecurityContextHolder.getContext().setAuthentication(authorities);
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }

    private UsernamePasswordAuthenticationToken checkAuthorizations(String token) {
        var subject = tokenService.verify(token);
        var user = userRepository.findByUsername(subject);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}