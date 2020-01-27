package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.PrintWriter;
import java.io.File;

public class Duke {
    static ArrayList<Task> arr;
    static Scanner scanner;

    public Duke() {
        arr = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void start() throws IOException {
        String line = "-----------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(line);
        loadData();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            checkCommand(input);
            input = scanner.nextLine();
        }
        printWithFormat("", "bye");
    }

    // get the data aka from the list and overwrite it
    public static void saveData() throws IOException {
        PrintWriter writer = new PrintWriter("../../../data/duke.txt");
        for (Task t : arr) {
            writer.println(t.getFormatForSave());
        }
        writer.close();
    }

    //read from duke.txt then do string manipulation
    //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public static void loadData() throws FileNotFoundException {
        File file = new File("../../../data/duke.txt");
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String inputLine = fileScanner.nextLine();
            interpretLine(inputLine);
        }
    }

    //what should the shape of the data be, should it be the same way it interprets regular stuff?
    //yes: can just construct directly from the input
    //no: more verbose when reading the duke.txt file, does not affect finding tho because u still looking thru the lsit
    //yes lets do it
    public static void interpretLine(String inputLine) {
        String[] strArr = inputLine.split(" "); // this is the problem
        String doneStatus = strArr[0];
        String type = strArr[1];
        String input = inputLine.substring(2);
//        System.out.println(input);
        switch (type) {
            case "deadline":
                Deadline d = new Deadline(input);
                if (doneStatus.equals("1")) {
                    d.setDone();
                }
                arr.add(d);
                break;
            case "event":
                Event e = new Event(input);
                if (doneStatus.equals("1")) {
                    e.setDone();
                }
                arr.add(e);
                break;
            case "todo":
                Todo td = new Todo(input);
                if (doneStatus.equals("1")) {
                    td.setDone();
                }
                arr.add(td);
                break;

        }
    }

    public static void checkCommand(String input) throws IOException {
        String line = "-----------------------------------";
        String[] strArr = input.split(" ");
        String command = strArr[0];
        try {
            int length = strArr.length;
            switch (command) {
                case "bye":
                    printWithFormat("", "bye");
                    break;
                case "list":
                    printWithFormat("", "list");
                    break;
                case "done":
                    if (length > 1) {
                        int index = Integer.parseInt(strArr[1]) - 1;
                        Task taskToBeDone = arr.get(index);
                        taskToBeDone.setDone();
                        printWithFormat(taskToBeDone.toString(), "done");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "deadline":
                    if (length > 1) {
                        Deadline d = new Deadline(input);
                        arr.add(d);
                        printWithFormat(d.toString(), "task");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "event":
                    if (length > 1) {
                        Event e = new Event(input);
                        arr.add(e);
                        printWithFormat(e.toString(), "task");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "todo":
                    if (length > 1) {
                        Todo td = new Todo(input);
                        arr.add(td);
                        printWithFormat(td.toString(), "task");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "delete":
                    int index = Integer.parseInt(strArr[1]) - 1;
                    Task taskToBeDeleted = arr.remove(index);
                    printWithFormat(taskToBeDeleted.toString(), "delete");
                    break;
                default:
                    throw new DukeException("invalid", command);
            }
        } catch (DukeException d){
            printWithFormat(d.getMessage(), "");
        }
    }

    public static void printWithFormat(String input, String type) throws IOException {
        String line = "-----------------------------------";
        System.out.println(line);
        switch (type) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= arr.size(); i++) {
                    StringBuilder str = new StringBuilder();
                    Task task = arr.get(i-1);
                    String output = str.append(i).append(". ").append(task.toString()).toString();
                    System.out.println(output);
                }
                break;
            case "task":
                System.out.println("Got it. I've added this task:");
                System.out.println(input);
                int arrlength = arr.size();
                System.out.println("Now you have "+ arrlength + " tasks in the list.");
                saveData();
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "done":
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(input);
                saveData();
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                System.out.println(input);
                System.out.println("Now you have "+ arr.size() + " tasks in the list.");
                saveData();
                break;
            default:
                System.out.println(input);
                break;
        }
        System.out.println(line);
    }

}
