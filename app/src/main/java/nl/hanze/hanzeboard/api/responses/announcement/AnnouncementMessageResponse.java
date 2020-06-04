package nl.hanze.hanzeboard.api.responses.announcement;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import nl.hanze.hanzeboard.api.responses.JsonMessageResponse;

public class AnnouncementMessageResponse extends JsonMessageResponse {

    @SerializedName("message")
    private List<AnnouncementResponse> announcementResponseList;

    public List<AnnouncementResponse> getObjectList() {
        return announcementResponseList;
    }
}
