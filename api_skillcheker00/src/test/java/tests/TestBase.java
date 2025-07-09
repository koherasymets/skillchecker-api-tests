package tests;

import helpers.ConfigProvider;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigProvider.BASE_URL;
    }
}