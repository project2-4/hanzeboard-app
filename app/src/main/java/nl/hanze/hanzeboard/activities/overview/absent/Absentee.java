package nl.hanze.hanzeboard.activities.overview.absent;

public class Absentee {

    private String name;
    private String abbreviation;
    private String status;

    /**
     * Constructor for the Absentee class.
     *
     * @param name name of the absentee.
     * @param abbreviation abbreviation of the absentee.
     * @param status the status of the absentee.
     */
    public Absentee(String name, String abbreviation, String status){
        this.name = name;
        this.abbreviation = abbreviation;
        this.status = status;
    }

    /**
     * Getter for the name property.
     *
     * @return the name of the absentee.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the abbreviation property.
     *
     * @return the abbreviation of the absentee.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Getter for the status property.
     *
     * @return the status of the absentee.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the name property.
     *
     * @param name the name of the absentee to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the abbreviation property.
     *
     * @param abbreviation the abbreviation of the absentee to be set.
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Setter for the status property.
     *
     * @param status the status of the absentee to be set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
