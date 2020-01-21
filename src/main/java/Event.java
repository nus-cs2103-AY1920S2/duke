public class Event extends Task {
    protected String word;
    protected String date;

    public Event(String description, String word, String date) {
        super(description);
        this.word = word;
        this.date = date;
    }

    public String getType() {
        return "E";
    }

    public String getTask() {
        return description + "(" +word+ ": " +date+ ")";
    }
}
