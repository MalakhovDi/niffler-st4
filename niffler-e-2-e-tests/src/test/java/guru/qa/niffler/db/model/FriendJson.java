package guru.qa.niffler.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FriendJson(
        @JsonProperty("username")
        String username) {
}
