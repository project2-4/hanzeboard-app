package nl.hanze.hanzeboard.api.responses.grade;

import com.google.gson.annotations.SerializedName;

public class GradeResponse {

    @SerializedName("grade")
    private String grade;

    @SerializedName("assignment")
    private AssignmentResponse assignmentResponse;

    public String getGrade() {
        return grade;
    }

    public AssignmentResponse getAssignmentResponse() {
        return assignmentResponse;
    }
}
