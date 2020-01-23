import java.util.Arrays;

public class Chatbot {

    private String name;
    private TaskList tasks;

    public Chatbot(String name) {
        this.name = name;
        this.tasks = new TaskList();
    }

    String greet() {
        return "I'm busy. What do you want?";
    }

    /**
     * Parse todo command.
     * @param tokens The input tokens.
     * @return The reply to the todo command.
     * @throws DukeException The command syntax error.
     */
    String parseTodo(String[] tokens) throws DukeException {
        if (tokens.length < 1) {
            throw new DukeException("TODO requires a description.");
        }

        Item item = new Todo(String.join(" ", tokens));
        tasks.add(item);
        return "Added the task.\n"
                + "----------\n  "
                + item + "\n"
                + "----------\n"
                + "You have " + tasks.getLength() + " tasks in the list. :(";
    }

    /**
     * Parse deadline command.
     * @param tokens The input tokens.
     * @return The reply to the deadline command.
     * @throws DukeException The command syntax error.
     */
    String parseDeadline(String[] tokens) throws DukeException {
        if (tokens.length < 1) {
            throw new DukeException("DEADLINE requires a description.");
        }

        int keyPosition = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                keyPosition = i;
            }
        }

        if (keyPosition == -1) {
            throw new DukeException("DEADLINE requires a format of description /by deadline.");
        }

        String description = String.join(" ",
                Arrays.copyOfRange(tokens, 0, keyPosition));
        if (description.strip().length() < 1) {
            throw new DukeException("DEADLINE description cannot be empty.");
        }

        String deadline = String.join(" ",
                Arrays.copyOfRange(tokens, keyPosition + 1, tokens.length));
        if (deadline.strip().length() < 1) {
            throw new DukeException("DEADLINE deadline cannot be empty.");
        }

        Item item = new Deadline(description, deadline);
        tasks.add(item);
        return "Added the task.\n"
                + "----------\n  "
                + item + "\n"
                + "----------\n"
                + "You have " + tasks.getLength() + " tasks in the list. :(";
    }

    /**
     * Parse deadline command.
     * @param tokens The input tokens.
     * @return The reply to the deadline command.
     * @throws DukeException The command syntax error.
     */
    String parseEvent(String[] tokens) throws DukeException {
        if (tokens.length < 1) {
            throw new DukeException("EVENT requires a description.");
        }

        int keyPosition = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/at")) {
                keyPosition = i;
            }
        }

        if (keyPosition == -1) {
            throw new DukeException("EVENT requires a format of description /by deadline.");
        }

        String description = String.join(" ",
                Arrays.copyOfRange(tokens, 0, keyPosition));
        if (description.strip().length() < 1) {
            throw new DukeException("EVENT description cannot be empty.");
        }

        String time = String.join(" ",
                Arrays.copyOfRange(tokens, keyPosition + 1, tokens.length));
        if (time.strip().length() < 1) {
            throw new DukeException("EVENT date/time cannot be empty.");
        }

        Item item = new Event(description, time);
        tasks.add(item);
        return "Added the task.\n"
                + "----------\n  "
                + item + "\n"
                + "----------\n"
                + "You have " + tasks.getLength() + " tasks in the list. :(";
    }

    /**
     * Parse done command.
     * @param tokens The input tokens.
     * @return The reply to the done command.
     * @throws DukeException The command syntax error.
     */
    String parseDone(String[] tokens) throws DukeException {
        DukeException invalidIndex = 
                new DukeException("DONE requires an index in the list to mark.");
        int index;
        
        if (tokens.length != 1) {
            throw invalidIndex;
        }

        try {
            index = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
            throw invalidIndex;
        }

        if (index < 1 || index > tasks.getLength()) {
            throw invalidIndex;
        }

        tasks.markDone(index);
        return "Tsk! I've marked this as done, you owe me.\n"
                + "----------\n"
                + "  " + tasks.getItem(index) + "\n"
                + "----------";
    }

    /**
     * Parse delete command.
     * @param tokens The input tokens.
     * @return The reply to the done command.
     * @throws DukeException The command syntax error.
     */
    String parseDelete(String[] tokens) throws DukeException {
        DukeException invalidIndex = 
                new DukeException("DELETE requires an index in the list to mark.");
        int index;
        
        if (tokens.length != 1) {
            throw invalidIndex;
        }

        try {
            index = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
            throw invalidIndex;
        }

        if (index < 1 || index > tasks.getLength()) {
            throw invalidIndex;
        }

        Item item = tasks.getItem(index);
        tasks.deleteItem(index);
        return "Removed and never to be seen again.\n"
                + "----------\n"
                + "  " + item + "\n"
                + "----------\n"
                + "You have " + tasks.getLength() + " tasks in the list. :(";
    }

    /**
     * Parsing the user's input and returning a reply.
     * @param message The user input message.
     * @return Chatbot's reply to the message.
     */
    String parse(String[] tokens) throws DukeException {
        String command = tokens[0].toUpperCase();

        if (command.length() < 1) {
            throw new DukeException("I have made you speechless.");
        }

        tokens = Arrays.copyOfRange(tokens, 1, tokens.length);

        switch (command) {
        case "TODO":
            return parseTodo(tokens);
        case "DEADLINE":
            return parseDeadline(tokens);
        case "EVENT":
            return parseEvent(tokens);
        case "LIST":
            return "This is your useless list.\n"
                    + tasks.toString();
        case "DONE":
            return parseDone(tokens);
        case "DELETE":
            return parseDelete(tokens);
        case "BYE":
            return "Bye, see you never!";
        default:
            throw new DukeException("What's this? You're wasting my time!");
        }
    }

    /**
     * Reply the user input message and displaying error as reply.
     * @param message The input message.
     * @return The reply to the message.
     */
    String reply(String message) {
        try {
            String[] tokens = message.split(" ");
            return parse(tokens);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Display what the chatbot is saying.
     * @param message The message the chatbot is saying.
     */
    void say(String message) {
        System.out.println(String.format("%s: %s\n", name, message));
    }
}