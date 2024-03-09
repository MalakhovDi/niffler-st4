package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import guru.qa.niffler.api.client.*;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.page.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Locale;

@ExtendWith({BrowserExtension.class})
public abstract class BaseWebTest {
    static {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1980x1024";
    }

    protected final FriendsPage friendsPage = new FriendsPage();
    protected final LoginPage loginPage = new LoginPage();
    protected final MainPage mainPage = new MainPage();
    protected final PeoplePage peoplePage = new PeoplePage();
    protected final ProfilePage profilePage = new ProfilePage();
    protected final RegisterPage registerPage = new RegisterPage();
    protected final WelcomePage welcomePage = new WelcomePage();
    protected final CategoryApiClient categoryApiClient = new CategoryApiClient();
    protected final GhApiClient ghApiClient = new GhApiClient();
    protected final SpendApiClient spendApiClient = new SpendApiClient();
    protected final UserApiClient userApiClient = new UserApiClient();
    protected final FriendsApiClient friendsApi = new FriendsApiClient();
    protected final CurrencyApiClient currencyApiClient = new CurrencyApiClient();
    protected final Faker faker = new Faker(Locale.ENGLISH);

    protected static final Config CFG = Config.getInstance();
}
