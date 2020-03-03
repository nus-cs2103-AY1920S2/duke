package tasks;

public class Deadline extends Task {

    private String taskDeadline;

    public Deadline(String taskDetails, String taskDeadline) {
        super(taskDetails);
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String writeToFile() {
        return "D / " + super.writeToFile() + "/ " + taskDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + taskDeadline;
    }
}
