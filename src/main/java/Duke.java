import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    ArrayList<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
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
            case "todo":
                String todo = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                addTodo(todo);

                say("Another task? Oh well, here's the task:\n"
                        + "\t" + getTask(getTaskCount()) + "\n"
                        + "Now you have " + getTaskCount() + " tasks in the list.");
                break;
            case "deadline":
                String entry = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                String description = entry.split("/")[0].strip();
                String time = entry.split("/")[1];

                addDeadline(description, time);
                say("Another task? Oh well, here's the task:\n"
                        + "\t" + getTask(getTaskCount()) + "\n"
                        + "Now you have " + getTaskCount() + " tasks in the list.");
                break;
            case "event":
                String input = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                String event = input.split("/")[0].strip();
                String timeRange = input.split("/")[1];

                addEvent(event, timeRange);
                say("Another task? Oh well, here's the task:\n"
                        + "\t" + getTask(getTaskCount()) + "\n"
                        + "Now you have " + getTaskCount() + " tasks in the list.");
                break;
            case "list":
                say("Here are your procrastinated tasks:\n"
                        + getTasks());
                break;
            case "done":
                int index = Integer.parseInt(words[1]);
                tickTask(index);

                say("Yay. You've finally done this task:\n\t"
                        + getTask(index));
                break;
            default:
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
        tasks.add(new Task(task));
    }

    private void addTodo(String todo) {
        tasks.add(new Todo(todo));
    }

    private void addDeadline(String description, String time) {
        tasks.add(new Deadline(description, time));
    }

    private void addEvent(String description, String timeRange) {
        tasks.add(new Event(description, timeRange));
    }

    private void tickTask(int index) {
        tasks.get(index - 1).tick();
    }

    private String getTask(int index) {
        return tasks.get(index - 1).toString();
    }

    private int getTaskCount() {
        return tasks.size();
    }

    private String getTasks() {
        String result = "";

        int index = 1;
        for (Task t : tasks) {
            result += index++
                    + "." + t + "\n";
        }

        return result;
    }
}
