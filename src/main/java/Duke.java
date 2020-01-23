import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    String[] tasks;
    int taskCount;

    public Duke() {
        tasks = new String[100];
        taskCount = 0;
    }

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
        String[] words = speech.split(" ");
        String firstWord = words[0];

        switch (firstWord) {
            case "list":
                say(getTasks());
                break;
            default:
                addTask(speech);
                say("Added: " + speech);
                break;
        }
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

    private void addTask(String task) {
        tasks[taskCount++] = task;
    }

    private String getTasks() {
        String result = "";

        for (int i = 0; i < taskCount; i++) {
            result += (i + 1)
                    + ". " + tasks[i] + "\n";
        }

        return result;
    }
}
