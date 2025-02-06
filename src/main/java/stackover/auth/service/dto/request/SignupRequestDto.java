package stackover.auth.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO для регистрации пользователя")
public record SignupRequestDto(
        @Schema(description = "Пароль пользователя(должен быть зашифрован)", example = "bcrypt")
        String password,

        @Schema(description = "Название роли пользователя", example = "User")
        String roleName,

        @Schema(description = "Электронная почта пользователя", example = "email@example.com")
        String email,

        @Schema(description = "Имя пользователя", example = "Анатолий Петров")
        String fullName,

        @Schema(description = "Город проживания пользователя", example = "Уфа")
        String city,

        @Schema(description = "Дата и время регистрации пользователя", example = "2025-01-31T01:01:01")
        LocalDateTime persistDateTime,

        @Schema(description = "Ссылка на личную страницу пользователя", example = "http://example.com/")
        String linkSite,

        @Schema(description = "Ссылка на профиль GitHub пользователя", example = "https://github.com/")
        String linkGitHub,

        @Schema(description = "Ссылка на профиль Вконтакте пользователя", example = "https://vk.com/")
        String linkVk,

        @Schema(description = "Информация о пользователе", example = "Пишу код на java")
        String about,

        @Schema(description = "Ссылка на изображение пользователя", example = "http://example.com/image.jpg")
        String imageLink,

        @Schema(description = "Никнейм пользователя", example = "Tolik")
        String nickname
) {
}
