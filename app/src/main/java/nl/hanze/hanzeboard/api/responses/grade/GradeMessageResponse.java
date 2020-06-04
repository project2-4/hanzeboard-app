package nl.hanze.hanzeboard.api.responses.grade;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import nl.hanze.hanzeboard.api.responses.JsonMessageResponse;

public class GradeMessageResponse extends JsonMessageResponse {

    @SerializedName("message")
    private List<GradeResponse> gradeResponseList;

    public List<GradeResponse> getObjectList() {
        return gradeResponseList;
    }
}
