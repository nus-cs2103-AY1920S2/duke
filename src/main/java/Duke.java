import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//oops i messed up and forgot to commit in a branch

public class Duke {
    public static Ui ui = new Ui();
    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage("duke.txt");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        storage.retrieveInfo();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    taskList.list();

                } else if (command.startsWith("done")) {
                    DukeException.checkCommand(command, "done", taskList.size());
                    taskList.done(Integer.valueOf(command.split(" ")[1]) - 1);

                } else if (command.startsWith("delete")) {
                    DukeException.checkCommand(command, "delete", taskList.size());
                    taskList.delete(Integer.valueOf(command.split(" ")[1]) - 1);
                } else {
                    String[] arr = command.split("/");
                    String[] description = (arr[0].split(" ", 2));

                    if (command.startsWith("todo")) {
                        DukeException.checkDescription(description, "todo");
                        taskList.add(new ToDo(description[1]));

                    } else if (command.startsWith("deadline")) {
                        DukeException.checkDescription(description, "deadline");
                        DukeException.checkTime(arr, "deadline");
                        taskList.add(new Deadline(description[1],
                                arr[1].split(" ", 2)[1], Task.parser));

                    } else if (command.startsWith("event")) {
                        DukeException.checkDescription(description, "event");
                        DukeException.checkTime(arr, "event");
                        taskList.add(new Event(description[1],
                                arr[1].split(" ", 2)[1], Task.parser));

                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                }
            } catch (DukeException e) {
                ui.dukePrint("☹ OOPS!!! " + e.getMessage()+ "\n");
            } finally {
                command = sc.nextLine();
            }
        }

        storage.updateInfo();
        ui.dukePrint("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}