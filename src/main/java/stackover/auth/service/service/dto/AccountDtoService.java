package stackover.auth.service.service.dto;

import stackover.auth.service.dto.account.AccountResponseDto;
import stackover.auth.service.util.enums.RoleNumEnum;

import java.util.Optional;

public interface AccountDtoService {
    Optional<AccountResponseDto> getAccountById(Long id);

    boolean checkExistByIdAndRole(Long accountId, RoleNumEnum role);
    boolean checkExistById(Long accountId);

}
