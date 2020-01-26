abstract class DateTimeTask extends Task {
    protected String dateTime;

    public DateTimeTask(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }
}