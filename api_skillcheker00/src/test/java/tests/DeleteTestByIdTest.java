package tests;

import helpers.ConfigProvider;
import helpers.DataHelper;
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteTestByIdTest extends TestBase {

    @Test
    public void createAndDeleteTestById() {
        String sid = Allure.step("Login and get SID", () ->
                given()
                        .baseUri(ConfigProvider.BASE_URL)
                        .contentType(ContentType.JSON)
                        .body("{\"email\": \"admin@skillchecker.tech\", \"password\": \"admin123\"}")
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(200)
                        .extract().cookie("connect.sid")
        );

        String testName = DataHelper.generateRandomTestName();

        int testId = Allure.step("Create new test with name: " + testName, () ->
                given()
                        .baseUri(ConfigProvider.BASE_URL)
                        .contentType(ContentType.JSON)
                        .cookie("connect.sid", sid)
                        .body("{\"name\": \"" + testName + "\"}")
                        .when()
                        .post("/tests")
                        .then()
                        .statusCode(201)
                        .extract().jsonPath().getInt("id")
        );

        Allure.step("Delete test by ID", () ->
                given()
                        .baseUri(ConfigProvider.BASE_URL)
                        .cookie("connect.sid", sid)
                        .when()
                        .delete("/tests/" + testId)
                        .then()
                        .statusCode(204)
        );

        Allure.step("Check that deleted test returns 404", () ->
                given()
                        .baseUri(ConfigProvider.BASE_URL)
                        .cookie("connect.sid", sid)
                        .when()
                        .get("/tests/" + testId)
                        .then()
                        .statusCode(404)
                        .body("message", equalTo("Test not found"))
        );
    }
}