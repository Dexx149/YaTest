package service;

import io.restassured.response.Response;

public class DiskService extends BaseService{
    private static final String BASE_PATH="/disk";

    public Response getInfo(){
        return getRequest(BASE_PATH);
    }
}
