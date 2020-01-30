public class Deadline extends Task {

    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTaskType() {
        return "D";
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String myword = "";
        myword = myword + "[" + this.getTaskType() + "]"
                + " [" +super.getStatusIcon() + "] " + super.description +
                " (" + this.time + ")";

        return myword;
    }
}
