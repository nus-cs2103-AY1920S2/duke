package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    // duke.task.Todo: Handle exceptional cases if any
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        for (String s : commandArgs) {
            if (s.equals("todo")) {
                continue;
            }
            description += s + " ";
        }

        // duke.task.Todo cannot be empty
        if (description.equals("")) {
            throw new IllegalArgumentException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }

        return new Todo(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}