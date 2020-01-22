import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("____________________________________________________________\n Hello! I'm Duke\n What can I do for you?\n____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            String sentence = sc.nextLine();
            if (sentence.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________\n Bye. Hope to see you again soon!\n____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________\n" + sentence + "\n____________________________________________________________");
            }
        }
    }
}
