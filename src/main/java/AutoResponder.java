import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoResponder {
    public static void main(String[] args) {
        String logo = "                _        _____                                 _           \n" +
                "     /\\        | |      |  __ \\                               | |          \n" +
                "    /  \\  _   _| |_ ___ | |__) |___  ___ _ __   ___  _ __   __| | ___ _ __ \n" +
                "   / /\\ \\| | | | __/ _ \\|  _  // _ \\/ __| '_ \\ / _ \\| '_ \\ / _` |/ _ \\ '__|\n" +
                "  / ____ \\ |_| | || (_) | | \\ \\  __/\\__ \\ |_) | (_) | | | | (_| |  __/ |   \n" +
                " /_/    \\_\\__,_|\\__\\___/|_|  \\_\\___||___/ .__/ \\___/|_| |_|\\__,_|\\___|_|   \n" +
                "                                        | |                                \n" +
                "                                        |_|                                ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you wish to do?");

        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        Pattern pDeadline = Pattern.compile("deadline (.+) /by (.+)");
        Pattern pEvent = Pattern.compile("event (.+) /at (.+)");
        Pattern pTodo = Pattern.compile("todo (.+)");
        Pattern pDone = Pattern.compile("done (\\d+)");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("--------------------------------------------");
                System.out.println("Farewell. Thank you for using AutoResponder.");
                System.out.println("--------------------------------------------");
                break;
            }

            System.out.println("--------------------------------------------");
            if (input.equals("list")) {
                for (int i = 1; i <= taskList.size(); ++i) {
                    System.out.println(i + ". " + taskList.get(i - 1));
                }
            } else if (pDone.matcher(input).find()) {
                Matcher m = pDone.matcher(input);
                m.find();
                int index = Integer.parseInt(m.group(1)) - 1;
                if (index < 0 || index >= taskList.size()) {
                    System.out.println("Index must correspond to list with size of " + taskList.size());
                } else {
                    taskList.set(index, taskList.get(index).makeCompleted());
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(index));
                }
            } else {
                if (pDeadline.matcher(input).find()) {
                    Matcher m = pDeadline.matcher(input); m.find();
                    Task t = new Deadline(m.group(1), m.group(2));
                    taskList.add(t);
                } else if (pEvent.matcher(input).find()) {
                    Matcher m = pEvent.matcher(input); m.find();
                    Task t = new Event(m.group(1), m.group(2));
                    taskList.add(t);
                } else if (pTodo.matcher(input).find()) {
                    Matcher m = pTodo.matcher(input); m.find();
                    Task t = new Todo(m.group(1));
                    taskList.add(t);
                } else {
                    System.out.println("No matching command found");
                    System.out.println("--------------------------------------------");
                    continue;
                }
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList.get(taskList.size() - 1));
            }
            System.out.println("--------------------------------------------");

        }

    }
}
