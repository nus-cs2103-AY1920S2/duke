import java.util.Scanner;
public class Duke {
    public static void HorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greeting(){
        HorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        HorizontalLine();
    }

    public static void exit(){
        HorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        HorizontalLine();
    }

    public static void echo(String line) {
        HorizontalLine();
        System.out.println(line);
        HorizontalLine();
    }
    public static void main(String[] args) {

        // Greet the user
        greeting();

        Scanner sc = new Scanner(System.in);

        //Output the command the user types
        // Exits when the user types 'bye'
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            // Output to the user
            echo(line);
            line = sc.nextLine();
        }

        // Say 'bye' to the user
        exit();
    }
}
