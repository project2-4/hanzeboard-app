package nl.hanze.hanzeboard.api.responses.staff;

import com.google.gson.annotations.SerializedName;

public class StaffStatusResponse {

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }
}
