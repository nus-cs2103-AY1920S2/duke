import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import exceptions.InvalidActionException;
import exceptions.InvalidTaskNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

//        System.out.println("Hello from\n" + logo);

        printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Chat logic
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<Task>();
        int numberOfTasks = 0;

        while (!input.equals("bye")) {
            String action = input.split(" ")[0];
            try {
                switch (action) {
                case "list":
                    printList(list);
                    break;
                case "done":
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if (taskNumber < 1 || taskNumber > numberOfTasks) {
                        throw new InvalidTaskNumberException(numberOfTasks);
                    }
                    list.get(taskNumber - 1).markAsDone();
                    printDone(list.get(taskNumber - 1));
                    break;
                case "delete":
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if (taskNumber < 1 || taskNumber > numberOfTasks) {
                        throw new InvalidTaskNumberException(numberOfTasks);
                    }
                    Task deleteTask = list.get(taskNumber - 1);
                    list.remove(taskNumber - 1);
                    numberOfTasks--;
                    printDelete(deleteTask, numberOfTasks);
                    break;
                case "todo":
                    try {
                        String[] fields = input.split("todo ");
                        if(fields.length < 2) {
                            throw new EmptyDescriptionException("todo");
                        }
                        Task newTodo =  new Todo(fields[1]);
                        list.add(newTodo);
                        numberOfTasks++;
                        printNewTask(newTodo, numberOfTasks);
                    } catch (EmptyDescriptionException ex) {
                        printFormattedOutput(ex.toString());
                    }
                    break;
                case "event":
                case "deadline":
                    try {
                        String[] fields = input.split(action + " ");
                        if (fields.length < 2) {
                            throw new EmptyDescriptionException(action);
                        }

                        fields = action.equals("event")
                                ? fields[1].split(" /at ")
                                : fields[1].split(" /by ");
                        if (fields.length < 2) {
                            throw new EmptyTimeException(action, fields);
                        }

                        Task newTask =  action.equals("event")
                                ? new Event(fields[0], fields[1])
                                : new Deadline(fields[0], fields[1]);

                        list.add(newTask);
                        numberOfTasks++;
                        printNewTask(newTask, numberOfTasks);
                    } catch (EmptyDescriptionException ex) {
                        printFormattedOutput(ex.toString());
                    } catch (EmptyTimeException ex) {
                        String message = ex.toString()
                                + "\n    Type in the time/date or press enter to stop creating task";
                        printFormattedOutput(message);
                        System.out.print(ex.stringifyFields());
                        input = sc.nextLine();
                        if (!input.equals("")) {
                            String[] fields = ex.getFields();
                            Task newTask =  action.equals("event")
                                    ? new Event(fields[0], input)
                                    : new Deadline(fields[0], input);
                            list.add(newTask);
                            numberOfTasks++;
                            printNewTask(newTask, numberOfTasks);
                        } else {
                            printFormattedOutput("Stopped creating task.");
                        }
                    }
                    break;
                case "date":
                    String[] fields = input.split(" ");
                    try {
                        LocalDate date = LocalDate.parse(fields[1]);
                        searchDateTask(list, date);
                    } catch (DateTimeException ex) {
                        printFormattedOutput("Sorry, I don't recognize this date format. " +
                                "Try to follow this format: 2020-12-31");
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        printFormattedOutput("Please input a date!");
                    }
                    break;
                default:
                    throw new InvalidActionException();
                }
            } catch (InvalidActionException ex) {
                printFormattedOutput(ex.toString());
            } catch (InvalidTaskNumberException ex) {
                printFormattedOutput(ex.toString());
            } catch (DateTimeException ex) {
                printFormattedOutput("You have entered an invalid time/date format.\n    " +
                        "Please follow the following format: 23:59 2020-12-31\n    " +
                        "You may input '-' to omit either the time or date");
            }

            input = sc.nextLine();
        }

        printFormattedOutput("Bye. Hope to see you again soon!");

    }

    public static void searchDateTask(ArrayList<Task> list, LocalDate date) {
        ArrayList<Task> dateTasks = new ArrayList<>();
        for (Task t : list) {
            if (t instanceof Event) {
                if (date.equals(((Event) t).getDate())) {
                    dateTasks.add(t);
                }
            } else if (t instanceof Deadline) {
                if (date.equals(((Deadline) t).getDate())) {
                    dateTasks.add(t);
                }
            }
        }
        printList(dateTasks);
    }

    // Print formatters

    private static String bar = "    **************************************************************\n";

    public static void printFormattedOutput(String output) {
        System.out.println(bar + "    " + output + "\n" + bar);
    }

    public static void printList(ArrayList<Task> list) {
        System.out.print(bar);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        System.out.println(bar);
    }

    public static void printNewTask(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

    public static void printDone(Task task) {
        System.out.print(bar);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println(bar);
    }

    public static void printDelete(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

//    public static void printDateTask(ArrayList<Task> list) {
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(i + ".  " + list.get(i));
//        }
//    }
}
