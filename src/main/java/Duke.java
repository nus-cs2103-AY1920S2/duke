import main.java.Echo;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        System.out.println("-------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        Echo echo_obj = new Echo();

        while(true) {
            String x = sc.nextLine();
            if (x.equals("bye")) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
                sc.close();
                System.exit(0);
            } else {
                System.out.println("-------------------------------------------------------------");
                System.out.println(echo_obj.echo(x));
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
            }
        }

    }
}
