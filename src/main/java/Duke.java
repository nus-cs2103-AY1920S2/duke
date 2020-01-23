import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingText = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greetingText);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                String byeText = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";
                System.out.println(byeText);
                break;
            } else if (input.equals("list")) {
                printList(taskList);
            } else if (input.contains("done")) {
                String[] inputArr = input.split(" ");
                int taskNum = Integer.parseInt(inputArr[1]);
                taskDone(taskList, taskNum);
            } else {
                addList(new Task(input), taskList);
            }
        }
    }

    public static void taskDone(ArrayList<Task> list, int taskNum) {
        Task doneTask = list.get(taskNum - 1);
        doneTask.toggleDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + doneTask + "\n" +
                "    ____________________________________________________________");
    }

    public static void addList(Task task, ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________\n" +
                "     added: " + task + "\n" +
                "    ____________________________________________________________");
        list.add(task);
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (Task s : list) {
            System.out.println("     " + (list.indexOf(s)+1) + ". " + s);
        }
        System.out.println("    ____________________________________________________________");
    }

}
