package duke;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Duke class represents a chatbot that keeps tracks of tasks.
 * Three unique commands can be given to the chatbot: "todo", "event and "deadline"
 * After each command, a description must be given.
 * In the case of the "event" class, after the description, the timing and date must be given after inputting "/at".
 * eg. event sister's birthday /at 08/06/2020 1700./
 * Date and timing must be given in this specific format (dd/mm/yyyy hhmm).
 * The same applies to deadline, but changing the "/at" to "/by".
 * eg. deadline math homework /by 07/08/2020 2359.
 * Other function includes marking the task as done and removing it from the list.
 * eg. done 3
 * delete 3
 * use "list" to printout the exising tasks on the list
 */
public class Duke {
    /**
     * This is the main class.
     * @param args args
     */
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