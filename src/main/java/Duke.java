import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.printf("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> newList = new ArrayList<Task>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                if (newList.size() == 0) {
                    System.out.printf("You have no tasks in your list\n");
                } else {
                    System.out.printf("Here are the tasks in your list:\n");
                    for (int i = 0; i < newList.size(); i += 1) {
                        System.out.printf("%d. %s\n", i + 1, newList.get(i).toString());
                    }
                }
            } else if (command.startsWith("done ")) {
                String[] arr = command.split(" ");
                int i = Integer.valueOf(arr[1]) - 1;
                Task task = newList.get(i);
                newList.get(i).markAsDone();
                System.out.printf("Nice! I've marked this task as done: \n%s\n",
                        newList.get(i).toString());
            } else {
                String[] arr = command.split("/");
                String description = (arr[0].split(" ", 2))[1];
                if (command.startsWith("todo ")) {
                    newList.add(new ToDo(description));
                } else if(command.startsWith("deadline ")) {
                    newList.add(new Deadline(description, arr[1]));
                } else if(command.startsWith("event ")) {
                    newList.add(new Event(description, arr[1]));
                }
                System.out.printf("Got it. I've added this task:\n%s\n" +
                        "Now you have %d tasks in the list.\n",
                        newList.get(newList.size() - 1).toString(), newList.size());
            }
            command = sc.nextLine();
        }

        System.out.printf("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
