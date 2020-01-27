import java.io.IOException;
import java.util.ArrayList;

/*
 * Duke
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The Duke class is the main class of the bot,
 * where the command processing happens.</p>
 * @author Mario Lorenzo
 */

public class Duke {
    private ArrayList<Task> tasks;

    /**
     * Constructs the Duke instance that has a list that
     * stores tasks, as well as the list of all valid commands
     * that the Duke instance can perform, stored in a HashMap.
     */

    private Duke(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a Duke instance with a factory method fashion.
     * A Duke instance with the list of tasks loaded will be created
     * if the file is formatted properly.
     * @return A Duke instance, loaded with the list of tasks.
     * @throws DukeInvalidTaskFormatException If the file is not formatted properly.
     */

    public static Duke start() throws DukeInvalidTaskFormatException {
        TaskReader reader = new TaskReader("./data/tasks.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = reader.loadTasks();
        } catch (IOException e) {
            System.err.println(e);
        } catch (DukeInvalidTaskFormatException e) {
            throw e;
        }
        return new Duke(tasks);
    }

    /**
     * Verifies whether the index
     * provided is within the range or not.
     * @param index the index of the task
     * @return the boolean value of whether the index given is
     * within the valid range.
     */

    public boolean isNotInRange(int index) {
        return index <= 0 || index > tasks.size() || tasks.size() <= 0;
    }

    /**
     * Marks the task of a particular index
     * to be done, and inform the client later. This method is
     * being called when a done command is entered by a client.
     * @param index The index of a particular task in the task list.
     * @throws DukeInvalidArgumentFormatException If the index given is out of bound.
     */

    public void markDone(int index) throws DukeInvalidArgumentFormatException {
        if (isNotInRange(index)) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }

        Task task = getTask(index);
        task.markAsDone();
        rewriteTasksToFile();

        System.out.println("Nice! I've marked this task as done: ");
        System.out.printf("   %s\n", task.toString());
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    /**
     * Gets the task of a given index
     * from the list of tasks. The index value is normalized
     * by subtracting the value with 1 since the value starts
     * from 1.
     * @param index The index of the task in the list.
     * @return The Task instance of an index provided by the client.
     */

    private Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Prints all the tasks stored in the ArrayList
     * of the Duke instance.
     */

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("The list is currently empty. Fill me please!");
        }
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d. %s\n", i, tasks.get(i - 1));
        }
    }

    /**
     * Adds the task with the corresponding
     * type, and the description provided by the client.
     * @param argument The argument of the add task.
     * @throws DukeInvalidArgumentFormatException If there is a format error in the command.
     */

    public void addTask(Argument argument) throws DukeInvalidArgumentFormatException {
        Command command = argument.getCommand();
        Task newTask;
        TaskWriter writer = new TaskWriter("./data/tasks.txt");

        /*
         The switch block uses to provide the appropriate task
         as requested by the client. The default block is used
         for the EVENT case.
         */

        switch (command) {
        case TODO:
            newTask = new Todo(argument.checkValidTodoArgument());
            break;

        case DEADLINE:
            Pair<String, String> description_schedule_pair = argument.checkValidDeadlineArgument();
            String description = description_schedule_pair.getFirst();
            String schedule = description_schedule_pair.getSecond();
            newTask = new Deadline(description, schedule);
            break;

        default:
            Pair<String, String> description_date_pair = argument.checkValidEventArgument();
            String desc = description_date_pair.getFirst();
            String date = description_date_pair.getSecond();
            newTask = new Event(desc, date);
            break;
        }

        tasks.add(newTask);
        try {
            writer.writeTask(newTask, true);
            System.out.println("Got it. I've added this task: ");
            System.out.printf("    %s\n", newTask);
            System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Deletes the task of a particular index
     * from the list, then remove it from the file.
     * @param index The index of the task in the list to be deleted.
     * @throws DukeInvalidArgumentFormatException If the index given is not within the valid range.
     */

    public void deleteTask(int index) throws DukeInvalidArgumentFormatException {
        if (isNotInRange(index)) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }

        Task task = getTask(index);
        tasks.remove(index - 1);
        rewriteTasksToFile();
        System.out.println("Noted. I've removed this task: ");
        System.out.printf("    %s\n", task);
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    /**
     * Parses the input entered by the client.<br>
     *     The following are valid commands that Duke can process:
     *     <ul>
     *         <li><tt>list</tt> - lists all the tasks that Duke has stored.</li>
     *         <li><tt>done [index]</tt> - marks the task of a particular index as done.</li>
     *         <li><tt>delete [index]</tt> - deletes the task of a particular index.</li>
     *         <li><tt>todo [description]</tt> - adds the Todo task to the list.</li>
     *         <li><tt>deadline [description] /by [date]</tt> - adds the Deadline task to the list.</li>
     *         <li><tt>event [description] /at [date]</tt> - adds the Event task to the list.</li>
     *     </ul>
     * @param commands The instruction provided by the client.
     */

    public void processCommand(String commands) {
        try {
            Argument argument = Argument.createArgument(commands);
            Command command = argument.getCommand();

            /*
             The switch block performs the corresponding command,
             as requested by the client. The default method is being
             used for adding the tasks to the list. A DukeException
             will be thrown if the input format is not valid.
             */

            switch (command) {
            case LIST:
                argument.checkValidListArgument();
                listTasks();
                break;

            case DONE:
                int index = argument.checkValidDoneArgument();
                markDone(index);
                break;

            case DELETE:
                int value = argument.checkValidDeleteArgument();
                deleteTask(value);
                break;

            default:
                addTask(argument);
                break;
            }
        } catch (DukeInvalidArgumentFormatException | DukeUnknownKeywordException exc) {
            System.err.println(exc);
        }
    }

    /**
     * Rewrites the list of tasks to the file. This method is being triggered
     * by {@code markDone} and {@code deleteTask} method.
     */

    private void rewriteTasksToFile() {
        try {
            TaskWriter writer = new TaskWriter("./data/tasks.txt");
            for (int i = 0; i < tasks.size(); i++) {
                writer.writeTask(tasks.get(i), i != 0);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
