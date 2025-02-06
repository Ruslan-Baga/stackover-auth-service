package stackover.auth.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackover.auth.service.model.Account;

import javax.validation.constraints.Email;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByEmail(@Email String email);
    Account findByEmail(String username);
}
