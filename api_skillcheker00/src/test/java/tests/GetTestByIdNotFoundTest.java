package tests;

import dto.ErrorResponse;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;
import wrappers.TestsApiWrapper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class GetTestByIdNotFoundTest extends TestBase {

    @Test(description = "GET /tests/{id} for non-existing ID returns 404 with correct error response")
    public void getTestByInvalidIdShouldReturn404() {
        String sid = Allure.step("Login and get SID", AuthApiWrapper::loginAsAdmin);

        int nonExistingId = 999999;

        Allure.step("Try to get non-existing test ID", () -> {
            Response response = TestsApiWrapper.getTestById(sid, nonExistingId);
            ErrorResponse error = response.as(ErrorResponse.class);

            assertThat(error.getMessage()).isEqualTo("Test not found");

            response.then()
                    .statusCode(404)
                    .body(matchesJsonSchemaInClasspath("schemas/error_response.json"));
        });
    }
}