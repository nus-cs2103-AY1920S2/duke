public class Chatbot {

    private String name;

    public Chatbot(String name) {
        this.name = name;
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
        if (message.toLowerCase().equals("bye")) {
            return "Bye, see you never!";
        }
        return message;
    }

    /**
     * Display what the chatbot is saying.
     * @param message The message the chatbot is saying.
     */
    void say(String message) {
        System.out.println(String.format("%s: %s\n", name, message));
    }
}