package guru.qa.niffler.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.niffler.model.SpendJson;

import java.util.List;

public record StatisticByCategoryJson(
        @JsonProperty("category")
        String category,
        @JsonProperty("total")
        Double total,
        @JsonProperty("totalInUserDefaultCurrency")
        Double totalInUserDefaultCurrency,
        @JsonProperty("spends")
        List<SpendJson> spends) {

}