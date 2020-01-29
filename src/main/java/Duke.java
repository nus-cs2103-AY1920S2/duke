import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static TaskList tasks;
    private static Storage storage;
    public static void main(String[] args) {
        printIntro();
        Scanner inputScanner = new Scanner(System.in);
        String fileName = "../data/tasks.txt";
        File file = new File(fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            printLines("File creation failed.");
        }
        
        storage = new Storage(fileName);
        tasks = new TaskList(storage.loadTasks());

        while (inputScanner.hasNextLine()) {
            try {
                String input = inputScanner.nextLine();
                String[] split = input.split(" ");
                String command = split[0];

                if (input.compareTo("list") == 0) {
                    System.out.println(tasks.toString());
                } else if (input.compareTo("bye") == 0) {
                    printGoodbye();
                    break;
                } else if (command.compareTo("delete") == 0) {
                    tasks.deleteTask(Integer.parseInt(split[1]) - 1);
                } else if (command.compareTo("done") == 0) {
                    tasks.doTask(Integer.parseInt(split[1]) - 1);
                    storage.doTask(Integer.parseInt(split[1]));
                } else if (command.compareTo("todo") == 0) {
                    manageTodo(tasks, input, fileName);
                } else if (command.compareTo("event") == 0) {
                    manageEvent(tasks, input, fileName);
                } else if (command.compareTo("deadline") == 0) {
                    manageDeadline(tasks, input, fileName);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                printLines("Sorry, invalid command. Try again with the following:\n     todo, deadline, event");
            } catch (ArrayIndexOutOfBoundsException e) {
                printLines("Sorry, invalid syntax or command. Please try again!");
            }
        }

        inputScanner.close();
    }

    private static void printIntro() {
        printLines("Hello! :) I'm Duke.\n" + "     How can I help you today?");
    }

    private static void printGoodbye() {
        printLines("Goodbye. See you again soon!");
    }

    public static void printLines(String content) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + content);
        System.out.println("    ____________________________________________________________");
    }

    private static void writeToFile(String fileName, String data) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(data);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            printLines("Sorry, the file input is invalid.");
        }
    }

    private static void manageTodo(TaskList tasks, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("todo");
            } else {
                String description = input.substring(input.indexOf(" ") + 1);
                Todo todo = new Todo(description, false);
                tasks.addTask(todo);
                String result = "T~0~" + description;
                writeToFile(fileName, result);
            }
        } catch (EmptyDescriptionException e) {
            printLines("Oops! The description of a " + e.getMessage() + " cannot be empty.");
        }
    }

    private static void manageEvent(TaskList tasks, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("event");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);

                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");

                if (split[0].compareTo("at") == 0) {
                    String time = Parser.parseTime(input.substring(input.indexOf("/") + 4));
                    Event event = new Event(description, false, time);
                    tasks.addTask(event);
                    String result = "E~0~" + description + "~" + time;
                    writeToFile(fileName, result);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            printLines("Oops! The description of an " + e.getMessage() + " cannot be empty.");
        } catch (InvalidTimeFormatException e) {
            printLines("Your time format is incorrect. Try: /at yyyy-mm-dd 2300");
        } catch (Exception e) {
            printLines("Sorry, invalid syntax or command. Please try again!");
        }
    }

    private static void manageDeadline(TaskList tasks, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("deadline");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                
                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");

                if (split[0].compareTo("by") == 0) {
                    String time = Parser.parseTime(input.substring(input.indexOf("/") + 4));
                    Deadline deadline = new Deadline(description, false, time);
                    tasks.addTask(deadline);
                    String result = "D~0~" + description + "~" + time;
                    writeToFile(fileName, result);
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