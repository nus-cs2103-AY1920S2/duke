public class Deadline extends Task {
    protected String dayTime;

    public Deadline(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    public String getDayTime() {
        return dayTime;
    }
}
