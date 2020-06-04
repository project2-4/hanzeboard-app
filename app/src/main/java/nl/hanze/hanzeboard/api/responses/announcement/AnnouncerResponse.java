package nl.hanze.hanzeboard.api.responses.announcement;

import com.google.gson.annotations.SerializedName;

public class AnnouncerResponse {

    @SerializedName("abbreviation")
    private String abbreviation;

    @SerializedName("user")
    private AnnouncerProfileResponse announcerProfileResponse;

    public String getAbbreviation() {
        return abbreviation;
    }

    public AnnouncerProfileResponse getAnnouncerProfileResponse() {
        return announcerProfileResponse;
    }
}
