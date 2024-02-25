package guru.qa.niffler.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrencyJson(
        @JsonProperty("currency")
        CurrencyValues currency,
        @JsonProperty("currencyRate")
        Double currencyRate) {
}