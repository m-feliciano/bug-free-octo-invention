package br.com.feliciano.forum.security;

import br.com.feliciano.forum.domain.User;
import br.com.feliciano.forum.repository.UserRepository;
import br.com.feliciano.forum.security.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recoverToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid) {
            authenticateUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        Long userId = tokenService.getUserId(token);
        Optional<User> user = userRepository.findById(userId);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user, null, user.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }

}
