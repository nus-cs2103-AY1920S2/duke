import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        processlist(sc);
    }

    private static void processlist(Scanner sc) {
        String[] list = new String[100];
        int count = 0;
        while (!sc.hasNext("bye")) {
            String temp = sc.nextLine();
            if (temp.equals("list")) {
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + ". " + list[i]);
                }
            } else {
                count++;
                list[count] = temp;
            }
        }
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
        return;
    }
}
