package guru.qa.niffler.page;

import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.message.SuccessMsg;
import guru.qa.niffler.page_element.HeaderMainPageElement;
import guru.qa.niffler.page_element.HistoryOfSpendingsElement;
import guru.qa.niffler.page_element.StatsElement;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class MainPage extends BasePage<MainPage> {

    private final HistoryOfSpendingsElement historyOfSpendingsElement = new HistoryOfSpendingsElement();
    private final HeaderMainPageElement headerMainPageElement = new HeaderMainPageElement();
    private final StatsElement statsElement = new StatsElement();
    public static final String URL = CFG.frontUrl() + "/main";

    @Override
    @Step("Ожидание загрузки главной страницы")
    public MainPage waitUntilLoaded() {
        historyOfSpendingsElement.waitUntilLoaded();
        return this;
    }

    @Step("Удалить Spending по названию {spendDescription}")
    public MainPage deleteSpendingByButtonDelete(String spendDescription) {
        historyOfSpendingsElement.deleteSpendingByButtonDeleteSpending(spendDescription);
        checkToastMessage(SuccessMsg.SPENDINGS_DELETED);
        return this;
    }

    @Step("Проверить пустой список Spendings")
    public MainPage verifyEmptyListOfSpendings() {
        historyOfSpendingsElement.verifyEmptyListOfSpendings();
        return this;
    }

    @Step("Перейти на страницу friends")
    public FriendsPage goToFriendsPage() {
        headerMainPageElement.clickFriendsPage();
        return new FriendsPage().waitUntilLoaded();
    }

    @Step("Перейти на страницу people")
    public PeoplePage goToPeoplePage() {
        headerMainPageElement.clickPeoplePage();
        return new PeoplePage().waitUntilLoaded();
    }

    @Step("Перейти на страницу профиля")
    public ProfilePage goToProfile() {
        headerMainPageElement.clickProfilePage();
        return new ProfilePage().waitUntilLoaded();
    }

    @Step("Проверка наличия раздела Статистика")
    public MainPage verifyStatisticsSectionShouldExist() {
        statsElement.verifyStatisticsSectionShouldExist();
        return this;
    }

    @Step("Проверить все spendings пользователя {username}")
    public MainPage checkAllSpends(String username) throws IOException {
        List<SpendJson> spends = spendApiClient.getSpends(username);
        historyOfSpendingsElement.checkSpends(spends.toArray(new SpendJson[spends.size()]));
        return this;
    }

    @Step("Выделить spend по индексу {index}")
    public MainPage selectSpendingByIndex(int index) {
        historyOfSpendingsElement.selectSpendingByIndex(index);
        return this;
    }

    @Step("Снять выделение spend по индексу {index}")
    public MainPage unSelectSpendingByIndex(int index) {
        historyOfSpendingsElement.unSelectSpendingByIndex(index);
        return this;
    }

    @Step("Выделить spend по описанию {description}")
    public MainPage selectSpendingByDescription(String description) {
        historyOfSpendingsElement.selectSpendingByDescription(description);
        return this;
    }

    @Step("Снять выделение spend по описанию {description}")
    public MainPage unSelectSpendingByDescription(String description) {
        historyOfSpendingsElement.selectSpendingByDescription(description);
        return this;
    }
}
