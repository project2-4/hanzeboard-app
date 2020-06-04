package nl.hanze.hanzeboard.api.responses.staff;

import com.google.gson.annotations.SerializedName;

public class StaffProfileResponse {

    @SerializedName("office_location")
    private String officeLocation;

    @SerializedName("abbreviation")
    private String abbreviation;

    @SerializedName("status")
    private StaffStatusResponse statusResponse;

    public String getOfficeLocation() {
        return officeLocation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public StaffStatusResponse getStatusResponse() {
        return statusResponse;
    }
}
