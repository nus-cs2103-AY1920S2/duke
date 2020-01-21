import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 1;
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");

        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("bye")) break;
            else {
                tasks.add(new Task(count, next));
                count++;
            }
            tasks.forEach(System.out::println);
        }
        //Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
