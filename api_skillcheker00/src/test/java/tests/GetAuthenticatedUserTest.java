package tests;

import dto.UserResponse;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAuthenticatedUserTest extends TestBase {

    @Test(description = "GET /user returns correct authenticated user info")
    public void getAuthenticatedUserShouldReturnUserInfo() {
        String sid = Allure.step("Step 1: Login and get SID", AuthApiWrapper::loginAsAdmin);

        Allure.step("Step 2: GET /user and verify mapped DTO fields", () -> {
            Response response = AuthApiWrapper.getAuthenticatedUser(sid);
            UserResponse user = response.as(UserResponse.class);

            assertThat(user.getEmail()).isEqualTo("admin@skillchecker.tech");
            assertThat(user.getFullName()).isNotBlank();
            assertThat(user.getOrganizationId()).isGreaterThan(0);
            assertThat(user.getRole()).isEqualTo("admin");
            assertThat(user.isActive()).isTrue();
            assertThat(user.getLastLogin()).isNotNull();
        });
    }
}