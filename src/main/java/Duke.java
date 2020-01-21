import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineBreak = "_________________________" +
                "_________________________";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 1;

        System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);

        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("bye")) break;
            else if (next.equals("list")) {
                System.out.println(lineBreak);
                tasks.forEach(System.out::println);
                System.out.println(lineBreak);
            } else {
                tasks.add(new Task(count, next));
                count++;
                System.out.println(lineBreak);
                System.out.println("added: " + next);
                System.out.println(lineBreak);
            }
        }
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
