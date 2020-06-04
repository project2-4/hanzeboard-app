package nl.hanze.hanzeboard.activities.overview.grades;

public class Grade {

    private double grade;
    private String assignment;

    /**
     * Constructor for the Grade class.
     *
     * @param grade the grade of the assignment.
     * @param assignment the corresponding assignment.
     */
    public Grade(double grade, String assignment){
        this.grade = grade;
        this.assignment = assignment;
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
     * Getter for the assignment property.
     *
     * @return the assignment property.
     */
    public String getAssignment() {
        return assignment;
    }

    /**
     * Setter for the grade property.
     *
     * @param grade the grade to be set.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Setter for the assignment property.
     *
     * @param assignment the assignment to be set.
     */
    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
}
