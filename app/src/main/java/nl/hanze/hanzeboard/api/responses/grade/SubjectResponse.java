package nl.hanze.hanzeboard.api.responses.grade;

import com.google.gson.annotations.SerializedName;

public class SubjectResponse {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
