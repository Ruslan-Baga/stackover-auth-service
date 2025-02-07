package stackover.auth.service.service.dto.impl;

import org.springframework.stereotype.Service;
import stackover.auth.service.repository.dto.AccountDtoRepository;
import stackover.auth.service.dto.account.AccountResponseDto;
import stackover.auth.service.service.dto.AccountDtoService;
import stackover.auth.service.util.enums.RoleNumEnum;

import java.util.Optional;

@Service
public class AccountDtoServiceImpl implements AccountDtoService {
    private final AccountDtoRepository accountDtoRepository;

    public AccountDtoServiceImpl(AccountDtoRepository accountDtoRepository) {
        this.accountDtoRepository = accountDtoRepository;
    }

    @Override
    public Optional<AccountResponseDto> getAccountById(Long id) {
        return Optional.ofNullable(accountDtoRepository.getAccountById(id));
    }

    @Override
    public boolean checkExistByIdAndRole(Long accountId, RoleNumEnum role) {
        return accountDtoRepository.existsByIdAndRole(accountId, role);
    }

    @Override
    public boolean checkExistById(Long accountId) {
        return accountDtoRepository.existsById(accountId);
    }
}
