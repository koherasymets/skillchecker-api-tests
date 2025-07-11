# 🧪 API SkillChecker — Rest Assured Test Framework

**Это учебный проект для тестирования API платформы Skillchecker.**

## 📚 Описание

В этом проекте реализован полноценный автотестовый фреймворк на **Java + Rest Assured + TestNG + Allure**, который покрывает:

- ✔️ Позитивные сценарии (валидные запросы, CRUD)
- ✔️ Негативные сценарии (невалидные данные, пустые поля, неверная авторизация)
- ✔️ Проверку схем JSON (JSON Schema Validation)
- ✔️ Генерацию подробных отчётов Allure с пошаговыми шагами
- ✔️ Использование DataHelper и Wrappers для чистого кода

## 🚀 Технологии

- Java 17
- Maven
- Rest Assured
- TestNG
- Allure Reports
- Postman (коллекция запросов)

## 📂 Структура проекта

```
├── pom.xml
├── .gitignore
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   ├── helpers/
│       │   ├── specs/
│       └── resources/
│           ├── schemas/
│           ├── config.properties
├── postman/
│   ├── Skillchecker.postman_collection.json
```

## ⚙️ Запуск тестов

1. Установить зависимости:

   ```bash
   mvn clean install
   ```

2. Запустить тесты:

   ```bash
   mvn test
   ```

3. Сгенерировать отчёт Allure:

   ```bash
   allure serve target/allure-results
   ```

## 📌 Важно

- Все конфигурации — в `config.properties`.
- Для генерации динамических данных используется `DataHelper`.
- Типовые запросы вынесены в **Wrappers**.
- Единые Specs используются для Request/Response.
- Полное покрытие позитивных и негативных сценариев.
- Подробная трассировка шагов через `Allure.step` и `CustomAllureListener`.

## 💻 Автор

**Kostiantyn Herasymets**

