package guru.qa.niffler.page_element;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.component.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PeopleTableElement extends BaseComponent<PeopleTableElement> {

    public PeopleTableElement() {
        super($(".people-content table"));
    }
    private final SelenideElement
            peopleTable = $(".people-content tbody");

    private final String
            addFriendButton = "[data-tooltip-id='add-friend']",
            submitInvitationButton = "[data-tooltip-id='submit-invitation']",
            declineInvitationButton = "[data-tooltip-id='decline-invitation']",
            removeFriendButton = "[data-tooltip-id='remove-friend']";


    public PeopleTableElement verifyFriendsListNotEmpty() {
        peopleTable.$$("tr")
                .find(text("You are friends"))
                .shouldBe(visible);

        return this;
    }

    public PeopleTableElement verifyReceivedFriendsInvitation() {
        peopleTable.$("tr")
                .$(submitInvitationButton)
                .shouldBe(visible);

        return this;
    }

    public PeopleTableElement verifyPendingFriendsInvitation() {
        peopleTable.$$("tr")
                .find(text("Pending invitation"))
                .shouldBe(visible);

        return this;
    }

    public PeopleTableElement verifyExistingButtonRemoveFriend() {
        peopleTable.$("tr")
                .$(removeFriendButton)
                .shouldBe(visible);

        return this;
    }

    public PeopleTableElement verifyPendingInvitationToUser(String username) {
        peopleTable.$$("tr").filter(Condition.partialText("Pending invitation")).first()
                .shouldHave(text(username));

        return this;
    }

    public PeopleTableElement verifyExistingDeclineInvitationBtn() {
        peopleTable.$$("tr")
                .first()
                .$(declineInvitationButton)
                .shouldBe(visible);

        return this;
    }
}
