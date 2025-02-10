package stackover.auth.service.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stackover.auth.service.model.Account;
import stackover.auth.service.repository.AccountRepository;
import stackover.auth.service.security.CustomUserDetails;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("Аккаунт с почтой %s не найден".formatted(username));
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().getName().name());
        CustomUserDetails customUserDetails = new CustomUserDetails(account.getId(), account.getEmail(), account.getPassword(), List.of(authority));
        return customUserDetails;
    }
}
