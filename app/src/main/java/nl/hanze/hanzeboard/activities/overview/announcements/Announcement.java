package nl.hanze.hanzeboard.activities.overview.announcements;

public class Announcement {

    private String title;
    private String announcer;
    private String date;
    private String content;
    private boolean expanded;

    /**
     * Constructor for the Announcement class.
     *
     * @param title the title of the announcement.
     * @param announcer the announcer of the announcement.
     * @param date the date of the announcement.
     * @param content the content of the announcement.
     */
    public Announcement(String title, String announcer, String date, String content){
        this.title = title;
        this.announcer = announcer;
        this.date = date;
        this.content = content;
        this.expanded = false;
    }

    /**
     * Getter for the title property.
     *
     * @return the title of the announcement.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the announcer property.
     *
     * @return the announcer property of the announcement.
     */
    public String getAnnouncer() {
        return announcer;
    }

    /**
     * Getter for the date property.
     *
     * @return the date property of the announcement.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the content property.
     *
     * @return the content property of the announcement.
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter for the expanded property.
     *
     * @return expanded property, true or false.
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Setter for the title property.
     *
     * @param title the title of the announcement to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for the announcer property.
     *
     * @param announcer the announcer of the announcement to be set.
     */
    public void setAnnouncer(String announcer) {
        this.announcer = announcer;
    }

    /**
     * Setter for the date property.
     *
     * @param date the date of the announcement to be set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter for the content property.
     *
     * @param content the content of the announcement to be set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Setter for the expanded property.
     *
     * @param expanded true or false.
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
