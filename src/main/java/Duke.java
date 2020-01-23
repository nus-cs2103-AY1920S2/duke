import java.util.*;

public class Duke {
    Task[] list;
    int latest_index = 0;

    public void printList() {

        for (int i = 1; i < latest_index + 1; i++) {
            System.out.println(i + ". " + list[i-1].toString());
        }
    }

    public void run() {

        System.out.println("Hello ! I'm Ashley Bot\nOi What u want\n");

        list = new Task[100];
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (str.isEmpty()) {
                continue;
            }

            System.out.println("--------------------------------------------------------------");
            if (str.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                return;

            } else if (str.equals("list")) {
                printList();

            } else if (str.contains("done ")) {

                str = str.substring(5);
                str = str.trim();
                int i = Integer.parseInt(str) - 1;

                if (!list[i].isDone) {
                    list[i].isDone = true;
                    System.out.println("Nice! You have done this:\n" + list[i].toString());
                } else {
                    System.out.println("You have already done\n" + list[i].toString() + "\nNo need to do it again!");
                }

            } else if (str.contains("todo ")) {

                str = str.substring(5);
                str = str.trim();
                Todo todo = new Todo(str);
                list[latest_index++] = todo;

                System.out.println("Got it. I have added this task:\n" + todo.toString() +
                        "\nNow you have a total of " + latest_index + " Tasks in your list");

            } else if (str.contains("deadline ")) {

                str = str.substring(9);
                str = str.trim();
                String[] strings = str.split("/by");

                Deadline deadline = new Deadline(strings[0],strings[1]);
                list[latest_index++] = deadline;

                System.out.println("Got it. I have added this task:\n" + deadline.toString() +
                        "\nNow you have a total of " + latest_index + " Tasks in your list");

            } else if (str.contains("event ")) {

                str = str.substring(6);
                str = str.trim();
                String[] strings = str.split("/at");

                Event event = new Event(strings[0],strings[1]);
                list[latest_index++] = event;

                System.out.println("Got it. I have added this task:\n" + event.toString() +
                        "\nNow you have a total of " + latest_index + " Tasks in your list");



            } else {
                Task task = new Task(str);
                list[latest_index++] = task;
                System.out.println("General task added: " + str);

            }
            System.out.println("--------------------------------------------------------------\n\n");
        }
    }
}