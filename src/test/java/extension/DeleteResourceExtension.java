package extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import service.ResourcesService;

public class DeleteResourceExtension implements AfterEachCallback {

    private final ResourcesService resourcesService = new ResourcesService();

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        String path = TestPathExtension.getPath(context);
        resourcesService.deleteByPath(path).then().statusCode(204);
    }
}
