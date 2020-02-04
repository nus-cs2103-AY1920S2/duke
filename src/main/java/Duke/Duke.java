package duke;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Konnichiwa! I am Duke the cat! What can I do for you? meow~ (^.___.^)");
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Ui ui = new Ui(taskList);
        Parser parser = new Parser(storage, ui, taskList, sc);

        try {
            storage.load();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        while (sc.hasNext()) {
            try {
                String command = sc.next();
                if (command.equals("bye")) {
                    ui.printBye();
                    break;
                } else {
                    parser.parse(command);
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}