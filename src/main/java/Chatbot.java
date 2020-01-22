import java.util.ArrayList;

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
     * Parsing the user's input and returning a reply.
     * @param message The user input message.
     * @return Chatbot's reply to the message.
     */
    String parse(String message) {
        String[] tokens = message.split(" ");
        switch (tokens[0].toLowerCase()) {
        case "list":
            return "This is your stupid list.\n" + tasks.toString();
        case "done":
            int index = Integer.parseInt(tokens[1]);
            tasks.markDone(index);
            return "Tsk! I've marked this, you owe me.\n" + tasks.getItem(index);
        case "bye":
            return "Bye, see you never!";
        default:
            tasks.add(message);
            return String.format("[Added] %s", message);
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