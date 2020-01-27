import duke.pack.Task;
import duke.pack.Deadline;
import duke.pack.Event;
import duke.pack.Todo;
import duke.pack.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    // ArrayList of tasks
    private static ArrayList<Task> arrList;
    private static File file;

    public static void main(String[] args) throws DukeException {
        arrList = new ArrayList<>();
        file = new File("data/duke.txt");

        // Scanner object for input
        Scanner sc = new Scanner(System.in);

        // loading tasks from hard disk
        try {
            loadFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // greeting
        greet();

        // take in user's command
        String command = sc.nextLine().trim();

        // continue processing user's command, as long as command is not bye
        while (!command.equals("bye")) {
            String[] comm = command.split(" ");

            try {
                if (command.equals("list")) {
                    // prints tasks in list if command is list
                    printList();

                } else if (comm[0].equals("done")) {
                    // if task number is not given
                    if (comm.length == 1) {
                        throw new DukeException("    Oh no! You have to specify which task is done!");
                    }

                    // mark the specified task as done

                    int taskNum = Integer.parseInt(comm[1]);
                    if (taskNum > arrList.size()) {
                        throw new DukeException("    Oh no! That task does not exist!");
                    }


                    arrList.get(taskNum - 1).markAsDone();

                } else if (comm[0].equals("delete")) {
                    // if task number is not given
                    if (comm.length == 1) {
                        throw new DukeException("    Oh no! You have to specify which task to delete!");
                    }

                    // delete the specified task
                    int taskNum = Integer.parseInt(comm[1]);
                    delete(taskNum);

                } else if (comm[0].equals("deadline")) {
                    // if no description is given
                    if (comm.length == 1) {
                        throw new DukeException("    Oh no! A deadline cannot be empty!");
                    }

                    String[] arr = command.split("/by");
                    // if no "by" is given
                    if (arr.length == 1) {
                        throw new DukeException("    Oh no! You need to include by when!");
                    }

                    String[] arr2 = arr[0].split("deadline");
                    String[] dateTime = arr[1].trim().split(" ");
                    String time = "";
                    if (dateTime.length == 1) {
                        throw new DukeException("    Oh no! Please include a time!");
                    }
                    time = dateTime[1].trim();

                    try {
                        LocalDate date = LocalDate.parse(dateTime[0].trim());
                        // add to list
                        Task d = new Deadline(arr2[1].trim(), time, date);
                        add(d);

                    } catch (DateTimeParseException e) {
                        throw new DukeException("    Oh no! Please follow the date format! " +
                                "Example: 2020-01-27!");
                    }

                } else if (comm[0].equals("event")) {
                    // if no description is given
                    if (comm.length == 1) {
                        throw new DukeException("    Oh no! An event cannot be empty!");
                    }

                    String[] arr = command.split("/at");
                    // if no "at" is given
                    if (arr.length == 1) {
                        throw new DukeException("    Oh no! Please include a by!");
                    }

                    String[] arr2 = arr[0].split("event");
                    String[] dateTime = arr[1].trim().split(" ");
                    String time = "";
                    if (dateTime.length == 1) {
                        throw new DukeException("    Oh no! Please include a time!");
                    }
                    time = dateTime[1].trim();

                    try {
                        LocalDate date = LocalDate.parse(dateTime[0].trim());
                        // add to list
                        Task ev = new Event(arr2[1].trim(), time, date);
                        add(ev);

                    } catch (DateTimeParseException e) {
                        throw new DukeException("    Oh no! Please follow the date format! " +
                                "Example: 2020-01-27!");
                    }

                } else if (comm[0].equals("todo")) {
                    String[] arr = command.split("todo");

                    // if no description is given
                    if (comm.length == 1) {
                        throw new DukeException("    Oh no! A todo cannot be empty!");
                    }

                    // add to list
                    Task t = new Todo(arr[1].trim());
                    add(t);

                } else {
                    // if command does not exist
                    throw new DukeException("    Oh no! I'm sorry, I do not understand that, please try again!");
                }

                // save updated tasks in hard disk
                updateFileContent();

            } catch (DukeException e) {
                System.out.println(e);

            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());

            } finally {
                command = sc.nextLine().trim();
            }

        }

        try {
            updateFileContent();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            // exit
            exit();
        }
    }

    /**
     * prints greeting to user
     */
    public static void greet() {
        System.out.println("    ------------------------------------------------------------");

        // logo obtained using https://www.kammerl.de/ascii/AsciiSignature.php
        String logo = "     __  __ _           _          _      \n" +
                "    |  \\/  | |         | |        | |     \n" +
                "    | \\  / | |__   ___ | |_       | |_ __ \n" +
                "    | |\\/| | '_ \\ / _ \\| __|  _   | | '__|\n" +
                "    | |  | | |_) | (_) | |_  | |__| | |   \n" +
                "    |_|  |_|_.__/ \\___/ \\__|  \\____/|_|   \n" +
                "\n";

        System.out.println(logo);
        System.out.println("    Hello, I'm Mbot Jr!\n" +
                "    How may I help you?");
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * echos user's command
     * @param comm command given by user
     */
    public static void echo(String comm) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    " + comm);
        System.out.println("    ------------------------------------------------------------");
    }

    public static void loadFileContent() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String task = fileScanner.nextLine();
            String[] taskArr = task.split("\\|");
            String taskType = taskArr[0].trim();

            if (taskType.equals("T")) {
                Task todo = new Todo(taskArr[2].trim());
                if (taskArr[1].trim().equals("1")) {
                    todo.setDone(true);
                }
                arrList.add(todo);

            } else if (taskType.equals("E")) {
                LocalDate date = LocalDate.parse(taskArr[4].trim());
                Task event = new Event(taskArr[2].trim(), taskArr[3].trim(), date);
                if (taskArr[1].trim().equals("1")) {
                    event.setDone(true);
                }
                arrList.add(event);

            } else if (taskType.equals("D")) {
                LocalDate date = LocalDate.parse(taskArr[4].trim());
                Task deadline = new Deadline(taskArr[2].trim(), taskArr[3].trim(), date);
                if (taskArr[1].trim().equals("1")) {
                    deadline.setDone(true);
                }
                arrList.add(deadline);
            }
        }
    }

    public static void updateFileContent() throws IOException {
        FileWriter fw = new FileWriter(file);
        String text = "";

        for ( Task task: arrList) {
            text = text + task.formatForFile();
        }

        fw.write(text);
        fw.close();
    }

    /**
     * adds user's command to arrList
     * @param t command given by user
     */
    public static void add(Task t) {
        arrList.add(t);
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Alright! I have added: \n    " + t
                + "\n    You now have " + arrList.size() + " tasks in your list!");
        System.out.println("    ------------------------------------------------------------");
    }

    public static void delete(int taskNum) {
        Task t = arrList.remove(taskNum - 1);
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Yeet! I have tossed the task: \n    " + t
                + "\n    You now have " + arrList.size() + " tasks in your list!");
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * prints list of tasks user has added
     */
    public static void printList() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Here are your tasks:");

        for (int i = 1; i <= arrList.size(); i++) {
            System.out.println("    " + i + ". " + arrList.get(i - 1));
        }
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * exits
     */
    public static void exit() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    See you later!");
        System.out.println("    ------------------------------------------------------------");
    }
}
