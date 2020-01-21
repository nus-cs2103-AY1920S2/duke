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
        String[] lists = new String[100];
        List list = new List();
        int count = 0;
        while (!sc.hasNext("bye")) {
            String temp = sc.nextLine();
            String[] tmp = temp.split(" ");
            if (temp.equals("list")) {
                System.out.println(list);
            } else if (tmp[0].equals("done")) {
                int index = Integer.parseInt(tmp[1]);
                list.items[index-1].markDone();
            } else {
                list.addItem(temp);
            }
        }
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
        return;
    }
}
