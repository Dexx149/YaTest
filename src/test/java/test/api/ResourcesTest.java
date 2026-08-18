package test.api;

import annotation.TestPath;
import extension.CreateCatalogExtension;
import extension.TestPathExtension;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import service.ResourcesService;

import static org.assertj.core.api.Assertions.assertThat;


public class ResourcesTest {

    private final ResourcesService resourcesService = new ResourcesService();

    //extension для удаления папки после теста
    @ExtendWith({TestPathExtension.class})
    @Test
    public void shouldCreateCatalog(@TestPath String path) {
        assertThat(resourcesService.createCatalog(path).statusCode()).isEqualTo(201);
        assertThat(resourcesService.getInfoByPath(path).statusCode()).isEqualTo(200);
    }

    @ExtendWith({TestPathExtension.class, CreateCatalogExtension.class})
    @Test
    public void shouldDeleteResource(@TestPath String path) {
        assertThat(resourcesService.deleteByPath(path).statusCode()).isEqualTo(204);
        assertThat(resourcesService.getInfoByPath(path).statusCode()).isEqualTo(404);
    }

    //extension для удаления ресурса после тестом
    @ExtendWith({TestPathExtension.class})
    @Test
    public void shouldUploadFileFromUrl(@TestPath String path) {
        Response response= resourcesService.uploadFile(path,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hWke95LIUerTt1L5mGB-bAzQnIchUAyzysY1M2th6eCU7xcSk6ohrPQs&s=10");
        assertThat(response.statusCode()).isEqualTo(202);
        response= resourcesService.getInfoByPath(path);
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
