package tests;

import helpers.CustomAllureListener;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static specs.Specs.baseRequestSpec;
import static specs.Specs.responseSpec200;

@Epic("Authentication API")
@Feature("Login Flow")
public class SampleTest extends TestBase {

    @Story("Login and fetch tests")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Konstantin")
    @Test(description = "Login and Get all tests with valid SID")
    public void loginAndGetAllTests() {

        Allure.step("Login and get tests flow", () -> {

            Response loginResponse = Allure.step("Step 1: Send POST /login with valid credentials", () ->
                    given()
                            .filter(CustomAllureListener.withCustomTemplates())
                            .spec(baseRequestSpec)
                            .body("{ \"email\": \"admin@skillchecker.tech\", \"password\": \"admin123\" }")
                            .when()
                            .post("/login")
                            .then()
                            .log().all()
                            .statusCode(200)
                            .extract()
                            .response()
            );

            Map<String, String> cookies = loginResponse.getCookies();
            String sid = cookies.get("connect.sid");

            Allure.step("Step 2: Fetched SID = " + sid);

            Allure.step("Step 3: Send GET /tests with valid SID", () ->
                    given()
                            .filter(CustomAllureListener.withCustomTemplates())
                            .spec(baseRequestSpec)
                            .cookie("connect.sid", sid)
                            .when()
                            .get("/tests")
                            .then()
                            .log().all()
                            .spec(responseSpec200)
            );
        });
    }
}