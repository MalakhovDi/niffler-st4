package guru.qa.niffler.page_element;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.db.model.CurrencyValues;
import guru.qa.niffler.page.component.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ProfileInformationElement extends BaseComponent<ProfileInformationElement> {
    public ProfileInformationElement() {
        super($(".profile-content .main-content__section-avatar"));
    }
    public ProfileInformationElement setFirstAndSurName(String firstName, String surName) {
        inputFirstName.setValue(firstName);
        inputSurName.setValue(surName);
        submitButton.scrollTo().click();
        return this;
    }    private final SelenideElement
            container = $(".profile-content .main-content__section-avatar"),
            profileName = container.$("figcaption"),
            defaultCurrencyValue = container.$(withText("Currency")).parent().$("[class*='singleValue']"),
            inputFirstName = $("[name=firstname]"),
            inputSurName = $("[name=surname]"),
            submitButton = $("[type=submit]");

    public ProfileInformationElement verifyDefaultCurrencyValue(CurrencyValues currencyValue) {
        defaultCurrencyValue.shouldHave(text(currencyValue.toString()));
        return this;
    }

    public ProfileInformationElement verifyProfileName(String username) {
        profileName.shouldHave(text(username));
        return this;
    }


}