import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! :) I'm Duke\n" +
                "     How can I help you today?\n" +
                "    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.compareTo("bye") != 0) {
                if (input.compareTo("list") == 0) {
                    printList(lst);
                } else {
                    Task task = new Task(input);
                    lst.add(task);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     added: " + task.getContent());
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
            System.out.println("     " + (i+1) + ". " + task.getContent());
        }
        System.out.println("    ____________________________________________________________");
    }
}
