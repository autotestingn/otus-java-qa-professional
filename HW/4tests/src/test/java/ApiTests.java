import dto.UserResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.UserApi;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {
    /**
     * Получение оценок пользователя по id пользователя
     * <p>
     * Шаги:
     * 1. Отправляем GET-запрос c URL http://localhost:8080/api/users/{userId}/scores/,
     * где userId пользователя
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со статусом 200, в теле ответа информация об оценках пользователя с "userId" по всем его курсам
     */
    @Test
    public void getUserScoresByExistUserId() {
        UserApi userApi = new UserApi();
        Response response = userApi.getScoresByUserId("1");

        response
                .then()
                .log().all()
                .statusCode(200)
                .body("get(0).score", equalTo(100))
                .body("get(0).userName", equalTo("JohnSmith"))
                .body("get(0).courseName", equalTo("JAVA"))
                .body("get(1).score", equalTo(78))
                .body("get(1).userName", equalTo("JohnSmith"))
                .body("get(1).courseName", equalTo("QA"));

        userApi.validateJsonSchema(response, "schemas/UserScores.json");
    }

    /**
     * Получение ответа на запрос оценок пользователя по id несуществующего пользователя
     * <p>
     * Шаги:
     * 1. Отправляем GET-запрос c URL http://localhost:8080/api/users/{userId}/scores/,
     * где userId несуществующего пользователя
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со статусом 404
     */
    @Test
    public void getUserScoresByNonExistUserId() {
        UserApi userApi = new UserApi();

        userApi.getScoresByUserId("3")
                .then()
                .log().all()
                .statusCode(404);
    }

    /**
     * Получение списка всех пользователей
     * <p>
     * Шаги:
     * 1. Отправляем GET-запрос c URL http://localhost:8080/api/users/
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со списком пользователей
     */
    @Test
    public void getUsersList() {
        UserApi userApi = new UserApi();
        Response response = userApi.getUsersList();

        List<UserResponse> users = Arrays.asList(response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .as(UserResponse[].class));

        assertThat(users.size(), equalTo(3));
        assertThat(users.get(0).getId(), equalTo(1L));
        assertThat(users.get(0).getName(), equalTo("JohnSmith"));
        assertThat(users.get(0).getEmail(), equalTo("john.smith@gmail.com"));

        userApi.validateJsonSchema(response, "schemas/Users.json");
    }
}
