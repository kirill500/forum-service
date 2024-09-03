package telran.java53.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordExpiryFilter extends OncePerRequestFilter{

	private final PasswordExpiryService passwordExpiryService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
    		FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && passwordExpiryService.isPasswordExpired(authentication.getName())) {
            response.sendRedirect("/account/change-password");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
