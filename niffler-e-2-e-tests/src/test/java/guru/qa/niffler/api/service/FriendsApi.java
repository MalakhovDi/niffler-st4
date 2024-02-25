package guru.qa.niffler.api.service;

import guru.qa.niffler.db.model.FriendJson;
import guru.qa.niffler.model.UserJson;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface FriendsApi {

    @GET("/friends")
    Call<List<UserJson>> friends(@Query("username") String username,
                                 @Query("includePending") boolean includePending);

    @GET("/invitations")
    Call<List<UserJson>> invitations(@Query("username") String username);

    @POST("/declineInvitation")
    Call<List<UserJson>> declineInvitation(@Query("username") String username,
                                           @Query("invitation") FriendJson invitation);

    @POST("/acceptInvitation")
    Call<List<UserJson>> acceptInvitation(@Query("username") String username,
                                          @Query("invitation") FriendJson invitation);

    @POST("/addFriend")
    Call<UserJson> addFriend(@Query("username") String username,
                             @Query("friend") FriendJson friend);

    @DELETE("/removeFriend")
    Call<List<UserJson>> removeFriend(@Query("username") String username,
                                      @Query("friendUsername") String friendUsername);
}
