package service;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DiskService extends BaseService{
    private static final String BASE_PATH="/disk";

    @Step("Получить информацию о диске")
    public Response getInfo(){
        return getRequest(BASE_PATH);
    }
}
