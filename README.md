# Yandex Disk API Tests

Автоматизированные API-тесты для REST API Яндекс.Диска, написанные с использованием Java, Rest Assured, JUnit 5 и Allure.

Проект содержит примеры тестирования основных HTTP-методов REST API:

- GET
- POST
- PUT
- DELETE

## Требования

- Java 17+
- Gradle (или Gradle Wrapper)
- OAuth-токен Яндекс.Диска

## Запуск

1. Получите OAuth-токен Яндекс.Диска. [Инструкция](https://yandex.ru/dev/disk-api/doc/ru/concepts/quickstart)
2. Укажите токен в файле `src/test/resources/application.properties` в поле `token`:

```properties
token=<Ваш_токен>
```

3. Запустите тесты:

```bash
./gradlew test
```

## Allure

Сформировать отчет:

```bash
./gradlew allureReport
```

Открыть отчет:

```bash
./gradlew allureServe
```
