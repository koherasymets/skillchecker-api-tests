package helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.Filter;

public class CustomAllureListener {

    public static Filter withCustomTemplates() {
        return new AllureRestAssured();
    }

}