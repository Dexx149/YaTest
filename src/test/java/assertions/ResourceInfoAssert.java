package assertions;

import io.qameta.allure.Step;
import model.response.ResourceInfo;
import org.assertj.core.api.AbstractAssert;

public class ResourceInfoAssert extends AbstractAssert<ResourceInfoAssert, ResourceInfo> {
    private ResourceInfoAssert(ResourceInfo actual) {
        super(actual, ResourceInfoAssert.class);
    }

    public static ResourceInfoAssert assertThat(ResourceInfo actual) {
        return new ResourceInfoAssert(actual);
    }

    @Step("Проверить, что ресурс является файлом")
    public ResourceInfoAssert isFile() {
        isNotNull();
        if (!actual.isFile()) {
            failWithMessage("Ожидался файл, получен тип: %s", actual.getType());
        }
        return this;
    }

    @Step("Проверить, что ресурс является папкой")
    public ResourceInfoAssert isDirectory() {
        isNotNull();
        if (!actual.isDirectory()) {
            failWithMessage("Ожидался каталог, получен тип: %s", actual.getType());
        }
        return this;
    }
}
