# 🧪 API Skillchecker — Rest Assured Test Framework

**Это учебный проект для тестирования API платформы Skillchecker.**

## 📚 Описание

Здесь реализован автотестовый фреймворк на **Java + Rest Assured + TestNG + Allure**, который покрывает:

- ✔️ Позитивные кейсы (валидные запросы)
- ✔️ Негативные кейсы (невалидные запросы и ошибки)
- ✔️ Проверку JSON схем
- ✔️ Отчёты Allure

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
- Типовые запросы можно выносить в **Wrappers** (опционально).

## 💻 Автор

**Kostiantyn Herasymets**

