package stackover.auth.service.rest.out;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stackover.auth.service.converters.SignupDtoConverter;
import stackover.auth.service.converters.SignupToProfileConverter;
import stackover.auth.service.dto.profile.ProfilePostDto;
import stackover.auth.service.dto.request.LoginRequestDto;
import stackover.auth.service.dto.response.AuthResponseDto;
import stackover.auth.service.dto.token.RefreshTokenResponseDto;
import stackover.auth.service.feign.ProfileServiceClient;
import stackover.auth.service.model.Account;
import stackover.auth.service.dto.request.SignupRequestDto;
import stackover.auth.service.service.security.AuthService;
import stackover.auth.service.service.entity.AccountService;
import stackover.auth.service.service.entity.impl.AccountServiceImpl;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authorization", description = "Эндпоинты для аутентификации пользователей и управления аккаунтами")
public class AuthRestController {
    private final SignupToProfileConverter signupToProfileConverter;
    private final AccountService accountService;
    private final SignupDtoConverter signupDtoConverter;
    private final AuthService authService;
    private final ProfileServiceClient profileServiceClient;

    public AuthRestController(AccountServiceImpl accountService, SignupDtoConverter signupDtoConverter, AuthService authService, ProfileServiceClient profileServiceClient,
                              SignupToProfileConverter signupToProfileConverter) {
        this.accountService = accountService;
        this.signupDtoConverter = signupDtoConverter;
        this.authService = authService;
        this.profileServiceClient = profileServiceClient;
        this.signupToProfileConverter = signupToProfileConverter;
    }

    @Operation(summary = "Регистрация нового пользователя", description = "Этот эндпоинт позволяет зарегистрировать нового пользователя, предоставив необходимые данные для аккаунта.")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequestDto signupRequestDto) {
        log.info("Запрос на регистрацию аккаунта с email: {}", signupRequestDto.email());
        Account account = signupDtoConverter.signupDtoToAccount(signupRequestDto);
        accountService.save(account);
        log.info("Аккаунта с email: {}, зарегистрирован", signupRequestDto.email());
        ProfilePostDto profilePostDto = signupToProfileConverter.toProfilePostDto(signupRequestDto, account.getId());
        log.info("Профиль: {}",profilePostDto);
        ResponseEntity<Void> response = profileServiceClient.createProfile(profilePostDto);
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Ошибка при создании профиля для аккаунта с email: {}. Код ошибки: {}", signupRequestDto.email(), response.getStatusCode());
            return ResponseEntity.status(response.getStatusCode()).build();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Аутентификация пользователя", description = "Этот эндпоинт выполняет аутентификацию пользователя и возвращает токен доступа.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Запрос аутентификации пользователя с email: {}",loginRequestDto.email());
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @Operation(summary = "Обновление токена", description = "Этот эндпоинт позволяет обновить истекший токен, предоставив старый токен для получения нового.")
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refresh(@RequestBody String token) {
        log.info("Запрос на обновление токена");
        RefreshTokenResponseDto response = authService.refreshToken(token);
        return ResponseEntity.ok(response);
    }
}
