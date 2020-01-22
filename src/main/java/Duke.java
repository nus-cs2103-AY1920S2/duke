import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        printIntro();
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] split = input.split(" ");
            String command = split[0];

            if (input.compareTo("bye") != 0) {
                if (input.compareTo("list") == 0) {
                    System.out.println(taskList.toString());
                } else if (command.compareTo("done") == 0) {
                    taskList.doTask(Integer.parseInt(split[1]) - 1);
                } else if (command.compareTo("todo") == 0) {
                    String description = input.substring(input.indexOf(" ") + 1);
                    Todo todo = new Todo(description, false);
                    taskList.addTask(todo);
                } else if (command.compareTo("event") == 0) {
                    String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                    String time = input.substring(input.indexOf("/") + 4);
                    Event event = new Event(description, false, time);
                    taskList.addTask(event);
                } else if (command.compareTo("deadline") == 0) {
                    String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                    String time = input.substring(input.indexOf("/") + 4);
                    Deadline deadline = new Deadline(description, false, time);
                    taskList.addTask(deadline);
                } else {
                    printLines("Sorry, invalid command. Try again with the following:\n     todo, deadline, event");
                }
            } else {
                printLines("Goodbye. See you again soon!");
                break;
            }
        }
    }

    public static void printIntro() {
        String intro = "    ____________________________________________________________\n" +
                "     Hello! :) I'm Duke.\n" +
                "     How can I help you today?\n" +
                "    ____________________________________________________________";
        System.out.println(intro);
    }

    public static void printLines(String content) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + content);
        System.out.println("    ____________________________________________________________");
    }
}