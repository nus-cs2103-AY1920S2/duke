import java.time.LocalDate;

public class ToDo extends Task {

    public String type;
    public String tag;

    /**
     * Constructor for ToDo class
     * Takes in a description and sets it as the Task description
     *
     * @param description description of the ToDo task
     */

    public ToDo(String description) {
        super(description);
        this.type = "todo";
    }


    @Override
    public LocalDate getBy() {

        return LocalDate.parse("0000-00-00");
    }

    @Override
    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        if (super.getStatus() == 0) {
            return "[T][✗]" + super.toString();
        } else {
            return "[T][✓]" + super.toString();
        }
    }
}