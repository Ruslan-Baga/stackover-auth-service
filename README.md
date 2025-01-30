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
