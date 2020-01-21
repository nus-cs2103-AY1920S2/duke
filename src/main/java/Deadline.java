public class Deadline extends Task {
    private String date;

    public Deadline(int id, String task, String date) {
        super(id, task);
        this.date = date;
    }

    @Override
    public String toString() {
        if (done) return  "[D][✓] " + task + " (by: " + date + ")";
        else return "[D][✗] " + task + " (by: " + date + ")";
    }
}
