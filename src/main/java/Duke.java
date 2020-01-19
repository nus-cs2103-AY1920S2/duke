import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

public class Duke {
    /**
     * main method for Duke.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> storedText = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am \n" + logo + "your personal buddy. What's up?\n" +
                            "____________________________________________________________\n");

        while(true) {
            String input = scan.nextLine(); //reads in user input

            switch(input) {
                case "bye":
                    divider("Aww okay, see you next time!");
                    return;
                case "list":
                    if (storedText.size() < 1) {
                        divider("nothing in list");
                        break;
                    }
                    divider("");
                    int index = 1;
                    String output = "";
                    for (String s: storedText) {
                        output = index + ". " + s;
                        System.out.println(output);
                    }
                    divider("");
                    break;
                default:
                    storedText.add(input); //stores input to storedText List
                    divider("added " + input);
            }
        }
    }

    private static void divider(String s) {
        if (s.length() > 0) {
            System.out.println("____________________________________________________________\n"
                    + s + "\n"
                    + "____________________________________________________________\n");
            return;
        }
        System.out.println("____________________________________________________________\n");
    }
}
