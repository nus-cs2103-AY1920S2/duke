import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Bob.\nWhat can I do for you? \uD83D\uDE03");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        boolean exiting = false;
        while (exiting != true) {
            while (!str.equals("bye")) {
                System.out.println(str);
                str = sc.next();
            }
            System.out.println("Are you sure you want to leave me alone?\uD83E\uDD7A (y/n)");
            if (sc.next().equals("y")) {
                exiting = true;
            } else {
                System.out.println("I know you are the best!\uD83D\uDE06");
                str = sc.next();
            }
        }
        System.out.println("Bye. Hope to see you again soon!\uD83D\uDE1E");
    }
}
