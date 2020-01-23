import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        BufferedReader br = null;

        String[] taskArray = new String[200];
        int nextInsertPin = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________________\n";

        String greeting = line +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line;

        String exitKey = "bye";
        String viewListKey = "list";

        String exitGreeting = line + "Bye. Hope to see you again soon!\n" + line;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(greeting);

            while (true) {
                String input = br.readLine();

                if (exitKey.equals(input)) {
                    System.out.println(exitGreeting);
                    System.exit(0);
                } else if (viewListKey.equals(input)) {
                    String listOverView = line;
                    for (int i = 0; i < taskArray.length; i++) {
                        if (taskArray[i] == null) {
                            break;
                        }
                        listOverView = listOverView + taskArray[i] + "\n";
                    }
                    listOverView = listOverView + line;
                    System.out.println(listOverView);
                }
                else {
                    taskArray[nextInsertPin] = input;
                    nextInsertPin ++;
                    System.out.println(line + "added: " + input + "\n" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
