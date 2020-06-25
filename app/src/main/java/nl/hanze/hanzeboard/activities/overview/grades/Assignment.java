package nl.hanze.hanzeboard.activities.overview.grades;

import java.util.List;

public class Assignment {

    private String assignmentName;
    private int credits;
    private double grade;
    private double passed;
    private double submissions;
    private double averageGrade;
    private List<Integer> gradeOverview;

    /**
     * Constructor for the Grade class.
     *
     * @param grade the grade of the assignment.
     * @param assignmentName the corresponding assignment.
     */
    public Assignment(String assignmentName, int credits, double grade, double passed, double submissions, double averageGrade, List<Integer> gradeOverview){
        this.assignmentName = assignmentName;
        this.credits = credits;
        this.grade = grade;
        this.passed = passed;
        this.submissions = submissions;
        this.averageGrade = averageGrade;

        this.gradeOverview = gradeOverview;
    }

    /**
     * Getter for the assignment property.
     *
     * @return the assignment property.
     */
    public String getAssignment() {
        return assignmentName;
    }

    /**
     * Getter for the credits property.
     *
     * @return the credits property.
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Getter for the grade property.
     *
     * @return the grade property.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Getter for the passed property.
     *
     * @return the passed property.
     */
    public double getPassed() {
        return passed;
    }

    /**
     * Getter for the submissions property.
     *
     * @return the submissions property.
     */
    public double getSubmissions() {
        return submissions;
    }

    /**
     * Getter for the averageGrade property.
     *
     * @return the averageGrade property.
     */
    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * Getter for the gradeOverview list.
     *
     * @return gradeOverview list.
     */
    public List<Integer> getGradeOverview() {
        return gradeOverview;
    }


}
