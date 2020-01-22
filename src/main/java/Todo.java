public class Todo extends Task {

    String formatted_todo_string = "";

    Todo(String description) {
        super(description);
    }

    @Override
    String format_tasks() throws DukeException {
        // Needed the space after "to do" due to space formatting.
        String[] splited_string = super.getDescription().split("todo ");

        if (splited_string.length < 2) {
            throw new DukeException("", "Todo");
        }

        StringBuilder task = new StringBuilder();

        for (int i = 1; i < splited_string.length; i++) {
            if (i == splited_string.length - 1) {
                task.append(splited_string[i]);
            } else {
                task.append(splited_string[i]).append(" ");
            }
        }
        return task.toString();
    }

    @Override
    void setDescription(String s) throws DukeException {
        super.setDescription(s);
    }


    @Override
    public String toString() {
        return " [" + Task_Codes.T + "]" + super.toString();
    }


}
