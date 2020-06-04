package nl.hanze.hanzeboard.api.responses.announcement;

import com.google.gson.annotations.SerializedName;

public class AnnouncerProfileResponse {

    @SerializedName("full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }
}
