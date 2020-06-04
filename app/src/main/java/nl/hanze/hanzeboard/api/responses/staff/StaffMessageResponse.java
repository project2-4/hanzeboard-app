package nl.hanze.hanzeboard.api.responses.staff;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import nl.hanze.hanzeboard.api.responses.JsonMessageResponse;

public class StaffMessageResponse extends JsonMessageResponse {

    @SerializedName("message")
    private List<StaffResponse> staffResponseList;

    public List<StaffResponse> getObjectList() {
        return staffResponseList;
    }
}
