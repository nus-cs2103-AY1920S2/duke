import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    private static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void echo(Scanner sc) {
        String userInput = sc.nextLine();

        if (!userInput.equals("bye")) {
            System.out.println(userInput);
            System.out.println();
            Duke.echo(new Scanner(System.in));
        } else {
            Duke.printByeMsg();
        }
    }

    private static void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon! :)");
    }

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();

        Duke.greet();

        Duke.echo(new Scanner(System.in));
    }
}
