import java.util.*;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Scanner sc = new Scanner(System.in);
        String Hello = "Hello! I'm Duke\nWhat can i do for you?";
        System.out.println(Hello);

        int flag = 0;
        ArrayList<String> mylist = new ArrayList<>();

        while(flag == 0) {
            String input = sc.nextLine();
            String Line = "_________________________________________________________________";

            System.out.println(Line);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                flag = 1;
            } else if (input.equals("list")) {
                printlist(mylist);
            } else {
                 mylist.add(input);
                 System.out.println("added: " + input);
            }

            System.out.println(Line);
        }
    }

    private static void printlist(ArrayList<String> ls) {
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + ". " + ls.get(i));
        }
    }
}
