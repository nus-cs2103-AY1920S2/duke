import java.util.ArrayList;

/**
 * Contains the task list, as well as operations to amend existing tasks in thelist such as add/delete.
 */
public class TaskList {
    public ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * marks the task to be done.
     * @param option Specific index of task in the list.
     */
    public void markAsDone(int option) {
        arr.get(option - 1).setDone();
        System.out.println("Nice! I've marked this task as done:\n  " + arr.get(option - 1));
    }

    /**
     * removes the task from the list.
     * @param option Specific index of task in the list.
     */
    public void deleteTask(int option) {
        System.out.println("Noted. I've removed this task: \n  " + arr.get(option - 1));
        arr.remove(option - 1);
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
    }

    /**
     * add tasks to the list and write the updated tasks in file.
     * @param inputArr InputArr breakdown of the specific task.
     * @param storage Storage object linked.
     * @throws DukeException If wrong inputs or missing chunks of inputs are given.
     */
    public void addAndWriteTask(String[] inputArr, Storage storage) {
        try {
            switch (inputArr[0]) {
                case "todo":
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        System.out.println("Got it. I've added this task: ");
                        Task task = new Todo(inputArr[1]);
                        arr.add(task);
                        storage.appendFile(task);
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
                            arr.add(task);
                            storage.appendFile(task);
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
                            arr.add(task);
                            storage.appendFile(task);
                        }
                    }
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("  " + arr.get(arr.size() - 1));
            System.out.println("Now you have " + arr.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
