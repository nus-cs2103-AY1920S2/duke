import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printIntro();
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] split = input.split(" ");
                String command = split[0];

                if (input.compareTo("list") == 0) {
                    System.out.println(taskList.toString());
                } else if (input.compareTo("bye") == 0) {
                    printGoodbye();
                    break;
                } else if (command.compareTo("done") == 0) {
                    taskList.doTask(Integer.parseInt(split[1]) - 1);
                } else if (command.compareTo("todo") == 0) {
                    manageTodo(taskList, input);
                } else if (command.compareTo("event") == 0) {
                    manageEvent(taskList, input);
                } else if (command.compareTo("deadline") == 0) {
                    manageDeadline(taskList, input);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                printLines("Sorry, invalid command. Try again with the following:\n     todo, deadline, event");
            } catch (ArrayIndexOutOfBoundsException e) {
                printLines("Sorry, invalid syntax or command. Please try again!");
            }
        }

        sc.close();
    }

    public static void printIntro() {
        printLines("     Hello! :) I'm Duke.\n" + "     How can I help you today?");
    }

    public static void printGoodbye() {
        printLines("Goodbye. See you again soon!");
    }

    public static void printLines(String content) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + content);
        System.out.println("    ____________________________________________________________");
    }

    public static void manageTodo(TaskList tasks, String input) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("todo");
            } else {
                String description = input.substring(input.indexOf(" ") + 1);
                Todo todo = new Todo(description, false);
                tasks.addTask(todo);
            }
        } catch (EmptyDescriptionException e) {
            printLines("Oops! The description of a " + e.getMessage() + " cannot be empty.");
        }
    }

    public static void manageEvent(TaskList tasks, String input) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("event");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));

                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");
                if (split[0].compareTo("at") == 0) {
                    String time = input.substring(input.indexOf("/") + 4);
                    Event event = new Event(description, false, time);
                    tasks.addTask(event);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            printLines("Oops! The description of an " + e.getMessage() + " cannot be empty.");
        } catch (InvalidTimeFormatException e) {
            printLines("Your time format is incorrect. Try: /at [time]");
        } catch (Exception e) {
            printLines("Sorry, invalid syntax or command. Please try again!");
        }
    }

    public static void manageDeadline(TaskList tasks, String input) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("deadline");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                
                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");
                if (split[0].compareTo("by") == 0) {
                    String time = input.substring(input.indexOf("/") + 4);
                    Deadline deadline = new Deadline(description, false, time);
                    tasks.addTask(deadline);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            printLines("Oops! The description of a " + e.getMessage() + " cannot be empty.");
        } catch (InvalidTimeFormatException e) {
            printLines("Your time format is incorrect. Try: /by [time]");
        } catch (Exception e) {
            printLines("Sorry, invalid syntax or command. Please try again!");
        }
    }
}