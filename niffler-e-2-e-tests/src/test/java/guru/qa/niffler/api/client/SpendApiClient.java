package guru.qa.niffler.api.client;

import guru.qa.niffler.api.service.SpendApi;
import guru.qa.niffler.db.model.StatisticJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class SpendApiClient extends RestClient {

    private final SpendApi spendApi;

    public SpendApiClient() {
        super("http://127.0.0.1:8093");
        this.spendApi = retrofit.create(SpendApi.class);
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
}
