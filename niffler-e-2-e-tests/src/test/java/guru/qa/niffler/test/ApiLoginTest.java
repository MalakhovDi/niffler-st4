package guru.qa.niffler.test;

import guru.qa.niffler.db.model.UserAuthEntity;
import guru.qa.niffler.jupiter.annotation.ApiLogin;
import guru.qa.niffler.jupiter.annotation.UserDb;
import guru.qa.niffler.jupiter.extension.ApiLoginExtension;
import guru.qa.niffler.jupiter.extension.ContextHolderExtension;
import guru.qa.niffler.jupiter.extension.UserDbExtension;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ContextHolderExtension.class, UserDbExtension.class, ApiLoginExtension.class})
public class ApiLoginTest extends BaseWebTest {
    @Test
    @ApiLogin(user = @UserDb)
    void successfulApiLoginTest(UserAuthEntity userAuth) {
        open(mainPage.URL, MainPage.class).waitUntilLoaded()
                .goToProfile().verifyProfileName(userAuth.getUsername());
    }
    @Test
    @ApiLogin(username = "duck", password = "12345")
    void successApiLoginTest() {
        open(mainPage.URL, MainPage.class).waitUntilLoaded()
                .goToProfile().verifyProfileName("duck");
    }

    @Test
    @UserDb()
    void dbUserTest(UserAuthEntity userAuth) {
        welcomePage.open().clickLoginAndGoToLoginPage().loginInUser(
                userAuth.getUsername(),
                userAuth.getPassword()
        ).goToProfile().verifyProfileName(userAuth.getUsername());
    }
}
