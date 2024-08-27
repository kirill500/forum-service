package telran.java53.security.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dao.UserAccountRepository;
import telran.java53.accounting.model.Role;
import telran.java53.accounting.model.UserAccount;

@Component
@RequiredArgsConstructor
@Order(20)
public class AdminManagingRolesFilter implements Filter {
	final UserAccountRepository userAccountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndpoint(request.getMethod(), request.getServletPath())) {
			String principal = request.getUserPrincipal().getName();
			UserAccount userAccount = userAccountRepository.findById(principal).get();
			if (!userAccount.getRoles().contains(Role.ADMINISTRATOR)) {
				response.sendError(403, "You are not allowed to access this resource");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private boolean checkEndpoint(String method, String path) {
		return path.matches("/account/user/\\w+/role/\\w+");
	}

}