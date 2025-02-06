package stackover.auth.service.dto.profile;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO для создания профиля")
public record ProfilePostDto(
        @Schema(description = "Уникальный идентификатор аккаунта", example = "1")
        Long accountId,

        @Schema(description = "Электронная почта пользователя", example = "email@example.com")
        String email,

        @Schema(description = "Полное имя пользователя", example = "Анатолий Петров")
        String fullName,

        @Schema(description = "Город пользователя", example = "Уфа")
        String city,

        @Schema(description = "Дата создания профиля", example = "2025-01-31T01:01:01")
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