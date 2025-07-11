package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import wrappers.AuthApiWrapper;

public class LogoutTest extends TestBase {

    @Test(description = "Logout should return 200 OK")
    public void logoutShouldSucceed() {
        String sid = Allure.step("Login and get SID", AuthApiWrapper::loginAsAdmin);

        Allure.step("Logout user session", () -> AuthApiWrapper.logout(sid));
    }
}