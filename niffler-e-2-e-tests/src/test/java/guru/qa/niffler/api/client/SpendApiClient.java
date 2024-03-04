package guru.qa.niffler.api.client;

import com.github.javafaker.Faker;
import guru.qa.niffler.api.service.CategoryApi;
import guru.qa.niffler.api.service.SpendApi;
import guru.qa.niffler.db.model.StatisticJson;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SpendApiClient extends RestClient {

    private final SpendApi spendApi;
    private final CategoryApi categoryApi;
    private final Faker faker = new Faker();

    public SpendApiClient() {
        super("http://127.0.0.1:8093");
        this.spendApi = retrofit.create(SpendApi.class);
        this.categoryApi = retrofit.create(CategoryApi.class);
    }

    @Step("POST addSpend")
    public SpendJson addSpend(SpendJson spend) throws IOException {
        return spendApi.addSpend(spend).execute().body();
    }

    @Step("GET getStatistic {username}")
    public List<StatisticJson> getStatistic(String username, CurrencyValues userCurrency) throws IOException {
        return spendApi.getStatistic(username, userCurrency).execute().body();
    }

    @Step("PATCH editSpend")
    public SpendJson editSpend(SpendJson spend) throws IOException {
        return spendApi.editSpend(spend).execute().body();
    }

    @Step("DELETE deleteSpends {username}")
    public void deleteSpends(String username) throws IOException {
        List<SpendJson> spends = spendApi.getSpends(username).execute().body();
        List<String> ids = spends.stream()
                .map(s -> s.id().toString())
                .toList();
        spendApi.deleteSpends(username, ids).execute();
    }

    @Step("Spend create {username}")
    public SpendJson addRandomSpend(String username) throws IOException {
        String randomCategory = faker.job().position();

        CategoryJson categoryJson = new CategoryJson(
                null,
                randomCategory,
                username
        );

        categoryApi.addCategory(categoryJson).execute();

        SpendJson spendJson = new SpendJson(
                null,
                new Date(),
                randomCategory,
                CurrencyValues.RUB,
                faker.number().randomDouble(2, 100, 100000),
                faker.commerce().productName(),
                username
        );

        return spendApi.addSpend(spendJson).execute().body();
    }

    @Step("GET getSpends {username}")
    public List<SpendJson> getSpends(String username) throws IOException {
        return spendApi.getSpends(username).execute().body();
    }
}
