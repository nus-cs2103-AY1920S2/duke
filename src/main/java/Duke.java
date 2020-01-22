import main.java.Echo;
import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> Tasks = new ArrayList<>();

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
            } else if (x.equals("list")) {
                System.out.println("-------------------------------------------------------------");
                for (int i = 0; i < Tasks.size(); i++) {
                    System.out.println(i+1 + ". " + Tasks.get(i));
                }
                System.out.println();
                System.out.println("-------------------------------------------------------------");
            } else {
                System.out.println("-------------------------------------------------------------");
                Tasks.add(x);
                System.out.println("added: " + echo_obj.echo(x));
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
            }
        }

    }
}
