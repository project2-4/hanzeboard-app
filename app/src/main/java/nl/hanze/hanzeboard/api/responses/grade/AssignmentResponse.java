package nl.hanze.hanzeboard.api.responses.grade;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssignmentResponse {

    @SerializedName("name")
    private String name;

    @SerializedName("credits")
    private int credits;

    @SerializedName("subject")
    private SubjectResponse subjectResponse;

    @SerializedName("passed")
    private double passed;

    @SerializedName("avg_grade")
    private double avgGrade;

    @SerializedName("total_submissions")
    private double totalSubmissions;

    @SerializedName("grade_overview")
    private List<Integer> gradeOverview;

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public SubjectResponse getSubjectResponse() {
        return subjectResponse;
    }

    public double getPassed() {
        return passed;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public double getTotalSubmissions() {
        return totalSubmissions;
    }

    public List<Integer> getGradeOverview(){
        return gradeOverview;
    }
}
