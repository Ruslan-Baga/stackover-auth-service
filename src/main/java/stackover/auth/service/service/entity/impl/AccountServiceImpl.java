package stackover.auth.service.service.entity.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stackover.auth.service.exception.AccountAlreadyExistsException;
import stackover.auth.service.model.Account;
import stackover.auth.service.model.Role;
import stackover.auth.service.repository.AccountRepository;
import stackover.auth.service.repository.RoleRepository;
import stackover.auth.service.service.entity.AccountService;
import stackover.auth.service.service.entity.RoleService;

@Service
public class AccountServiceImpl extends AbstractServiceImpl<Account, Long> implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        super(accountRepository);
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account save(Account account) {
        if (accountRepository.existsAccountByEmail(account.getEmail())) {
            throw new AccountAlreadyExistsException("Аккаунт с email " + account.getEmail() + " уже существует");
        }
        Role role = roleService.getRoleByName(account.getRole().getName());
        if (role == null) {
            Role newRole = new Role();
            newRole.setName(account.getRole().getName());
            roleService.save(newRole);
            account.setRole(newRole);
        } else {
            account.setRole(role);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return super.save(account);
    }
}
