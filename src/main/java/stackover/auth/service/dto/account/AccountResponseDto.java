package stackover.auth.service.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import stackover.auth.service.util.enums.RoleNumEnum;

import javax.validation.constraints.NotEmpty;

@Schema(description = "DTO для ответа с информацией об аккаунте")
public record AccountResponseDto(
        @Schema(description = "Уникальный идентификатор аккаунта", example = "1")
        Long id,

        @Schema(description = "Электронная почта аккаунта", example = "email@example.com")
        @NotEmpty
        String email,

        @Schema(description = "Роль аккаунта", example = "ADMIN")
        @NotEmpty
        RoleNumEnum role,

        @Schema(description = "Указание, активен ли аккаунт", example = "true")
        boolean enabled
) {
}
