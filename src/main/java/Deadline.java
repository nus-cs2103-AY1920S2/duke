public class Deadline extends Task {
    protected String byTime;

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = byTime;
    }

    public String getShortName() {
        return "D";
    }

}
