import java.util.*;

public class Duke {
    String[] list;
    int latest_index = 0;

    public void printList() {

        for (int i = 1; i < latest_index + 1; i++) {
            System.out.println(i + ". " + list[i-1]);
        }
    }

    public void run() {

        System.out.println("Hello ! I'm Ashley\nOi What u want");

        list = new String[100];
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                return;

            } else if (str.equals("list")) {
                printList();

            }
            else {
                list[latest_index++] = str;
                System.out.println("added: " + str + "\n");
            }
        }
    }
}
