package guru.qa.niffler.page.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SuccessMsg implements Msg {
    PROFILE_SUCCESSFULLY_UPDATED("Profile successfully updated"),
    SPENDINGS_DELETED("Spendings deleted");

    private final String msg;

    @Override
    public String getMessage() {
        return msg;
    }
}
