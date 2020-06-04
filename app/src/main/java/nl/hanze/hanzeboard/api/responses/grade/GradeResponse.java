package nl.hanze.hanzeboard.api.responses.grade;

import com.google.gson.annotations.SerializedName;

public class GradeResponse {

    @SerializedName("grade")
    private String grade;

    public String getGrade() {
        return grade;
    }
}
