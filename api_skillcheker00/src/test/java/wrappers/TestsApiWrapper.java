package wrappers;

import dto.TestRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.Specs.baseRequestSpec;

public class TestsApiWrapper {

    public static int createTestPositive(String sid, TestRequest body) {
        Response response = given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .body(body)
                .when()
                .post("/tests")
                .then()
                .statusCode(201)
                .extract()
                .response();

        return response.jsonPath().getInt("id");
    }

    public static Response createTestNegative(String sid, TestRequest body) {
        return given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .body(body)
                .when()
                .post("/tests")
                .then()
                .extract()
                .response();
    }

    public static void updateTestById(String sid, int testId, TestRequest body) {
        given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .body(body)
                .when()
                .patch("/tests/" + testId)
                .then()
                .statusCode(200);
    }

    public static Response getTestById(String sid, int testId) {
        return given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .when()
                .get("/tests/" + testId)
                .then()
                .extract()
                .response();
    }

    public static Response getAllTests(String sid) {
        return given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .when()
                .get("/tests")
                .then()
                .extract()
                .response();
    }

    public static void deleteTestById(String sid, int testId) {
        given()
                .spec(baseRequestSpec)
                .cookie("connect.sid", sid)
                .when()
                .delete("/tests/" + testId)
                .then()
                .statusCode(204);
    }
}