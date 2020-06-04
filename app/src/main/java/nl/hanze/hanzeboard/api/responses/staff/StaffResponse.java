package nl.hanze.hanzeboard.api.responses.staff;

import com.google.gson.annotations.SerializedName;

public class StaffResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("profile")
    private StaffProfileResponse staffProfile;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public StaffProfileResponse getStaffProfileResponse() {
        return staffProfile;
    }
}
