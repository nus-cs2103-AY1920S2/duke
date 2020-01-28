public class Deadline extends Task {
    public Deadline(String msg, String date) {
        super(msg);
        super.type = "D";
        super.time = date;
    }

    @Override
    public String writeToFile() {
        return "D , " + super.status + " ," + super.msg + " , " + super.time;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.status + "]" + super.msg + " (by: "
                + super.time + ")";
    }
}
