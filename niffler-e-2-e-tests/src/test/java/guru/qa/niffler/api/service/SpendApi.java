package guru.qa.niffler.api.service;

import guru.qa.niffler.db.model.StatisticJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface SpendApi {

    @POST("/addSpend")
    Call<SpendJson> addSpend(@Body SpendJson spend);

    @GET("/statistic")
    Call<List<StatisticJson>> getStatistic(@Query("username") String username,
                                           @Query("userCurrency") CurrencyValues userCurrency);

    @PATCH("/editSpend")
    Call<SpendJson> editSpend(@Body SpendJson spend);
}
