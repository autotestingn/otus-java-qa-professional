package services;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

abstract class PetstoreApi {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    protected RequestSpecification requestSpecification;

    public PetstoreApi() {
        requestSpecification = given()
                                .baseUri(BASE_URI)
                                .contentType(ContentType.JSON);
    }
}
