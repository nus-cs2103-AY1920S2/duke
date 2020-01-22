import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! :) I'm Duke.\n" +
                "     How can I help you today?\n" +
                "    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] split = input.split(" ");

            if (input.compareTo("bye") != 0) {
                if (input.compareTo("list") == 0) {
                    printList(lst);
                } else if (split[0].compareTo("done") == 0) {
                    Task currentTask = lst.get(Integer.parseInt(split[1]) - 1);
                    currentTask.setIsDone(true);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Good job, you completed a task!");
                    System.out.println("     [\u2713] " + currentTask.getContent());
                    System.out.println("    ____________________________________________________________");
                } else {
                    Task task = new Task(input, false);
                    lst.add(task);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Added: " + task.getContent());
                    System.out.println("    ____________________________________________________________");
                }
            } else {
                System.out.println("    ____________________________________________________________\n"
                        + "     Goodbye. See you again soon!\n"
                        + "    ____________________________________________________________");
                break;
            }
        }
    }

    public static void printList(ArrayList<Task> lst) {
        System.out.println("    ____________________________________________________________");
        if (lst.isEmpty()) {
            System.out.println("     Sorry, your list is currently empty!");
        }
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            String str = "     " + (i+1) + ".";
            str += task.getIsDone() + task.getContent();
            System.out.println(str);
        }
        System.out.println("    ____________________________________________________________");
    }
}
