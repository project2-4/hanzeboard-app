package nl.hanze.hanzeboard.api.responses.course;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import nl.hanze.hanzeboard.api.responses.JsonMessageResponse;

public class CourseMessageResponse extends JsonMessageResponse {

    @SerializedName("message")
    private List<CourseResponse> courseResponseList;

    public List<CourseResponse> getObjectList() {
        return courseResponseList;
    }
}
