package nl.hanze.hanzeboard.api.responses;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    public UserResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
