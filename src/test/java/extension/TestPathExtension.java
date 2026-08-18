package extension;

import annotation.TestPath;
import org.junit.jupiter.api.extension.*;

import java.util.UUID;

public class TestPathExtension  implements BeforeEachCallback, ParameterResolver {
    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(TestPathExtension.class);
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String path = "testPath"+ UUID.randomUUID();
        context.getStore(NAMESPACE).put(context.getUniqueId(), path);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(String.class)
                && parameterContext.getParameter().isAnnotationPresent(TestPath.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId());
    }
    public static String getPath(ExtensionContext context) {
        String path = context.getStore(NAMESPACE).get(context.getUniqueId()).toString();

        if (path == null) {
            throw new IllegalStateException("Test path was not generated");
        }

        return path;
    }
}
