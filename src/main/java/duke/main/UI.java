package duke.main;

public class UI {
    //Custom welcome Method to print a welcome message
    static void welcome() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    //Custom print Method to print simple inputs
    public static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________\n");
    }
}
