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
            String description = "";
            String output = "";
            String[] inputArr = input.split(" ");
            if (inputArr[0].equals("done")) {
                description = "Nice! I've marked this task as done: \n";
                int taskNumber = Integer.parseInt(inputArr[1]);
                Task task = arrList.get(taskNumber - 1);
                task.completeTask();
                output = description + task.toString();
            } else if (input.equals("list")) {
                description = "Here are the tasks in your list: \n";
                String currentList = getCurrentList();
                output = description + currentList;
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                Task newTask = new Task(input);
                arrList.add(newTask);
                output = "added: " + input;
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
