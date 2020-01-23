import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    /**
     * isInRange method verifies whether the index
     * provided is within the range or not.
     * @param index the index of the task
     * @return the boolean value of whether the index given is
     * within the valid range.
     */

    public boolean isInRange(int index) {
        return index > 0 && index <= tasks.size() && tasks.size() > 0;
    }

    /**
     * markDone method marks the task of a particular index
     * to be done, and inform the client later. This method is
     * being called when a done command is entered by a client.
     * @param index The index of a particular task in the task list.
     */

    public void markDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.printf("   %s\n", task.toString());
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    /**
     * getTask method gets the task of a given index
     * from the list of tasks. The index value is normalized
     * by subtracting the value with 1 since the value starts
     * from 1.
     * @param index The index of the task in the list.
     * @return The Task instance of an index provided by the client.
     */

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * listTasks method prints all the tasks stored in the ArrayList
     * of the Duke instance.
     */

    public void listTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d. %s\n", i, tasks.get(i - 1));
        }
    }

    /**
     * addTask method adds the task with the corresponding
     * type, and the description provided by the client.
     * @param command The type of task that is requested.
     * @param description The description of the task.
     * @throws DukeException If there is a format error in the command.
     */

    public void addTask(Command command, String description) throws DukeException {
        Task new_task;
        String[] description_with_schedule;

        /*
         The switch block uses to provide the appropriate task
         as requested by the client. The default block is used
         for the EVENT case.
         */

        switch (command) {
            case TODO:
                new_task = new Todo(description);
                break;

            case DEADLINE:
                description_with_schedule = description.split(" /by ");
                try {
                    String caption = description_with_schedule[0];
                    String by_schedule = description_with_schedule[1];
                    new_task = new Deadline(caption, by_schedule);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You should indicate the date of the deadline.");
                }
                break;

            default:
                description_with_schedule = description.split(" /at ");
                try {
                    String caption = description_with_schedule[0];
                    String at_schedule = description_with_schedule[1];
                    new_task = new Event(caption, at_schedule);

                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You should indicate the date of the event.");
                }
                break;
        }

        tasks.add(new_task);
        System.out.println("Got it. I've added this task: ");
        System.out.printf("    %s\n", new_task);
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    /**
     * deleteTask deletes the task of a particular index
     * from the list.
     * @param index The index of the task in the list to be deleted.
     * @throws DukeException If the index given is not within the valid range.
     */

    public void deleteTask(int index) throws DukeException {
        if (!isInRange(index)) {
            throw new DukeException("☹ OOPS!!! The index given is out of bound.");
        }

        Task task = getTask(index);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.printf("    %s\n", task);
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    /**
     * processCommand method parses the input entered by the client.<br>
     *     The following are valid commands that Duke can process:
     *     <ul>
     *         <li><tt>list</tt> - lists all the tasks that Duke has stored.</li>
     *         <li><tt>done [index]</tt> - marks the task of a particular index as done.</li>
     *         <li><tt>delete [index]</tt> - deletes the task of a particular index.</li>
     *         <li><tt>todo [description]</tt> - adds the Todo task to the list.</li>
     *         <li><tt>deadline [description] /by [date]</tt> - adds the Deadline task to the list.</li>
     *         <li><tt>event [description] /at [date]</tt> - adds the Event task to the list.</li>
     *     </ul>
     * @param arguments The instruction provided by the client.
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
                    Argument.isValidListCommand();
                    listTasks();
                    break;

                case DONE:
                    try {
                        String index_string = arguments.split(" ", 2)[1];
                        int index = Integer.parseInt(index_string);
                        markDone(index);

                    } catch (NumberFormatException e) {
                        throw new DukeException("☹ OOPS!!! The done argument requires a number.");
                    }
                    break;

                case DELETE:
                    try {
                        String index_string = arguments.split(" ", 2)[1];
                        int index = Integer.parseInt(index_string);
                        deleteTask(index);

                    } catch (NumberFormatException e) {
                        throw new DukeException("☹ OOPS!!! The done argument requires a number.");
                    }
                    break;
                default:
                    String[] command_and_description = arguments.split(" ", 2);
                    try {
                        String description = command_and_description[1];
                        addTask(command, description);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(("☹ OOPS!!! The number of arguments is not sufficient."));
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
            }
        } catch (DukeException exc) {
            System.err.println(exc);
        }
    }
}
