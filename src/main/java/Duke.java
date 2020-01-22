import java.util.*;

public class Duke {
    Task[] list;
    int latest_index = 0;

    public void printList() {

        for (int i = 1; i < latest_index + 1; i++) {
            System.out.println(i + ". " + list[i-1].toString());
        }
        System.out.println("\n");
    }

    public void run() {

        System.out.println("Hello ! I'm Ashley\nOi What u want\n");

        list = new Task[100];
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                return;

            } else if (str.equals("list")) {
                printList();

            } else if (str.contains("done ")) {

                str = str.substring(5);
                int i = Integer.parseInt(str) - 1;

                if (!list[i].isDone) {
                    list[i].isDone = true;
                    System.out.println("Nice! You have done this:\n" + list[i].toString() + "\n");
                } else {
                    System.out.println("You have already done\n" + list[i].toString() + "\nNo need to do it again!\n");
                }

            } else {
                Task task = new Task(str);
                list[latest_index++] = task;
                System.out.println("added: " + str + "\n");

            }
        }
    }
}
