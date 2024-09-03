package telran.java53.security;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dao.UserAccountRepository;
import telran.java53.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class PasswordExpiryService {
	
	private final UserAccountRepository userRepository;
    private static final long PASSWORD_EXPIRY_DAYS = 90;

    public boolean isPasswordExpired(String userName) {
        UserAccount user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        LocalDateTime lastChanged = user.getPasswordLastChanged();
        return lastChanged != null 
        		&& ChronoUnit.DAYS.between(lastChanged, LocalDateTime.now()) > PASSWORD_EXPIRY_DAYS;
    }
}
