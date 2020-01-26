public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // Todo: Handle exceptional cases
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        String by = "";

        int toggle = 0;
        for (int i = 0; i < commandArgs.length; i++) {
            if (commandArgs[i].equals("/by")) {
                toggle = 1;
                continue;
            }
            // current token is part of description
            if (toggle == 0) {
                description += commandArgs[i] + " ";
            }
            // current token is part of by
            else if (toggle == 1) {
                by += commandArgs[i] + " ";
            }
        }

        return new Deadline(description.trim(), by.trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}