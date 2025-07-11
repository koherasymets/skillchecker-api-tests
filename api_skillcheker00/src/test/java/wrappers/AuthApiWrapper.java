package wrappers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.Specs.baseRequestSpec;
import static helpers.ConfigProvider.ADMIN_EMAIL;
import static helpers.ConfigProvider.ADMIN_PASSWORD;

public class AuthApiWrapper {

    public static String loginAsAdmin() {
        Response response = given()
                .spec(baseRequestSpec)
                .body("{ \"email\": \"" + ADMIN_EMAIL + "\", \"password\": \"" + ADMIN_PASSWORD + "\" }")
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.getCookie("connect.sid");
    }

    public static Response getAuthenticatedUser(String sid) {
        return given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .when()
                .get("/user")
                .then()
                .extract()
                .response();
    }

    public static void logout(String sid) {
        given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .when()
                .post("/logout")
                .then()
                .statusCode(200);
    }
}