package services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {
    private static final String USER_LIST_RESOURCE = "/users/";

    private static final String GET_USER_SCORE_BY_USER_ID_RESOURCE = "/users/{userId}/scores/";

    public Response getScoresByUserId(String userId) {
        return given(requestSpecification)
                .with()
                .pathParam("userId", userId)
                .log().all()
                .when()
                .get(GET_USER_SCORE_BY_USER_ID_RESOURCE);
    }

    public Response getUsersList() {
        return given(requestSpecification)
                .with()
                .log().all()
                .when()
                .get(USER_LIST_RESOURCE);
    }
}
