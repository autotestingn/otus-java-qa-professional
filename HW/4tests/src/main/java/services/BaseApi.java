package services;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApi {
    private static final String HOSTNAME = System.getProperty("stubHostname") + ":" + System.getProperty("stubPort") + "/api/";
    protected RequestSpecification requestSpecification;

    public BaseApi() {
        requestSpecification = given()
                                .baseUri(HOSTNAME)
                                .contentType(ContentType.JSON);
    }
}
