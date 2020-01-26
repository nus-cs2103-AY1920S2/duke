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

            String word = myScanner.nextLine();
            System.out.println("________________________________________");

            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                break;
            } else if (word.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).getStatusIcon() + " " + list.get(i).getTask());
                }
                System.out.println("________________________________________");

            } else {

                String[] arrSplit = word.split(" ");
                if (arrSplit[0].equals("done")) {
                    int taskNumber =  Integer.valueOf(arrSplit[1]);
                    list.get(taskNumber - 1).markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(taskNumber - 1).getStatusIcon() +
                            " " + list.get(taskNumber - 1).getTask());

                } else {

                    list.add(new Task(word));
                    System.out.println("added: " + word);
                    System.out.println("________________________________________");
                }
            }
        }
    }
}
