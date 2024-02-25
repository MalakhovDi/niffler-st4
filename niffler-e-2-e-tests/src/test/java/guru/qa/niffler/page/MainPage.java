package guru.qa.niffler.page;

import guru.qa.niffler.page.message.SuccessMsg;
import guru.qa.niffler.page_element.HeaderMainPageElement;
import guru.qa.niffler.page_element.HistoryOfSpendingsElement;
import guru.qa.niffler.page_element.StatsElement;
import io.qameta.allure.Step;

public class MainPage extends BasePage<MainPage> {

    private final HistoryOfSpendingsElement HistoryOfSpendingsElement = new HistoryOfSpendingsElement();
    private final HeaderMainPageElement headerMainPageElement = new HeaderMainPageElement();
    private final StatsElement statsElement = new StatsElement();

    @Override
    @Step("Ожидание загрузки главной страницы")
    public MainPage waitUntilLoaded() {
        HistoryOfSpendingsElement.waitUntilLoaded();
        return this;
    }

    @Step("Удалить Spending по названию {spendDescription}")
    public MainPage deleteSpendingByButtonDelete(String spendDescription) {
        HistoryOfSpendingsElement.deleteSpendingByButtonDeleteSpending(spendDescription);
        checkToastMessage(SuccessMsg.SPENDINGS_DELETED);
        return this;
    }

    @Step("Проверить пустой список Spendings")
    public MainPage verifyEmptyListOfSpendings() {
        HistoryOfSpendingsElement.verifyEmptyListOfSpendings();
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
}
