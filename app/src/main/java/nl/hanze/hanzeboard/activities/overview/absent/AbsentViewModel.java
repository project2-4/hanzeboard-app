package nl.hanze.hanzeboard.activities.overview.absent;

import androidx.lifecycle.ViewModel;

public class AbsentViewModel extends ViewModel {

    private Absentee[] absentees = { new Absentee("M. Johnson", "MJOH","Absent"),
            new Absentee("D. Pittsburgh", "DPIT","Absent"),
            new Absentee("Y. Zhang", "YZHG","Absent"),
            new Absentee("K. Gardner", "KGAR","Absent"),
            new Absentee("P. Wright", "PWRG","Absent"),
            new Absentee("D. Salinas", "DSAL","Absent"),
            new Absentee("A. Estrada", "AEST","Absent"),
            new Absentee("H. Dawson", "HDAW","Absent")
    };

    /**
     * Getter for the absentees array.
     *
     * @return the absentees array.
     */
    public Absentee[] getAbsentees() {
        return absentees;
    }
}
