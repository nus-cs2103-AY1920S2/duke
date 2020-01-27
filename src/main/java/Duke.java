import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


import java.io.File;
import java.io.FileWriter;
import org.apache.commons.io.FileUtils;
import java.io.IOException;

import java.nio.charset.Charset;

public class Duke {

    static Scanner scanner;
    static ArrayList<Task> arrList;
    static File file;

    public static void main(String[] args) {

        printIntro();
        initialise();

        // Simulation
        while (true) {

            try {

                String input = scanner.nextLine();
                System.out.println(input);
                String output = "";
                String[] inputArr = input.split(" ");
                String instruction = inputArr[0];

                if (instruction.equals("bye")) {
                    System.out.print(stringWrapper("Bye. Hope to see you again soon!"));
                    break;

                } else if (instruction.equals("done")) {
                    if (inputArr.length <= 1) {
                        // EXCEPTION
                        String message = stringWrapper("☹ OOPS!!! Please specify a task number to mark as done!");
                        throw new DukeException(message);
                    }
                    int taskNumber = Integer.parseInt(inputArr[1]);
                    if (taskNumber > arrList.size()) {
                        // EXCEPTION
                        String message = stringWrapper("☹ OOPS!!! Please specify a valid task number!");
                        throw new DukeException(message);
                    }
                    Task task = arrList.get(taskNumber - 1);
                    task.completeTask();
                    output = "Nice! I've marked this task as done: \n" + task.toString();

                } else if (instruction.equals("delete")) {
                    if (inputArr.length <= 1) {
                        // EXCEPTION
                        String message = stringWrapper("☹ OOPS!!! Please specify a task number to be deleted!");
                        throw new DukeException(message);
                    }
                    int taskNumber = Integer.parseInt(inputArr[1]);
                    if (taskNumber > arrList.size()) {
                        // EXCEPTION
                        String message = stringWrapper("☹ OOPS!!! Please specify a valid task number!");
                        throw new DukeException(message);
                    }
                    Task task = arrList.get(taskNumber - 1);
                    arrList.remove(taskNumber - 1);
                    output = "Noted! I've removed this task: \n"
                            + task.toString() + "\n"
                            + "Now you have " + arrList.size()
                            + " tasks in the list.";;

                } else if (instruction.equals("list")) {
                    String currentList = getCurrentList();
                    output = "Here are the tasks in your list: \n" + currentList;

                } else if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")){

                    if (inputArr.length <= 1) {
                        // EXCEPTION
                        String message = stringWrapper("☹ OOPS!!! Please enter the descriptions for your to-do list!");
                        throw new DukeException(message);
                    }
                    int idx = input.indexOf(" ");
                    String taskInput = input.substring(idx + 1);
                    String[] taskInputArr = taskInput.split("/");
                    String taskName = taskInputArr[0];
                    Task newTask = null;
                    if (instruction.equals("todo")) {
                        newTask = new ToDo(taskName);
                    } else if (instruction.equals("deadline")) {
                        String dateTime = taskInputArr[1];
                        idx = dateTime.indexOf(" ");
                        dateTime = dateTime.substring(idx + 1);
                        newTask = new Deadline(taskName, dateTime);
                    } else if (instruction.equals("event")) {
                        String dateTime = taskInputArr[1];
                        System.out.println("here:" + dateTime);
                        idx = dateTime.indexOf(" ");
                        dateTime = dateTime.substring(idx + 1);
                        newTask = new Event(taskName, dateTime);
                    }
                    arrList.add(newTask);
                    output = "Got it. I've added this task: \n"
                            + newTask.toString() + "\n"
                            + "Now you have " + arrList.size()
                            + " tasks in the list.";

                } else {
                    // EXCEPTION
                    String message = stringWrapper("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    throw new DukeException(message);
                }

                saveList();
                output = stringWrapper(output);
                System.out.println(output);

            } catch (DukeException e) {
                System.err.println(e.toString());
                continue;

            } catch (ArrayIndexOutOfBoundsException x) {
                Exception e = new DukeException("Please enter a valid instruction!");
                System.err.println(e.toString());
                continue;

            } catch (IOException e) {
                System.err.println(e.toString());
                continue;
            }

        }

    }

    private static void printIntro() {
        String intro =
                "Hello! I'm Duke\n" +
                        "What can I do for you?";

        System.out.println(stringWrapper((intro)));
    }

    private static void initialise() {
        scanner = new Scanner(System.in);
        arrList = new ArrayList<>(100);
        file = new File("./data/duke.txt");
        try {
            if (!file.exists()) {
                //the file does not exist yet
                file.getParentFile().mkdirs();
                file.createNewFile();
                file = new File("./data/duke.txt");
            }
            List<String> data = FileUtils.readLines(file, Charset.defaultCharset());
            for (int i = 0; i < data.size(); i++) {
                String line = data.get(i);
                // Parse saved data per line
                String[] parsedLine = line.split("_");
                switch (parsedLine[0]) {
                    case "T":
                        arrList.add(new ToDo(parsedLine[2], Boolean.parseBoolean(parsedLine[1])));
                        break;
                    case "D":
                        arrList.add(new Deadline(parsedLine[2], Boolean.parseBoolean(parsedLine[1]), parsedLine[3]));
                        break;
                    case "E":
                        arrList.add(new Event(parsedLine[2], Boolean.parseBoolean(parsedLine[1]), parsedLine[3]));
                        break;
                    default:
                }
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private static void saveList() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < arrList.size(); i++) {
            Task item = arrList.get(i);
            System.out.println(item.getSaveFormat());
            fileWriter.write(item.getSaveFormat() + "\n");
        }
        fileWriter.flush();
    }

    private static String getCurrentList() {
        String list = "";
        for (int i = 0; i < arrList.size(); i++) {
            String count = (i + 1) + "";
            String task = arrList.get(i).toString();
            list += count + ". " + task;
            if (i != arrList.size() - 1) {
                list += "\n";
            }
        }
        return list;
    }

    private static String stringWrapper(String string) {
        return "____________________________________________________________\n"
                + string
                + "\n"
                + "____________________________________________________________\n";
    }

}
