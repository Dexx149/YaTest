package service;

import config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.stream.Collector;

import static io.restassured.RestAssured.given;

public class BaseService {

    private final RequestSpecification requestSpecification;

    public BaseService(){
        requestSpecification = given().contentType(ContentType.JSON).baseUri(Config.getBaseUrl())
                .header("Authorization", Config.getToken());
    }

    protected Response getRequest(String endpoint) {
        return requestSpecification.when().get(endpoint);
    }

    protected Response getRequest(String endpoint, Map<String, ?> queryParams) {
        return requestSpecification
                .queryParams(queryParams)
                .when()
                .get(endpoint);
    }

    protected Response postRequest(String endpoint, Object payload) {
        return requestSpecification.body(payload).when().post(endpoint);
    }
    protected Response postRequest(String endpoint, Map<String, ?> queryParams) {
        return requestSpecification
                .queryParams(queryParams)
                .when()
                .post(endpoint);
    }

    protected Response putRequest(String endpoint, Object payload) {
        return requestSpecification.body(payload).when().put(endpoint);
    }

    protected Response putRequest(String endpoint, Map<String, ?> queryParams) {
        return requestSpecification
                .queryParams(queryParams)
                .when()
                .put(endpoint);
    }

    protected Response deleteRequest(String endpoint) {
        return requestSpecification.when().delete(endpoint);
    }

    protected Response deleteRequest(String endpoint, Map<String, ?> queryParams) {
        return requestSpecification
                .queryParams(queryParams)
                .when()
                .delete(endpoint);
    }
}
