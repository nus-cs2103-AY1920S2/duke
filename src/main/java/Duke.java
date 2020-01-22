import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n I am at your service\n");
        String botReplyLine = "----------------------------------------------";


        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();


        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(botReplyLine);
                for (int i=0; i<arr.size(); i++) {
                    System.out.println(i +". "+ arr.get(i));
                }
                System.out.println(botReplyLine);

            } else {
                arr.add(userInput);
                System.out.println(botReplyLine + "\n Duke: added " + userInput + "\n" + botReplyLine);
            }
        }

    }
}

