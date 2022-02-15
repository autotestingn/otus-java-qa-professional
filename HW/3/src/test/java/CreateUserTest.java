import dto.UserRequest;
import dto.UserResponse;
import org.testng.annotations.Test;
import services.UserApi;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {

    /**
     * Создание списка валидных пользователей
     * <p>
     * Шаги:
     * 1. Создаем первого пользователя
     * 2. Создаем второго пользователя
     * 3. Добавляем пользователей в список
     * 4. Отправляем POST-запрос c URL https://petstore.swagger.io/v2/user/createWithList, передавая в теле запроса список пользователей
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со статусом 200, message = ok
     */

    @Test
    public void createListOfValidUsers() {
        UserRequest firstUser = UserRequest.builder()
                .username("firstUser")
                .phone("123")
                .email("123@123.ru")
                .userStatus(1L)
                .id(1L)
                .firstName("firstUserName")
                .password("123")
                .lastName("firstUserLastName")
                .build();

        UserRequest secondUser = UserRequest.builder()
                .username("secondUser")
                .phone("345")
                .email("345@345.ru")
                .userStatus(2L)
                .id(2L)
                .firstName("secondUserName")
                .password("345")
                .lastName("secondUserLastName")
                .build();

        List<UserRequest> users = new ArrayList<>();
        users.add(firstUser);
        users.add(secondUser);

        UserApi userApi = new UserApi();
        userApi.createUsersWithList(users)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("ok"));
    }

    /**
     * Создание валидного пользователя
     * <p>
     * Шаги:
     * 1. Создаем пользователя
     * 2. Отправляем POST-запрос c URL https://petstore.swagger.io/v2/user, передавая в теле запроса пользователя
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со статусом 200, message = 1
     */

    @Test
    public void createValidUser() {
        UserRequest user = UserRequest.builder()
                .username("user")
                .phone("123")
                .email("123@123.ru")
                .userStatus(1L)
                .id(1L)
                .firstName("userName")
                .password("123")
                .lastName("userLastName")
                .build();

        UserApi userApi = new UserApi();
        userApi.createUser(user)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("1"));
    }

    /**
     * Получение пользователя по имени
     * <p>
     * Шаги:
     * 1. Создаем пользователя с именем "user1"
     * 2. Отправляем GET-запрос c URL https://petstore.swagger.io/v2/user/{username}, где username - имя "user1" созданного пользователя
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со статусом 200, в теле ответа информация о пользователе с именем "user1"
     */
    @Test
    public void getUserByExistName() {
        UserRequest.builder()
                .username("user1")
                .phone("123")
                .email("123@123.ru")
                .userStatus(1L)
                .id(1L)
                .firstName("userName")
                .password("123")
                .lastName("userLastName")
                .build();

        UserApi userApi = new UserApi();

        userApi.getUserByName("user1")
                .then()
                .log().all()
                .statusCode(200)
                .body("username", equalTo("user1"))
                .body("phone", equalTo("123"))
                .body("email", equalTo("123@123.ru"))
                .body("userStatus", equalTo(1L))
                .body("id", equalTo(1L))
                .body("firstName", equalTo("userName"))
                .body("lastName", equalTo("userLastName"));
    }

    /**
     * Получение пользователя по несуществующему имени
     * <p>
     * Шаги:
     * 1. Создаем пользователя с именем "user1"
     * 2. Отправляем GET-запрос c URL https://petstore.swagger.io/v2/user/{username}, где username - несуществующее имя "user2" несуществующего пользователя
     * <p>
     * Ожидаемый результат:
     * Приходит ответ со статусом 404, в теле ответа message "User not found"
     */
    @Test
    public void getUserByNotExistName() {
        UserRequest.builder()
                .username("user1")
                .phone("123")
                .email("123@123.ru")
                .userStatus(1L)
                .id(1L)
                .firstName("userName")
                .password("123")
                .lastName("userLastName")
                .build();

        UserApi userApi = new UserApi();

        UserResponse userResponse = userApi.getUserByName("user2")
                .then()
                .log().all()
                .statusCode(404)
                .extract()
                .body()
                .as(UserResponse.class);

        assertThat(userResponse.getCode(), equalTo(1L));
        assertThat(userResponse.getType(), equalTo("error"));
        assertThat(userResponse.getMessage(), equalTo("User not found"));
    }
}
