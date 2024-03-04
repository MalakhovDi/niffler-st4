package guru.qa.niffler.test;

import guru.qa.niffler.db.repository.SpendRepository;
import guru.qa.niffler.jupiter.annotation.GenerateCategory;
import guru.qa.niffler.jupiter.annotation.GenerateSpend;
import guru.qa.niffler.jupiter.annotation.GenerateSpendWithCategory;
import guru.qa.niffler.jupiter.extension.SpendRepositoryExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(SpendRepositoryExtension.class)
@Feature("Spendings")
public class SpendingTest extends BaseWebTest {
    private static SpendRepository spendRepository;
    @AfterAll
    static void deleteAllSpendsFromDb() {
        spendRepository.deleteAllSpendsInDb();
    }
    @GenerateCategory(
            category = "Обучение",
            username = "duck"
    )
    @GenerateSpend(
            username = "duck",
            description = "QA.GURU Advanced 4",
            amount = 72500.00,
            currency = CurrencyValues.RUB
    )
    @Test
    void spendingShouldBeDeletedByButtonDeleteSpending(SpendJson spend) {
        welcomePage.open().clickLoginAndGoToLoginPage()
                .loginInUser("duck", "12345")
                .deleteSpendingByButtonDelete(spend.description())
                .verifyEmptyListOfSpendings();
    }

    @GenerateSpendWithCategory(
            username = "bee",
            category = "Путешествия",
            description = "Тайланд",
            amount = 200000.00,
            currency = CurrencyValues.RUB
    )
    @Test
    @DisplayName("Пользователь может удалить Spending")
    void spendingShouldBeDeletedByButtonDeleteSpendingRest(SpendJson spend) throws IOException {
        welcomePage.open().clickLoginAndGoToLoginPage()
                .loginInUser("bee", "12345")
                .deleteSpendingByButtonDelete(spend.description())
                .verifyEmptyListOfSpendings();
    }

    @Test
    @Disabled
    @DisplayName("Проверяем список spendings в таблице")
    void checkSeveralSpendingsTest() throws IOException {
        String username = "duck";

        for (int i = 0; i < 2; i++) {
            spendApiClient.addRandomSpend(username);
        }

        SpendJson spendJson = spendApiClient.addRandomSpend(username);

        welcomePage.open().clickLoginAndGoToLoginPage()
                .loginInUser(username, "12345")
                .checkAllSpends(username).selectSpendingByIndex(0).unSelectSpendingByIndex(0)
                .selectSpendingByDescription(spendJson.description());
    }
}
