import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hey friend! I'm Duke V2.0.0 at your service\n" +
                "     What can I do for you today?\n" +
                "    ____________________________________________________________\n"
        );

        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        String input;
        List<String> list = new ArrayList<>();

        // Parse each command by user
        while (true) {
            // Get command entered by user
            try {
                 input = bufferedReader.readLine();
            } catch (IOException e) {
                System.out.println("Error in reading input");
                break;
            }

            switch (input) {
                case "bye":
                    // Exit program
                    prettyPrint("Sorry to see you go. Hope to see you again soon! (＾▽＾)／");
                    break;
                case "list":
                    String listItems = "";
                    Integer listSize = list.size();

                    // Print out all items in list
                    for(int i = 0; i < listSize; i++) {
                        listItems += (i + 1) + ". " + list.get(i);
                        if (i != listSize - 1) {
                            listItems += "\n     ";
                        }
                    }
                    listItems = listItems.equals("") ? "There is nothing on your list." : listItems;
                    prettyPrint(listItems);
                    break;
                default:
                    list.add(input);
                    prettyPrint("You have added " + input);
            }
        }
    }

    public static void prettyPrint(String line) {
        System.out.println(
                "    _____________________________DUKE___________________________\n" +
                "     " + line + "\n" +
                "    _________★゜・。。・゜゜・。。・゜☆゜・。。・゜゜・。。・゜★_______\n"
        );
    }
}
