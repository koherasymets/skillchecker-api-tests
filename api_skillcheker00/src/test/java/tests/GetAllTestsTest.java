package tests;

import dto.TestRequest;
import helpers.DataHelper;
import io.qameta.allure.Allure;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;
import wrappers.TestsApiWrapper;

import static org.hamcrest.Matchers.hasItem;

public class GetAllTestsTest extends TestBase {

    @Test(description = "Create test, verify it appears in GET /tests, and validate JSON Schema")
    public void createTestAndCheckInAllTests() {

        String sid = Allure.step("Step 1: Login and get SID", AuthApiWrapper::loginAsAdmin);

        TestRequest newTest = new TestRequest();
        newTest.setName(DataHelper.generateRandomTestName());
        newTest.setDescription("Created for get all");
        newTest.setTimeLimit(30);
        newTest.setPassingScore(70);

        int testId = Allure.step("Step 2: Create new test", () ->
                TestsApiWrapper.createTestPositive(sid, newTest)
        );

        Allure.step("Step 3: Verify new test appears in GET /tests and matches JSON Schema", () -> {
            Response response = TestsApiWrapper.getAllTests(sid);
            response.then()
                    .statusCode(200)
                    .body("id", hasItem(testId))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/tests_response.json"));
        });
    }
}