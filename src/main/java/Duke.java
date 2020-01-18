import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> arrList;

    public static void main(String[] args) {

        // Introduction

        String intro =
            "Hello! I'm Duke\n" +
            "What can I do for you?";

        System.out.println(stringWrapper((intro)));

        // Initialisation

        Scanner scanner = new Scanner(System.in);
        arrList = new ArrayList<>(100);

        // User Input

        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);
            String output = "";
            String[] inputArr = input.split(" ");
            String instruction = inputArr[0];
            if (instruction.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (instruction.equals("done")) {
                int taskNumber = Integer.parseInt(inputArr[1]);
                Task task = arrList.get(taskNumber - 1);
                task.completeTask();
                output = "Nice! I've marked this task as done: \n" + task.toString();
            } else if (instruction.equals("list")) {
                String currentList = getCurrentList();
                output = "Here are the tasks in your list: \n" + currentList;
            } else {
                int idx = input.indexOf(" ");
                String taskInput = input.substring(idx + 1);
                String[] taskInputArr = taskInput.split("/");
                String taskName = taskInputArr[0];
                Task newTask = null;
                if (instruction.equals("todo")) {
                    newTask = new ToDo(taskName);
                } else if (instruction.equals("deadline")) {
                    String dateTime = taskInputArr[1];
                    idx = dateTime.indexOf(" ");
                    dateTime = dateTime.substring(idx + 1);
                    newTask = new Deadline(taskName, dateTime);
                } else if (instruction.equals("event")) {
                    String dateTime = taskInputArr[1];
                    idx = dateTime.indexOf(" ");
                    dateTime = dateTime.substring(idx + 1);
                    newTask = new Event(taskName, dateTime);
                }
                arrList.add(newTask);
                output = "Got it. I've added this task: \n"
                        + newTask.toString() + "\n" +
                        "Now you have " + arrList.size()
                        + " tasks in the list.";
            }
            output = stringWrapper(output);
            System.out.println(output);
        }

    }

    private static String getCurrentList() {
        String list = "";
        for (int i = 0; i < arrList.size(); i++) {
            String count = (i + 1) + "";
            String task = arrList.get(i).toString();
            list += count + ". " + task;
            if (i != arrList.size() - 1) {
                list += "\n";
            }
        }
        return list;
    }

    private static String stringWrapper(String string) {
        return "____________________\n" + string + "\n" + "____________________\n";
    }

}
