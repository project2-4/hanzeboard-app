package nl.hanze.hanzeboard.activities.overview.grades;

import androidx.lifecycle.ViewModel;

public class GradesViewModel extends ViewModel {

    private Grade[] grades = {new Grade("9.5", "Algorithms and data structures"), new Grade("7.6", "OOP3 and Design Patterns"),
            new Grade("9.2", "Academic Writing"), new Grade("8.8", "Project 2.3 Software Engineering"),
            new Grade("7.9", "Discrete Mathematics I"), new Grade("7.4", "OOP4"),
            new Grade("5.5", "OOP5"), new Grade("10", "Web & Mobile Services"),
            new Grade("9.4", "French I"), new Grade("7.8", "French II"),
            new Grade("8.5", "Project 2.4 Web & Mobile Services and Web Development"), new Grade("6", "Research and Reporting Skills"),
            new Grade("P", "Artificial Intelligence Lab"), new Grade("7.3", "Machine Learning I "),
            new Grade("P", "Business Intelligence Lab"), new Grade("9.3", "Machine Learning II"),
            new Grade("8.4", "Japanese I"), new Grade("8.3", "Japanese II")
    };

    /**
     * Getter for the grades array.
     *
     * @return the grades array.
     */
    public Grade[] getGrades(){
        return grades;
    }


}
