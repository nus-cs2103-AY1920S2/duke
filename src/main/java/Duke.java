import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    ArrayList<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
        loadList();
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
                if (words.length > 1) {
                    String todo = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    addTodo(todo);
                    say("Another task? Oh well, here's the task:\n"
                            + "\t" + getTask(getTaskCount()) + "\n"
                            + "Now you have " + getTaskCount() + " tasks in the list.");
                } else {
                    say("Your todo is as empty as your brain. Try again properly.");
                }
                saveList();
                break;
            case "deadline":
                String entry = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                String description = entry.split("/")[0].strip();
                String time = entry.split("/")[1];

                addDeadline(description, time);
                say("Another task? Oh well, here's the task:\n"
                        + "\t" + getTask(getTaskCount()) + "\n"
                        + "Now you have " + getTaskCount() + " tasks in the list.");
                saveList();
                break;
            case "event":
                String input = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                String event = input.split("/")[0].strip();
                String timeRange = input.split("/")[1];

                addEvent(event, timeRange);
                say("Another task? Oh well, here's the task:\n"
                        + "\t" + getTask(getTaskCount()) + "\n"
                        + "Now you have " + getTaskCount() + " tasks in the list.");
                saveList();
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
            case "delete":
                int number = Integer.parseInt(words[1]);

                say("Giving up already? Removed this:\n\t"
                        + popTask(number));
                break;
            default:
                say("Unknown command. Program it yourself or get a dictionary.");
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

    private void saveList() {
        try {
            File f = new File(Paths.get("data", "duke.txt").toString());
            f.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(f);
            Coder c = new Coder();
            for (Task t : tasks) {
                fw.append(c.encode(t) + "\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {
        try {
            File f = new File(Paths.get("data", "duke.txt").toString());
            FileReader fr = new FileReader(f);
            Scanner sc = new Scanner(fr);
            Coder c = new Coder();

            while (sc.hasNextLine()) {
                String code = sc.nextLine();
                tasks.add(c.decode(code));
            }
        } catch (FileNotFoundException e) {
            return;
        }
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

    private String popTask(int index) {
        return tasks.remove(index - 1).toString();
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
