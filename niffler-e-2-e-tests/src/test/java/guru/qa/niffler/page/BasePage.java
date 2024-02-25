package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.message.Msg;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage<T extends BasePage> {

    protected final SelenideElement toast = $("[class*='toast']");

    @SuppressWarnings("unchecked")
    @Step("Check toast {msg.getMessage()}")
    public T checkToastMessage(Msg msg) {
        toast.shouldHave(text(msg.getMessage()));
        return (T) this;
    }

    protected abstract T waitUntilLoaded();
}
