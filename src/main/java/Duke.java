import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I 'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");

        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {

            System.out.println("________________________________________");
            String word = myScanner.nextLine();
            String[] arrSplit = word.split(" " , 2);
            String keyword = arrSplit[0];

            if (keyword.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                break;
            } else if (keyword.equals("list")) {
                System.out.println("Here are the task in your list");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.println("________________________________________");

            } else if (keyword.equals("done")) {
                int taskNumber = Integer.valueOf(arrSplit[1]);
                list.get(taskNumber - 1).markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(taskNumber - 1).getStatusIcon() +
                        " " + list.get(taskNumber - 1).getTask());
                System.out.println("________________________________________");
            } else if (keyword.equals("delete")) {
                int taskNumber = Integer.valueOf(arrSplit[1]);
                System.out.println("Noted. I've removed this task");
                System.out.println(list.get(taskNumber - 1));
                list.remove(taskNumber - 1);
                System.out.println("Now you have " + list.size() + " in the list.");

            } else {

                String therest = arrSplit[1];
                String[] arrSplit2 = therest.split("/", 2);
                if (keyword.equals("todo")) {
                    list.add(new Todo(arrSplit2[0]));
                } else if (keyword.equals("deadline")) {
                    list.add(new Deadline(arrSplit2[0], arrSplit2[1]));
                } else if (keyword.equals("event")) {
                    list.add(new Event(arrSplit2[0], arrSplit2[1]));
                }

                System.out.println("Got it. I 've added this task:");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " in the list.");


                System.out.println("________________________________________");
            }
        }
    }
}
