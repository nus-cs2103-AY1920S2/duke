public class Deadline extends Task{
    String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "D , " + doneInt + " , " + name + " , " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
