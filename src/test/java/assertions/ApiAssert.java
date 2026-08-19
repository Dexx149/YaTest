package assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;

public class ApiAssert extends AbstractAssert<ApiAssert, Response> {
    private ApiAssert(Response actual) {
        super(actual, ApiAssert.class);
    }

    public static ApiAssert assertThat(Response actual) {
        return new ApiAssert(actual);
    }

    @Step("Проверить, что статус ответа равен {expected}")
    public ApiAssert hasStatus(int expected) {
        isNotNull();
        if (actual.statusCode() != expected) {
            failWithMessage("Ожидался статус %d, получен %d", expected, actual.statusCode());
        }
        return this;
    }

    @Step("Проверить, что ресурс успешно создан")
    public ApiAssert hasCreated() {
        return hasStatus(201);
    }

    @Step("Проверить, что ресурс не найден")
    public ApiAssert isNotFound() {
        return hasStatus(404);
    }

    public <T> T as(Class<T> clazz) {
        return actual.as(clazz);
    }
}
