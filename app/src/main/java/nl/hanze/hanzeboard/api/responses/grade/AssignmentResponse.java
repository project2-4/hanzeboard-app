package nl.hanze.hanzeboard.api.responses.grade;

import com.google.gson.annotations.SerializedName;

public class AssignmentResponse {

    @SerializedName("name")
    private String name;

    @SerializedName("credits")
    private int credits;

    @SerializedName("subject")
    private SubjectResponse subjectResponse;


    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public SubjectResponse getSubjectResponse() {
        return subjectResponse;
    }
}
