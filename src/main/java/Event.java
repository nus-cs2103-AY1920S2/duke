public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Task createTask(String[] commandArgs) {
        String description = "";
        String at = "";

        int toggle = 0;
        for (int i = 0; i < commandArgs.length; i++) {
            if (commandArgs[i].equals("/at")) {
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

        return new Event(description.trim(), at.trim());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}