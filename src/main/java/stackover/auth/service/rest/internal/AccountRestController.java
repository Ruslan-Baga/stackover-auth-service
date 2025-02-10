package stackover.auth.service.rest.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stackover.auth.service.exception.AccountExistException;
import stackover.auth.service.dto.account.AccountResponseDto;
import stackover.auth.service.service.dto.AccountDtoService;
import stackover.auth.service.util.enums.RoleNumEnum;

@Slf4j
@RestController
@Tag(name = "Account Controller", description = "Контроллер для управления аккаунтами пользователей.")
@RequestMapping("/api/internal/account")
public class AccountRestController {
    private final AccountDtoService accountDtoService;

    public AccountRestController(AccountDtoService accountDtoService) {
        this.accountDtoService = accountDtoService;
    }

    @Operation(summary = "Получить аккаунт по id", description = "Возвращает информацию об аккаунте по его id")
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id) {
        log.info("Запрос на получение аккаунта с id: {}", id);
        AccountResponseDto account = accountDtoService.getAccountById(id)
                .orElseThrow(() -> {
                    log.warn("Аккаунт с id {} не найден", id);
                    return new AccountExistException("Аккаунт с id %s не найден".formatted(id));
                });
        log.info("Аккаунт с id {} успешно найден: {}", id, account);
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Проверка существования аккаунта по id и роли",
            description = "Если аккаунт с заданным id и ролью найден, возвращает true, в противном случае — false")
    @RequestMapping(value = "/{id}/exists", method = RequestMethod.GET, params = "role")
    public boolean checkExistByIdAndRole(@PathVariable Long id, @RequestParam RoleNumEnum role) {
            log.info("Запрос на проверку существования аккаунта с id: {} и ролью: {}", id, role);
            boolean exists = accountDtoService.checkExistByIdAndRole(id, role);
        if (exists) {
            log.info("Аккаунт с id {} и ролью {} существует", id, role);
        } else {
            log.warn("Аккаунт с id {} и ролью {} не найден", id, role);
            return accountDtoService.checkExistByIdAndRole(id, role);
        }
        return exists;
    }
    @Operation(summary = "Проверка существования аккаунта по id",
            description = "Если аккаунт с заданным id найден, возвращает true, в противном случае — false")
    @RequestMapping(value = "/{id}/exists", method = RequestMethod.GET)
    public boolean checkExistByIdAndRole(@PathVariable Long id) {
        log.info("Запрос на проверку существования аккаунта с id: {}", id);
        boolean exists = accountDtoService.checkExistById(id);
        if (exists) {
            log.info("Аккаунт с id {} существует", id);
        } else {
            log.warn("Аккаунт с id {} не найден", id);
        }
        return exists;
    }
}
