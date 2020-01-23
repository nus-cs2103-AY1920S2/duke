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
        String detail = "";
        String keyword = "";
        String deadline = "";
        Boolean flag = false;
        Item item;

        switch (tokens[0].toLowerCase()) {
        case "todo":
            item = new Todo(message.substring(5));
            break;
        case "deadline":
            for (int i = 1; i < tokens.length; i++) {
                String token = tokens[i];
                if (!flag) {
                    if (token.charAt(0) == '/') {
                        keyword = token.substring(1);
                        flag = true;
                    } else {
                        detail += token + " ";
                    }
                } else {
                    deadline += token + " ";
                }
            }

            item = new Deadline(detail.strip(), keyword, deadline.strip());
            break;
        case "event":
            for (int i = 1; i < tokens.length; i++) {
                String token = tokens[i];
                if (!flag) {
                    if (token.charAt(0) == '/') {
                        keyword = token.substring(1);
                        flag = true;
                    } else {
                        detail += token + " ";
                    }
                } else {
                    deadline += token + " ";
                }
            }

            item = new Event(detail.strip(), keyword, deadline.strip());
            break;
        case "list":
            return "This is your stupid list.\n" + tasks.toString();
        case "done":
            int index = Integer.parseInt(tokens[1]);
            tasks.markDone(index);
            return "Tsk! I've marked this as done, you owe me.\n----------\n  "
                    + tasks.getItem(index) + "\n----------";
        case "bye":
            return "Bye, see you never!";
        default:
            return "What's this? You're wasting my time!";
            // tasks.add(new Item(message));
            // return String.format("[Added] %s", message);
        }

        tasks.add(item);
        return "Added the task.\n----------\n  " + item
                + "\n----------\nYou have " + tasks.getLength()
                + " tasks in the list. :(";
    }

    /**
     * Display what the chatbot is saying.
     * @param message The message the chatbot is saying.
     */
    void say(String message) {
        System.out.println(String.format("%s: %s\n", name, message));
    }
}