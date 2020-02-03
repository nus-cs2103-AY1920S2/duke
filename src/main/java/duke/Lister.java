package duke;

/**
 * The Lister Class parses commands and references TaskList and Storage Classes to store and retrieve Tasks
 * @author qiujingying
 * @version 1.0
 */
public class Lister {
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a Lister object to reference to TaskList and Storage objects
     * @param tasks initial TaskList stores Tasks
     * @param storage initial storage with filepath to store data
     */
    public Lister(TaskList tasks, Storage storage) {
        this.taskList = tasks;
        this.storage = storage;
    }

    /**
     * Parses commands from command line
     * @param command input to identify relevant Task objects to create and their description
     */
    public void record(String command) {
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + "." + taskList.retrieveTask(i).toString());
            }
        } else {
            try {
                int x = getIndex(command);
                switch (command.substring(0, x)) {
                case "delete":
                    int b = Integer.valueOf(command.substring(x + 1)) - 1;
                    taskList.removeTask(b);
                    System.out.println("Noted. I've removed this task:\n" + taskList.retrieveTask(b).toString());
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;
                case "done":
                    int y = Integer.valueOf(command.substring(x + 1)) - 1;
                    taskList.retrieveTask(y).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + taskList.retrieveTask(y).toString());
                    break;
                case "deadline":
                    int z = command.indexOf('/');
                    Task newDeadline = new Deadline(command.substring(x + 1, z - 1), command.substring(z + 4));
                    taskList.addTask(newDeadline);
                    printTask(newDeadline);
                    break;
                case "event":
                    int a = command.indexOf('/');
                    Task newEvent = new Event(command.substring(x + 1, a - 1), command.substring(a + 4));
                    taskList.addTask(newEvent);
                    printTask(newEvent);
                    break;
                case "todo":
                    Task newToDo = new ToDo(command.substring(x + 1), "");
                    taskList.addTask(newToDo);
                    printTask(newToDo);
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        storage.storeData(taskList);
    }

    private int getIndex(String command) throws DukeException {
        int x;
        try {
            x = command.indexOf(' ');
            if (x < 0) {
                throw new StringIndexOutOfBoundsException();
            }
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

    private void printTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
