package stackover.auth.service.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для обновления токена")
public record RefreshTokenResponseDto(
        @Schema(description = "JWT токен для доступа", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        String accessToken,

        @Schema(description = "JWT токен для обновления", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        String refreshToken,

        @Schema(description = "Указание, заблокирован ли аккаунт", example = "false")
        boolean blocked
) {
}
