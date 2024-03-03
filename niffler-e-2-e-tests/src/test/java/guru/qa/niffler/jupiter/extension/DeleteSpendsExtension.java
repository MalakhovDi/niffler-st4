package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.api.client.SpendApiClient;
import guru.qa.niffler.model.CategoryJson;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DeleteSpendsExtension implements BeforeEachCallback {
    private final SpendApiClient spendApiClient = new SpendApiClient();
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        CategoryJson categoryFromExtension = (CategoryJson) extensionContext.getStore(CategoryExtension.NAMESPACE).get("category");
        String username = categoryFromExtension.username();

        spendApiClient.deleteSpends(username);
    }
}
