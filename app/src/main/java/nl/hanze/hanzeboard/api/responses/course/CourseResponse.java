package nl.hanze.hanzeboard.api.responses.course;

import com.google.gson.annotations.SerializedName;

public class CourseResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
