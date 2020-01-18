import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> arrList;

    public static void main(String[] args) {

        // Introduction

        String intro =
            "Hello! I'm Duke\n" +
            "What can I do for you?";

        System.out.println(stringWrapper((intro)));

        // Initialisation

        Scanner scanner = new Scanner(System.in);
        arrList = new ArrayList<>(100);

        // User Input

        while (true) {

            String input = scanner.nextLine();
            System.out.println(input);
            String output = "";
            String[] inputArr = input.split(" ");
            String instruction = inputArr[0];

            try {

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
                    System.out.println("here: " + taskInput);
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
                        idx = dateTime.indexOf(" ");
                        dateTime = dateTime.substring(idx + 1);
                        newTask = new Event(taskName, dateTime);
                    }
                    arrList.add(newTask);
                    output = "Got it. I've added this task: \n"
                            + newTask.toString() + "\n" +
                            "Now you have " + arrList.size()
                            + " tasks in the list.";

                } else {
                    // EXCEPTION
                    String message = stringWrapper("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    throw new DukeException(message);
                }

            } catch (DukeException e) {
                System.err.println(e.toString());
                continue;
            } catch (ArrayIndexOutOfBoundsException x) {
                Exception e = new DukeException("Please enter a valid instruction!");
                System.err.println(e.toString());
                continue;
            }
            output = stringWrapper(output);
            System.out.println(output);
        }

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
