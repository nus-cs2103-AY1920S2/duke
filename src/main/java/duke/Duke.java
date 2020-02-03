package duke;

import java.util.Scanner;

public class Duke {
    public void echo(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello I am duke.Duke.\n" + "What can I do for you?");

        Lister lister = new Lister(tasks, storage);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                lister.record(command);
            }
        }
    }

    public void showLoadingError() {
        System.out.println("The text file is empty...");
    }

}
