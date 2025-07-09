package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {

    public static RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://skillchecker.tech/api")
            .setContentType("application/json")
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();
}