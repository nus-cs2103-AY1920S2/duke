import java.io.*;
import java.util.*;

public class Duke {

    public static void appendFile(String filename, Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            String line = task.toLine();
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (Exception ignored) {
        }
    }

    public static void writeFile(String filename, ArrayList<Task> arr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < arr.size(); i++) {
                String line = arr.get(i).toLine();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        //load data from file
        String filename = "data/duke.txt";
        File file = new File(filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String str = br.readLine();
                if (str == null) break;
                String[] arr = str.split("/");
                if (arr[0].equals("T")) {
                    Todo task = new Todo(arr[2]);
                    if (arr[1].equals("1")) task.setDone();
                    data.add(task);
                } else if (arr[0].equals("D")) {
                    Deadline task = new Deadline(arr[2], arr[3]);
                    if (arr[1].equals("1")) task.setDone();
                    data.add(task);
                } else { //event
                    Event task = new Event(arr[2], arr[3]);
                    if (arr[1].equals("1")) task.setDone();
                    data.add(task);
                }
            }
        } catch (Exception ignored) {
        }

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String action = inputArr[0];
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println(i + 1 + ". " + data.get(i));
                    }
                } else if (action.equals("done")) {
                    int option = Integer.parseInt(inputArr[1]);
                    data.get(option - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + data.get(option - 1));
                    Duke.writeFile(filename, data);
                } else if (action.equals("delete")) {
                    int option = Integer.parseInt(inputArr[1]);
                    System.out.println("Noted. I've removed this task: \n  " + data.get(option - 1));
                    data.remove(option - 1);
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    Duke.writeFile(filename, data);
                } else {
                    switch (action) {
                        case "todo":
                            if (inputArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            } else {
                                System.out.println("Got it. I've added this task: ");
                                Task task = new Todo(inputArr[1]);
                                data.add(task);
                                Duke.appendFile(filename, task);
                            }
                            break;
                        case "deadline":
                            if (inputArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            } else {
                                String[] dArr = inputArr[1].split(" /by ", 2);
                                if (dArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! You forgot to specify a date/time for the deadline.");
                                } else {
                                    System.out.println("Got it. I've added this task: ");
                                    Task task = new Deadline(dArr[0], dArr[1]);
                                    data.add(task);
                                    Duke.appendFile(filename, task);
                                }
                            }
                            break;
                        case "event":
                            if (inputArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            } else {
                                String[] eArr = inputArr[1].split(" /at ", 2);
                                if (eArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! You forgot to specify a date/time for the event.");
                                } else {
                                    System.out.println("Got it. I've added this task: ");
                                    Task task = new Event(eArr[0], eArr[1]);
                                    data.add(task);
                                    Duke.appendFile(filename, task);
                                }
                            }
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("  " + data.get(data.size() - 1));
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
