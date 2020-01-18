public class Deadline extends Task {
    private String time;
    protected final String taskType = "Deadline";

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return(this.time);
    }
}

