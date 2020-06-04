package nl.hanze.hanzeboard.api.responses.announcement;

import com.google.gson.annotations.SerializedName;

public class AnnouncementResponse {

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("poster")
    private AnnouncerResponse announcerResponse;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public AnnouncerResponse getAnnouncerResponse(){
        return announcerResponse;
    }
}
