import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//oops i messed up and forgot to commit in a branch

public class Duke {
    public static ArrayList<Task> newList;
    public static Storage storage = new Storage("duke.txt");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        newList = new ArrayList<Task>();
        storage.retrieveInfo();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    list();

                } else if (command.startsWith("done")) {
                    DukeException.checkCommand(command, "done", newList.size());
                    done(Integer.valueOf(command.split(" ")[1]) - 1);

                } else if (command.startsWith("delete")) {
                    DukeException.checkCommand(command, "delete", newList.size());
                    delete(Integer.valueOf(command.split(" ")[1]) - 1);
                } else {
                    String[] arr = command.split("/");
                    String[] description = (arr[0].split(" ", 2));

                    if (command.startsWith("todo")) {
                        DukeException.checkDescription(description, "todo");
                        newList.add(new ToDo(description[1]));

                    } else if (command.startsWith("deadline")) {
                        DukeException.checkDescription(description, "deadline");
                        DukeException.checkTime(arr, "deadline");
                        newList.add(new Deadline(description[1],
                                arr[1].split(" ", 2)[1], Task.parser));

                    } else if (command.startsWith("event")) {
                        DukeException.checkDescription(description, "event");
                        DukeException.checkTime(arr, "event");
                        newList.add(new Event(description[1],
                                arr[1].split(" ", 2)[1], Task.parser));

                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                    dukePrint("Got it. I've added this task:\n"+
                            newList.get(newList.size() - 1).toString()+"\n" +
                            "Now you have "+newList.size()+" tasks in the list.\n");
                }
            } catch (DukeException e) {
                dukePrint("☹ OOPS!!! " + e.getMessage()+ "\n");
            } finally {
                command = sc.nextLine();
            }
        }

        storage.updateInfo();
        dukePrint("Bye. Hope to see you again soon!\n");
        sc.close();
    }

    public static String horizontalLines() {
        return "__________________________________________________________________________________________________________\n";
    }

    public static void dukePrint (String input) {
        System.out.print(horizontalLines());
        System.out.print(input);
        System.out.print(horizontalLines());
    }

    public static void list() {
        if (newList.size() == 0) {
            dukePrint("You currently have no tasks in your list\n");
        } else {
            System.out.print(horizontalLines() + "Here are the tasks in your list:\n");
            for (int i = 0; i < newList.size(); i += 1) {
                System.out.print((i + 1)+". "+ newList.get(i).toString()+"\n");
            }
            System.out.print(horizontalLines());
        }
    }

    public static void done(int i) {
        newList.get(i).markAsDone();
        dukePrint("Nice! I've marked this task as done: \n"+ newList.get(i).toString()+"\n");
    }

    public static void delete(int i) {
        Task task = newList.get(i);
        newList.remove(i);
        dukePrint("Noted. I've removed this task:\n"+task.toString()+"\n" +
                "Now you have " + newList.size() + " tasks in the list.\n");
    }
}