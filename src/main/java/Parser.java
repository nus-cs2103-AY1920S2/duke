import java.util.Scanner;

public class Parser {
    //deals with making sense of the user command
    public Scanner sc = new Scanner(System.in);
    public static Ui ui;
    public static TaskList taskList;

    public Parser() {
        this.ui = Duke.ui;
        this.taskList = Duke.taskList;
    }

    public void parse() {
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
                        taskList.add(new ToDo(description[1]), "print");

                    } else if (command.startsWith("deadline")) {
                        DukeException.checkDescription(description, "deadline");
                        DukeException.checkTime(arr, "deadline");
                        taskList.add(new Deadline(description[1],
                                arr[1].split(" ", 2)[1], Task.parser), "print");

                    } else if (command.startsWith("event")) {
                        DukeException.checkDescription(description, "event");
                        DukeException.checkTime(arr, "event");
                        taskList.add(new Event(description[1],
                                arr[1].split(" ", 2)[1], Task.parser), "print");

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
        ui.dukePrint("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}