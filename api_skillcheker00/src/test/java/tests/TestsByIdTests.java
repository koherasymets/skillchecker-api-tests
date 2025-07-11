package tests;

import dto.TestRequest;
import helpers.DataHelper;
import io.qameta.allure.Allure;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;
import wrappers.TestsApiWrapper;

import static org.hamcrest.Matchers.equalTo;

public class TestsByIdTests extends TestBase {

    @Test(description = "Create test, update it by ID and validate GET response schema")
    public void createUpdateAndCheckSchema() {
        String sid = Allure.step("Login and get SID", AuthApiWrapper::loginAsAdmin);

        TestRequest createBody = new TestRequest();
        createBody.setName(DataHelper.generateRandomTestName());
        createBody.setDescription("Initial description");
        createBody.setTimeLimit(30);
        createBody.setPassingScore(70);

        int testId = Allure.step("Create new test", () ->
                TestsApiWrapper.createTestPositive(sid, createBody)
        );

        TestRequest updateBody = new TestRequest();
        updateBody.setName(DataHelper.generateRandomTestName());
        updateBody.setDescription("Updated description");

        Allure.step("Update test by ID", () ->
                TestsApiWrapper.updateTestById(sid, testId, updateBody)
        );

        Allure.step("GET test by ID and validate JSON Schema", () ->
                TestsApiWrapper.getTestById(sid, testId)
                        .then()
                        .statusCode(200)
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/test_by_id_response.json"))
                        .body("id", equalTo(testId))
                        .body("name", equalTo(updateBody.getName()))
                        .body("description", equalTo(updateBody.getDescription()))
        );
    }
}