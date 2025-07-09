package tests;

import helpers.DataHelper;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specs.Specs.baseRequestSpec;

public class TestsByIdTests extends TestBase {

    @Test(description = "Create test and update it by ID")
    public void createAndUpdateTestById() {

        Response loginResponse = Allure.step("Step 1: Login and get SID", () ->
                given()
                        .spec(baseRequestSpec)
                        .body("{ \"email\": \"admin@skillchecker.tech\", \"password\": \"admin123\" }")
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
        );

        String sid = loginResponse.getCookie("connect.sid");
        Allure.step("Fetched SID = " + sid);

        String testName = DataHelper.generateRandomTestName();

        Response createTestResponse = Allure.step("Step 2: Create new test", () ->
                given()
                        .spec(baseRequestSpec)
                        .cookie("connect.sid", sid)
                        .body("{ \"name\": \"" + testName + "\" }")
                        .when()
                        .post("/tests")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response()
        );

        int testId = createTestResponse.jsonPath().getInt("id");
        Allure.step("Created test with ID = " + testId);

        Allure.step("Step 3: Update test by ID", () -> {
            String updatedName = DataHelper.generateRandomTestName();
            String updatedDescription = "Updated description";

            given()
                    .spec(baseRequestSpec)
                    .cookie("connect.sid", sid)
                    .body("{ \"name\": \"" + updatedName + "\", \"description\": \"" + updatedDescription + "\" }")
                    .when()
                    .patch("/tests/" + testId)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(updatedName))
                    .body("description", equalTo(updatedDescription));
        });
    }
}