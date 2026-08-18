package extension;

import org.junit.jupiter.api.extension.*;
import service.ResourcesService;


public class CreateCatalogExtension implements BeforeEachCallback{

    private final ResourcesService resourcesService = new ResourcesService();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String path = TestPathExtension.getPath(context);
        resourcesService.createCatalog(path).then().statusCode(201);
    }



}
