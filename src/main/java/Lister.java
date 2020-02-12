/**
 * The Lister Class parses commands and references TaskList and Storage Classes to store and retrieve Tasks.
 * @author qiujingying
 * @version 1.0
 */
public class Lister {
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a Lister object to reference to TaskList and Storage objects.
     * @param tasks initial TaskList stores Tasks
     * @param storage initial storage with filepath to store data
     */
    public Lister(TaskList tasks, Storage storage) {
        this.taskList = tasks;
        this.storage = storage;
    }

    /**
     * Parses commands from command line.
     * @param command input to identify relevant Task objects to create and their description
     */
    public String record(String command) {
        StringBuilder sb = new StringBuilder();
        if (command.equals("list")) {
            sb.append("Here are the tasks in your list: \n");
            for (int i = 0; i < taskList.getSize(); i++) {
                sb.append((i + 1) + "." + taskList.retrieveTask(i).toString() + "\n");
            }
        } else {
            try {
                int x = getIndex(command);
                switch (command.substring(0, x)) {
                case "delete":
                    int b = Integer.valueOf(command.substring(x + 1)) - 1;
                    taskList.removeTask(b);
                    sb.append("Noted. I've removed this task:\n" + taskList.retrieveTask(b).toString() + "\n");
                    sb.append("Now you have " + taskList.getSize() + " tasks in the list.\n");
                    break;
                case "done":
                    int y = Integer.valueOf(command.substring(x + 1)) - 1;
                    taskList.retrieveTask(y).markAsDone();
                    sb.append("Nice! I've marked this task as done:\n" + taskList.retrieveTask(y).toString() + "\n");
                    break;
                case "deadline":
                    int z = command.indexOf('/');
                    Task newDeadline = new Deadline(command.substring(x + 1, z - 1), command.substring(z + 4));
                    taskList.addTask(newDeadline);
                    sb.append(stringTask(newDeadline));
                    break;
                case "event":
                    int a = command.indexOf('/');
                    Task newEvent = new Event(command.substring(x + 1, a - 1), command.substring(a + 4));
                    taskList.addTask(newEvent);
                    sb.append(stringTask(newEvent));
                    break;
                case "todo":
                    Task newToDo = new ToDo(command.substring(x + 1), "");
                    taskList.addTask(newToDo);
                    sb.append(stringTask(newToDo));
                    break;
                case "find":
                    TaskList tempList = new TaskList();
                    String description = command.substring(x + 1);
                    for (int i = 0; i < taskList.getSize(); i++) {
                        Task tempTask = taskList.retrieveTask(i);
                        if (tempTask.description.equals(description)) {
                            tempList.addTask(tempTask);
                        }
                    }

                    try {
                        tempList.retrieveTask(0);
                    } catch (IndexOutOfBoundsException e) {
                        sb.append("Sorry there are no such tasks.");
                        break;
                    }

                    sb.append("Here are the matching tasks in your list:\n");
                    for (int i = 0; i < tempList.getSize(); i++) {
                        sb.append((i + 1) + "." + tempList.retrieveTask(i).toString() + "\n");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command.substring(0, x));
                }
            } catch (DukeException e) {
                sb.append(e.getMessage());
            }
        }
        storage.storeData(taskList);
        return sb.toString();
    }

    private int getIndex(String command) throws DukeException {
        int x;
        try {
            x = command.indexOf(' ');
//            if (x < 0) {
//                throw new StringIndexOutOfBoundsException();
//            }
            assert x >= 0;
        } catch (StringIndexOutOfBoundsException e) {
            switch (command) {
            case "todo":
                throw new DukeException("The description of a todo cannot be empty");
            default:
                throw new DukeException("I'm sorry but I don't know what that means :(");
            }
        }
        return x > 0 ? x : 0;
    }

    private String stringTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n" + task.toString());
        sb.append("\nNow you have " + taskList.getSize() + " tasks in the list.");
        return sb.toString();
    }
}
