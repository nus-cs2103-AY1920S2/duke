import java.util.Scanner;

public class Duke {
    private static String indentation = "    ";
    private static String hori_line = "______________________________________";

    public static void main(String[] args) {

        // Init Duke
        Duke d = new Duke();

        // Greet user on start
        d.Greet();
        d.Loop();
    }

    private static void PrintWithIndent(String toPrint) {
        System.out.println(indentation + toPrint);
    }

    private void Greet() {
        PrintWithIndent(hori_line);
        PrintWithIndent("Hello! I'm Duke");
        PrintWithIndent("What can I do for you?");
        PrintWithIndent(hori_line);
    }

    private void Loop() {
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean is_exiting = false;

        do {
            input = in.nextLine();
            is_exiting = input.compareToIgnoreCase("bye") == 0;

            if (!is_exiting) {
                // Echo back user's input if not exiting
                PrintWithIndent(hori_line);
                PrintWithIndent(input);
                PrintWithIndent(hori_line);
            } else {
                PrintWithIndent(hori_line);
                PrintWithIndent("Bye. Hope to see you again soon!");
                PrintWithIndent(hori_line);
            }
        } while (!is_exiting);
    }
}
