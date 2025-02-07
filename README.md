# StackoverKata - Auth-Service

# Настройка и запуск проекта с заданным профилем

## Профили
Проект поддерживает два профиля:
- local — для локальной разработки.
- dev — для разработки на удаленном сервере.

## Настройка
1. При необходимости заменить настройки БД в файле **db.env**

2. Определить файл **db.env** в переменных окружения:
	вкладка **Run** -> **Edit Configurations** -> (если Environment Variables и VM Options не открыты -> Modify options**(1)**)

![](src/main/resources/static/images/git_tutor/app_profiles.png)

3. В поле **VM Options (2)** ставим значение **-Dspring.profiles.active={профиль}**, где {профиль} - local или dev 

4. В поле **Environment Variables (3)** указываем путь к нашему файлу **db.env**

5. Apply

# Авторизация с помощью Postman

Перед запуском проекта необходимо указать секретный ключ для JWT-аутентификации(в auth-service и gateway). В Environments Variables добавьте:
JWT_SECRET → "Секретный ключ"(укажите ваш ключ, минимальная длина: 256 бит).

## Получение Access Token
1. Для авторизации отправьте запрос на POST /api/auth/login. В теле запроса укажите ваши данные(email и пароль)
2. В ответе вы получите access token, который будет использоваться для дальнейших запросов
   ![](src/main/resources/static/images/git_tutor/authorization.png)

## Тестирование API с Access Token
3. Для использования полученного токена в дальнейших запросах к защищенным эндпоинтам, добавьте его в Headers:
Key: Authorization   Value: Bearer {your-access-token}
   ![](src/main/resources/static/images/git_tutor/api-testing.png)

