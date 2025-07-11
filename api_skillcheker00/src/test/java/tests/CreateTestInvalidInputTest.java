package tests;

import dto.TestRequest;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;
import wrappers.TestsApiWrapper;

import static org.hamcrest.Matchers.equalTo;

public class CreateTestInvalidInputTest extends TestBase {

    @Test
    public void createTestWithoutNameShouldFail() {
        String sid = Allure.step("Login and get SID", AuthApiWrapper::loginAsAdmin);

        TestRequest invalidTest = new TestRequest();
        invalidTest.setName(null);
        invalidTest.setDescription("This should fail because name is missing");
        invalidTest.setTimeLimit(30);
        invalidTest.setPassingScore(70);

        Allure.step("Try to create test without name — expect 400 and validate error schema", () -> {
            Response response = TestsApiWrapper.createTestNegative(sid, invalidTest);

            response.then()
                    .statusCode(400)
                    .body("message", equalTo("Test name must not be empty or whitespace only"))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error_response.json"));
        });
    }
}