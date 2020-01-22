public class Deadline extends Task {
    public Deadline(String msg, String date) {
        super(msg);
        super.type = "[D]";
        super.time = "(by: " + date + ")";
    }
}
