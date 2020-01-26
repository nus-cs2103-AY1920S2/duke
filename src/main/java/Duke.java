import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Welcome Text
        print("Hello! I'm Duke\nWhat can I do for you?");

        //Main Program Loop, possible change to Switch Case later on
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else {
                print(input);
            }
        }
    }

    //Custom print Method to print the
    static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________\n");
    }
}
