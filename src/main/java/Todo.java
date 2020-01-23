public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static Task createTask(String[] commandArgs) {
        String description = "";
        for (String s : commandArgs) {
            if (s.equals("todo")) {
                continue;
            }
            description += s + " ";
        }

        return new Todo(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}