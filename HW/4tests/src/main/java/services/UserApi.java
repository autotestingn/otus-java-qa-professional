package services;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserApi extends BaseApi {
    public UserApi() {
        requestSpecification.basePath("/users/");
    }

    public Response getScoresByUserId(String userId) {
        return given(requestSpecification)
                .with()
                .pathParam("userId", userId)
                .log().all()
                .when()
                .get("/{userId}/scores/");
    }

    public Response getUsersList() {
        return given(requestSpecification)
                .with()
                .log().all()
                .when()
                .get("/");
    }

    public ValidatableResponse validateJsonSchema(Response response, String jsonSchemaPath) {
        return response
                .then()
                .body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }
}
