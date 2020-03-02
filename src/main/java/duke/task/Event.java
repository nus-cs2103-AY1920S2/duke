package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    // duke.task.Todo: Handle exceptional cases
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        String at = "";

        int toggle = 0;
        for (int i = 0; i < commandArgs.length; i++) {
            if (commandArgs[i].equals("(at:")) {
                toggle = 1;
                continue;
            }
            // current token is part of description
            if (toggle == 0) {
                description += commandArgs[i] + " ";
            }
            // current token is part of by
            else if (toggle == 1) {
                at += commandArgs[i] + " ";
            }
        }

        at = at.trim();
        at = at.substring(0, at.length() - 1);

        return new Event(description.trim(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}