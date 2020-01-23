import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke chatbot = new Duke();

        chatbot.greet();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                chatbot.bye();
                break;
            } else {
                chatbot.process(command);
            }
        }
    }

    private void greet() {
        String greeting = "Hello. I'm Duke.\n"
                + "Oh, it's you, can you please stop coming?";

        say(greeting);
    }

    private void process(String speech) {
        say(speech);
    }

    private void bye() {
        String parting = "Bye. Please don't come back again or I'll call the cops.";

        say(parting);
    }

    private void say(String speech) {
        String separator = "\t============================================================\n";

        String[] lines = speech.split("\n");
        String indented = "\t " + String.join("\n\t ", lines) + "\n";

        String result = separator + indented + separator;

        System.out.println(result);
    }
}
