package tests;

import dto.TestRequest;
import helpers.DataHelper;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;
import wrappers.TestsApiWrapper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specs.Specs.baseRequestSpec;

public class DeleteTestByIdTest extends TestBase {

    @Test
    public void createAndDeleteTestById() {
        String sid = Allure.step("Login and get SID", AuthApiWrapper::loginAsAdmin);

        TestRequest request = new TestRequest();
        request.setName(DataHelper.generateRandomTestName());
        request.setDescription("To be deleted");
        request.setTimeLimit(30);
        request.setPassingScore(70);

        int testId = Allure.step("Create new test", () ->
                TestsApiWrapper.createTestPositive(sid, request)
        );

        Allure.step("Delete test by ID", () ->
                TestsApiWrapper.deleteTestById(sid, testId)
        );

        Allure.step("Check deleted test returns 404", () ->
                given()
                        .spec(baseRequestSpec)
                        .cookie("connect.sid", sid)
                        .when()
                        .get("/tests/" + testId)
                        .then()
                        .statusCode(404)
                        .body("message", equalTo("Test not found"))
        );
    }
}
