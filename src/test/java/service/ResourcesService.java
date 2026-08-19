package service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.response.ResourceInfo;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import static org.awaitility.Awaitility.await;

public class ResourcesService extends BaseService{

    private static final String BASE_PATH="/disk/resources";

    @Step("Создать каталог по пути: {path}")
    public Response createCatalog(String path){
        return putRequest(BASE_PATH, Map.of("path",path));
    }

    @Step("Получить информацию о ресурсе по пути: {path}")
    public Response getInfoByPath(String path){
        return getRequest(BASE_PATH, Map.of("path",path));
    }

    @Step("Удалить ресурс по пути: {path}")
    public Response deleteByPath(String path){
        return deleteRequest(BASE_PATH, Map.of("path",path));
    }

    @Step("Загрузить файл из {url} в {path}")
    public Response uploadFile(String path, String url){
        return postRequest(BASE_PATH + "/upload", Map.of("path",path,"url",url));
    }

    @Step("Ожидание появления ресурса в {path} при асинхронной операции")
    public ResourceInfo waitForResource(String path) {
        return await()
                .atMost(Duration.ofSeconds(5))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> {
                    Response response = getInfoByPath(path);

                    return response.statusCode() == 200
                            ? response.as(ResourceInfo.class)
                            : null;
                }, Objects::nonNull);
    }

}
