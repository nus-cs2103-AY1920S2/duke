import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    static ArrayList<Task> list = new ArrayList<>();

    private static void printFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // new file for given file path
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] split = nextLine.split("|");
            String indicator = split[0];
            switch (indicator) {
                case "D":
                    Deadline deadline = new Deadline(split[2], split[3]);
                    if (split[1].equals("Y")) {
                        deadline.setCheck();
                    }
                    list.add(deadline);
                    break;

                case "E":
                    Event event = new Event(split[2], split[3]);
                    if (split[1].equals("Y")) {
                        event.setCheck();
                    }
                    list.add(event);
                    break;

                case "T":
                    Todo todo = new Todo(split[2]);
                    if (split[1].equals("Y")) {
                        todo.setCheck();
                    }
                    list.add(todo);
                    break;

                default:
                    break;
            }
        }
    }

    private static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            fw.write(t.toStringTxt() + System.lineSeparator());
        }
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(intro);
        String filePath = "/Users/Simon/Documents/duke/src/main/java/duke.txt";
        try {
            printFileContents(filePath, list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found la oi");
        }
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                // if the input is 1 word, usually "bye" or "list"
                if (input.split(" ").length == 1) {
                    if (input.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (input.equals("list")) {
                        for (Task t : list) {
                            int index = list.indexOf(t) + 1;
                            System.out.println("" + index + "." + t.toString());
                        }
                    } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                        throw new DukeException("OOPS!!! The description of a " + input + " cannot be empty.");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                // if the input is > 1 word; eg deadline return book, done 3
                else {
                    String[] stringArr = input.split(" ", 2);
                    String action = stringArr[0];
                    String taskName = stringArr[1];
                    if (action.equals("done")) {
                        int val = Integer.parseInt(stringArr[1]);
                        Task task = list.get(val - 1);
                        task.setCheck();
                        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
                        writeToFile(filePath, list);
                    } else if (action.equals("delete")) {
                        int val = Integer.parseInt(stringArr[1]);
                        Task t = list.get(val - 1);
                        list.remove(val - 1);
                        int numTask = list.size();
                        System.out.println("Noted. I've removed this task:\n" + t.toString() + "\n" + "Now you have " + numTask + " tasks in the list.");
                        writeToFile(filePath, list);
                    }
                    else if (action.equals("deadline")){
                        String[] splitTaskName = taskName.split("/"); // to obtain command when splitting
                        String[] splitTaskName2 = splitTaskName[1].split(" ", 2); // to obtain date when splitting
                        Deadline deadline = new Deadline(splitTaskName[0], splitTaskName2[1]);
                        list.add(deadline);
                        int numTasks = list.size();
                        System.out.println("Got it. I've added this task:\n" + deadline.toString() + "\n" + "Now you have " + numTasks + " tasks in the list.");
                        appendToFile(filePath, deadline.toStringTxt());
                    } else if (action.equals("event")) {
                        String[] splitTaskName = taskName.split("/"); // to obtain command when splitting
                        String[] splitTaskName2 = splitTaskName[1].split(" ", 2); // to obtain date when splitting
                        Event event = new Event(splitTaskName[0], splitTaskName2[1]);
                        list.add(event);
                        int numTasks = list.size();
                        System.out.println("Got it. I've added this task:\n" + event.toString() + "\n" + "Now you have " + numTasks + " tasks in the list.");
                        appendToFile(filePath, event.toStringTxt());
                    } else if (action.equals("todo")) {
                        Todo todo = new Todo(taskName);
                        list.add(todo);
                        int numTasks = list.size();
                        System.out.println("Got it. I've added this task:\n" + todo.toString() + "\n" + "Now you have " + numTasks + " tasks in the list.");
                        appendToFile(filePath, todo.toStringTxt());
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            catch (IOException e) {
                System.out.println("File not found");
            }
        }
    }
}