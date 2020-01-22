import java.util.ArrayList;

public class Chatbot {

    private String name;
    private ArrayList<Item> items;

    public Chatbot(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    String greet() {
        return "I'm busy. What do you want?";
    }

    /**
     * Convert list to string and number it.
     * @return The string format of the list.
     */
    String listItems() {
        String output = "This is your list:\n";
        output += "----------\n";
        for (int i = 0; i < items.size(); i++) {
            output += String.format("%d. %s\n", i + 1, items.get(i));
        }
        output += "----------";
        return output;
    }

    /**
     * Parsing the user's input and returning a reply.
     * @param message The user input message.
     * @return Chatbot's reply to the message.
     */
    String parse(String message) {
        switch (message.toLowerCase()) {
        case "list":
            return listItems();
        case "bye":
            return "Bye, see you never!";
        default:
            items.add(new Item(message));
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