public class Deadline extends Task {
    protected String word;
    protected String date;

    public Deadline(String description, String word, String date) {
        super(description);
        this.word = word;
        this.date = date;
    }

    public String getType() {
        return "D";
    }

    public String getTask() {
        return description + "(" +word+ ": " +date+ ")";
    }
}
