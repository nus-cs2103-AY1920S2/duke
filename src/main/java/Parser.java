import java.io.IOException;

public class Parser {

    protected static boolean parseCommand(String input, Storage storage, TaskList taskList) throws DukeException, IOException {

        String[] inputArr = input.split(" ");
        String instruction = inputArr[0];

        switch (instruction) {
            case "done": {
                if (inputArr.length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please specify a task number to mark as done!");
                }
                int taskNumber = Integer.parseInt(inputArr[1]);
                if (taskNumber > taskList.getSize()) {
                    throw new DukeException("☹ OOPS!!! Please specify a valid task number!");
                }
                Task task = taskList.completeTask(taskNumber);
                UI.printMessage("Nice! I've marked this task as done: \n" + task.toString());
                break;
            }
            case "delete": {
                if (inputArr.length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please specify a task number to be deleted!");
                }
                int taskNumber = Integer.parseInt(inputArr[1]);
                if (taskNumber > taskList.getSize()) {
                    throw new DukeException("☹ OOPS!!! Please specify a valid task number!");
                }
                Task task = taskList.removeTask(taskNumber);
                UI.printMessage("Noted! I've removed this task: \n"
                        + task.toString() + "\n"
                        + "Now you have " + taskList.getSize()
                        + " tasks in the list.");
                break;
            }
            case "todo": {
                if (inputArr.length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
                }
                int idx = input.indexOf(" ");
                String taskInput = input.substring(idx + 1);
                String[] taskInputArr = taskInput.split("/");
                String taskName = taskInputArr[0];
                Task newTask = new ToDo(taskName);
                taskList.addTask(newTask);
                UI.printMessage("Got it. I've added this task: \n"
                        + newTask.toString() + "\n"
                        + "Now you have " + taskList.getSize()
                        + " tasks in the list.");
                break;
            }
            case "deadline": {
                if (inputArr.length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
                }
                int idx = input.indexOf(" ");
                String taskInput = input.substring(idx + 1);
                String[] taskInputArr = taskInput.split("/");
                String taskName = taskInputArr[0];
                String dateTime = taskInputArr[1];
                idx = dateTime.indexOf(" ");
                dateTime = dateTime.substring(idx + 1);
                Task newTask = new Deadline(taskName, dateTime);
                taskList.addTask(newTask);
                UI.printMessage("Got it. I've added this task: \n"
                        + newTask.toString() + "\n"
                        + "Now you have " + taskList.getSize()
                        + " tasks in the list.");
                break;
            }
            case "event": {
                if (inputArr.length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
                }
                int idx = input.indexOf(" ");
                String taskInput = input.substring(idx + 1);
                String[] taskInputArr = taskInput.split("/");
                String taskName = taskInputArr[0];
                String dateTime = taskInputArr[1];
                System.out.println("here:" + dateTime);
                idx = dateTime.indexOf(" ");
                dateTime = dateTime.substring(idx + 1);
                Task newTask = new Event(taskName, dateTime);
                taskList.addTask(newTask);
                UI.printMessage("Got it. I've added this task: \n"
                        + newTask.toString() + "\n"
                        + "Now you have " + taskList.getSize()
                        + " tasks in the list.");
                break;
            }
            case "list":
                UI.printMessage("Here are the tasks in your list: \n" + taskList.getTaskList());
                break;
            case "bye":
                UI.printMessage("Bye. Hope to see you again soon!");
                return true;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        storage.saveTaskList(taskList);
        return false;

    }


}
