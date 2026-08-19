package test.api;

import annotation.TestPath;
import assertions.ApiAssert;
import assertions.ResourceInfoAssert;
import extension.CreateCatalogExtension;
import extension.DeleteResourceExtension;
import extension.TestPathExtension;
import model.response.ResourceInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import service.ResourcesService;

public class ResourcesTest {

    private final ResourcesService resourcesService = new ResourcesService();

    @ExtendWith({TestPathExtension.class, DeleteResourceExtension.class})
    @Test
    public void shouldCreateEmptyCatalog(@TestPath String path) {
        ApiAssert.assertThat(resourcesService.createCatalog(path)).hasCreated();
        ResourceInfo resourceInfo =
                ApiAssert.assertThat(resourcesService.getInfoByPath(path)).hasStatus(200).as(ResourceInfo.class);
        ResourceInfoAssert.assertThat(resourceInfo).isDirectory();
    }

    @ExtendWith({TestPathExtension.class, CreateCatalogExtension.class})
    @Test
    public void shouldDeleteResource(@TestPath String path) {
        ApiAssert.assertThat(resourcesService.deleteByPath(path)).hasStatus(204);
        ApiAssert.assertThat(resourcesService.getInfoByPath(path)).isNotFound();
    }

    @ExtendWith({TestPathExtension.class, DeleteResourceExtension.class})
    @Test
    public void shouldUploadFileFromUrl(@TestPath String path) {

        String filePath =
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hWke95LIUerTt1L5mGB-bAzQnIchUAyzysY1M2th6eCU7xcSk6ohrPQs&s=10";

        ApiAssert.assertThat(resourcesService.uploadFile(path,filePath)).hasStatus(202);
        ResourceInfo resourceInfo = resourcesService.waitForResource(path);
        ResourceInfoAssert.assertThat(resourceInfo).isFile();
    }
}
