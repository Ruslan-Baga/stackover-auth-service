package stackover.auth.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для ответов с авторизованным аккаунтом")
public record AuthResponseDto(
        @Schema(description = "Уникальный идентификатор аккаунта", example = "1")
        Long accountId,

        @Schema(description = "Электронная почта пользователя", example = "email@example.com")
        String email,

        @Schema(description = "JWT токен для доступа", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        String accessToken,

        @Schema(description = "Время жизни токена в секундах", example = "3600")
        Long expiresIn
) {
}
