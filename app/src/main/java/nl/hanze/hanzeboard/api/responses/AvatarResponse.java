package nl.hanze.hanzeboard.api.responses;

import com.google.gson.annotations.SerializedName;

public class AvatarResponse {
    @SerializedName("message")
    private UserResponse user;

    public UserResponse getUser() {
        return user;
    }
}
