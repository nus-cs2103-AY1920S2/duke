public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    // Todo: Handle exceptional cases if any
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        for (String s : commandArgs) {
            if (s.equals("todo")) {
                continue;
            }
            description += s + " ";
        }

        // Todo cannot be empty
        if (description.equals("")) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        return new Todo(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}