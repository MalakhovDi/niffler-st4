package guru.qa.niffler.api.client;

import guru.qa.niffler.api.service.CategoryApi;
import guru.qa.niffler.model.CategoryJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class CategoryApiClient extends RestClient {

    private final CategoryApi categoryApi;

    public CategoryApiClient() {
        super("http://127.0.0.1:8093");
        this.categoryApi = retrofit.create(CategoryApi.class);
    }

    @Step("POST addCategory")
    public CategoryJson addCategory(CategoryJson category) throws IOException {
        return categoryApi.addCategory(category).execute().body();
    }

    @Step("GET getCategories {username}")
    public List<CategoryJson> getCategories(String username) throws IOException {
        return categoryApi.getCategories(username).execute().body();
    }
}
