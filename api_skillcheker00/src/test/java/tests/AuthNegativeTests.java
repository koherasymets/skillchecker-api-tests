package tests;

import dto.LoginRequest;
import helpers.CustomAllureListener;
import helpers.DataHelper;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specs.Specs.*;

@Epic("Authentication API")
@Feature("Negative Authentication Tests")
public class AuthNegativeTests extends TestBase {

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with empty body should return 400")
    public void loginWithEmptyBody() {
        LoginRequest body = new LoginRequest();

        Allure.step("Scenario: Send empty body for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec400)
                        .body("message", equalTo("Email and password are required"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with empty email should return 400")
    public void loginWithEmptyEmail() {
        LoginRequest body = new LoginRequest("", DataHelper.generateRandomPassword());

        Allure.step("Scenario: Send empty email for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec400)
                        .body("message", equalTo("Email and password are required"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with empty password should return 400")
    public void loginWithEmptyPassword() {
        LoginRequest body = new LoginRequest(DataHelper.generateRandomEmail(), "");

        Allure.step("Scenario: Send empty password for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec400)
                        .body("message", equalTo("Email and password are required"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with invalid credentials should return 401")
    public void loginWithInvalidCredentials() {
        LoginRequest body = new LoginRequest(
                DataHelper.generateInvalidEmail(),
                DataHelper.generateInvalidPassword()
        );

        Allure.step("Scenario: Send wrong credentials for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec401)
                        .body("message", equalTo("Invalid credentials"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }
}