import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        String[] input = new String[100];
        int i = 0;
        String current = "";
        while(!(current = sc.nextLine()).equals("bye")) {
            switch (current) {
                case "read book":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    added: " + current);
                    System.out.println("    ____________________________________________________________");
                    break;
                case "return book":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    added: " + current);
                    System.out.println("    ____________________________________________________________");
                    break;
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for(int count = 0; count < i; count++){
                        System.out.println("    " + (count+1) + ". " + input[count]);
                    }

                    System.out.println("    ____________________________________________________________");
                    break;
                default:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    " + current);
                    System.out.println("    ____________________________________________________________");
                    break;


            }
            input[i] = current;
            i++;

        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
