package tests;

import helpers.CustomAllureListener;
import helpers.DataHelper;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specs.Specs.baseRequestSpec;
import static specs.Specs.responseSpec400;

@Epic("Authentication API")
@Feature("Negative Authentication Tests")
public class AuthNegativeTests extends TestBase {

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with empty body should return 400")
    public void loginWithEmptyBody() {
        Allure.step("Scenario: Send empty body for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body("{}")
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .spec(responseSpec400)
                        .body("message", equalTo("Email and password are required"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with empty email should return 400")
    public void loginWithEmptyEmail() {
        String body = "{ \"email\": \"\", \"password\": \"" + DataHelper.generateRandomPassword() + "\" }";

        Allure.step("Scenario: Send empty email for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .spec(responseSpec400)
                        .body("message", equalTo("Email and password are required"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with empty password should return 400")
    public void loginWithEmptyPassword() {
        String body = "{ \"email\": \"" + DataHelper.generateRandomEmail() + "\", \"password\": \"\" }";

        Allure.step("Scenario: Send empty password for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .spec(responseSpec400)
                        .body("message", equalTo("Email and password are required"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }

    @Story("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with invalid credentials should return 401")
    public void loginWithInvalidCredentials() {
        String body = "{ \"email\": \"" + DataHelper.generateInvalidEmail() + "\", \"password\": \"" + DataHelper.generateInvalidPassword() + "\" }";

        Allure.step("Scenario: Send wrong credentials for login", () ->
                given()
                        .filter(CustomAllureListener.withCustomTemplates())
                        .spec(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .statusCode(401)
                        .body("message", equalTo("Invalid credentials"))
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"))
        );
    }
}