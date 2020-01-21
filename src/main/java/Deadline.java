public class Deadline extends Task{
    public Deadline(String description, String date) {
        this.description = description;
        this.type = "D";
        this.date = date;
        this.isDone = false;
    }

    @Override
    public String printTaskDetails() {
        return String.format(
                "[%s][%s] %s (%s)",
                this.getType(),
                this.getStatusIcon(),
                this.getDescription(),
                this.getDate());
    }
}
