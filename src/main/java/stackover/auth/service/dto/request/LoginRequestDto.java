package stackover.auth.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;

@Schema(description = "DTO с учетными данными пользователя")
public record LoginRequestDto(
        @Schema(description = "Электронная почта пользователя", example = "email@example.com")
        @Email
        String email,

        @Schema(description = "Пароль пользователя(должен быть зашифрован)", example = "bcrypt")
        String password
) {
}
