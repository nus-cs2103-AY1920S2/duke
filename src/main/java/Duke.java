import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Duke {
    private ArrayList<Task> tasks;
    private HashMap<String, Command> valid_commands;

    public Duke() {
        this.tasks = new ArrayList<>();
        this.valid_commands = new HashMap<String, Command>() {
            {
                put("list", Command.LIST);
                put("done", Command.DONE);
                put("todo", Command.TODO);
                put("deadline", Command.DEADLINE);
                put("event", Command.EVENT);
            }
        };
    }

    public void listTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d. %s\n", i, tasks.get(i - 1));
        }
    }

    public void markDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.printf("   %s\n", task.toString());
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    public void addTask(Command command, String description) throws DukeException {
        Task new_task;
        String[] description_with_schedule;
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
    
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public Optional<Command> getCommand(String command_string) {
        return Optional.ofNullable(valid_commands.get(command_string));
    }

    public void processCommand(String arguments) {
        String[] splitted_arguments = arguments.split(" ");
        String command_string = splitted_arguments[0];

        try {
            Optional<Command> boxed_command = getCommand(command_string);
            Command command = boxed_command.orElseThrow(() ->
                    new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));

            switch (command) {
                case LIST:
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
