package service;

import io.restassured.response.Response;

import java.util.Map;

public class ResourcesService extends BaseService{

    private static final String BASE_PATH="/disk/resources";

    public Response createCatalog(String path){
        return putRequest(BASE_PATH, Map.of("path",path));
    }

    public Response getInfoByPath(String path){
        return getRequest(BASE_PATH, Map.of("path",path));
    }

    public Response deleteByPath(String path){
        return deleteRequest(BASE_PATH, Map.of("path",path));
    }

    public Response uploadFile(String path, String url){
        return postRequest(BASE_PATH + "/upload", Map.of("path",path,"url",url));
    }

}
