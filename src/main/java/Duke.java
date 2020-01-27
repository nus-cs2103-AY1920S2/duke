import java.util.Scanner;
import java.util.ArrayList;


import java.io.IOException;



public class Duke {

    private Scanner scanner;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        try {
            this.scanner = new Scanner(System.in);
            this.storage = new Storage("data/duke.txt");
            this.taskList = this.storage.loadTaskList();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void run() {

        UI.printIntro();

        // Simulation
        while (true) {

            try {

                String input = this.scanner.nextLine();
                System.out.println(input);
                String output = "";
                String[] inputArr = input.split(" ");
                String instruction = inputArr[0];

                if (instruction.equals("bye")) {
                    System.out.print(UI.stringWrapper("Bye. Hope to see you again soon!"));
                    break;

                } else if (instruction.equals("done")) {
                    if (inputArr.length <= 1) {
                        // EXCEPTION
                        String message = UI.stringWrapper("☹ OOPS!!! Please specify a task number to mark as done!");
                        throw new DukeException(message);
                    }
                    int taskNumber = Integer.parseInt(inputArr[1]);
                    if (taskNumber > this.taskList.getSize()) {
                        // EXCEPTION
                        String message = UI.stringWrapper("☹ OOPS!!! Please specify a valid task number!");
                        throw new DukeException(message);
                    }
                    Task task = this.taskList.completeTask(taskNumber);
                    output = "Nice! I've marked this task as done: \n" + task.toString();

                } else if (instruction.equals("delete")) {
                    if (inputArr.length <= 1) {
                        // EXCEPTION
                        String message = UI.stringWrapper("☹ OOPS!!! Please specify a task number to be deleted!");
                        throw new DukeException(message);
                    }
                    int taskNumber = Integer.parseInt(inputArr[1]);
                    if (taskNumber > this.taskList.getSize()) {
                        // EXCEPTION
                        String message = UI.stringWrapper("☹ OOPS!!! Please specify a valid task number!");
                        throw new DukeException(message);
                    }
                    Task task = this.taskList.removeTask(taskNumber);
                    output = "Noted! I've removed this task: \n"
                            + task.toString() + "\n"
                            + "Now you have " + this.taskList.getSize()
                            + " tasks in the list.";;

                } else if (instruction.equals("list")) {
                    output = "Here are the tasks in your list: \n" + this.taskList.getTaskList();

                } else if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")){

                    if (inputArr.length <= 1) {
                        // EXCEPTION
                        String message = UI.stringWrapper("☹ OOPS!!! Please enter the descriptions for your to-do list!");
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
                    this.taskList.addTask(newTask);
                    output = "Got it. I've added this task: \n"
                            + newTask.toString() + "\n"
                            + "Now you have " + this.taskList.getSize()
                            + " tasks in the list.";

                } else {
                    // EXCEPTION
                    String message = UI.stringWrapper("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    throw new DukeException(message);
                }

                storage.saveTaskList(this.taskList);
                output = UI.stringWrapper(output);
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

}