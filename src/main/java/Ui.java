import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String BORDER = "**********************************************************************";

    public Ui() {
    }

    protected void showWelcome() {
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(BORDER + "\n  " + greeting + "\n" + BORDER);
    }

    protected void showLoadingError() {
        System.out.println();
    }

    protected void print(String toPrint) {
        System.out.println(BORDER + toPrint + BORDER);
    }

    protected void start(){
        // Create new Scanner object to read user input.
        Scanner sc = new Scanner(System.in);

        // Initialise variables needed
        String input = sc.next();
        String taskAdd = "Noted. The following task has been added:\n   ";
        String[] split;
        String description;

        // Check if user is done with Duke
        while (!input.equals("bye")) {
            try{
                switch (input) {
                case "list":
                    // Check if list of tasks is empty
                    if (Duke.tasks.isEmpty()) {
                        this.print("\n  There are currently no items in your list. \n");
                        break;
                    }

                    // Initialise String builder to hold list of Task objects.
                    StringBuilder sb = new StringBuilder();

                    // Append each Task object to string builder.
                    sb.append("\n  " + "The items in your list are: \n");
                    for (int i = 1; i <= Duke.tasks.getSize(); i++) {
                        sb.append("  ").append(i).append(". ")
                                .append(Duke.tasks.getTask(i - 1)).append("\n");
                    }

                    // Print out list of tasks to user.
                    this.print(sb.toString());

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    break;
                case "done":
                    // Retrieve Task object at user inputted index.
                    Task task = Duke.tasks.getTask(sc.nextInt() - 1);

                    // Mark retrieved Task object as done.
                    task.done();

                    // Output success message
                    this.print("\n  " + "Well Done! The task has been marked as done."
                            + "\n  " + task + "\n");

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    break;
                case "todo":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Check if description is valid.
                    if (description.isBlank()) {
                        throw new DukeException("My apologies, " +
                                "description of todo is empty. :(");
                    }

                    // Create new Task of type todo.
                    Task todo = new Task(Duke.Tasks.todo, description);

                    // Add new Task object to list.
                    Duke.tasks.addTask(todo);

                    // Output success message.
                    this.print("\n  " + taskAdd + todo + "\n  There are currently "
                            + Duke.tasks.getSize() + " tasks in the list.\n");

                    // Update safe file.
                    Duke.storage.write(todo);

                    break;
                case "deadline":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Split given description
                    split = description.split("/by ");

                    // Check if sufficient details were provided in description.
                    if (split.length < 2) {
                        throw new DukeException("My apologies, " +
                                "deadline description has insufficient details :(");
                    }

                    // Create new Task object of task type deadline.
                    Task deadline = new Task(Duke.Tasks.deadline, split[0], split[1]);

                    // Add deadline to list.
                    Duke.tasks.addTask(deadline);

                    // Output success message.
                    this.print("\n  " + taskAdd + deadline + "\n  There are currently "
                            + Duke.tasks.getSize() + " tasks in the list.\n");

                    // Update save file.
                    Duke.storage.write(deadline);

                    break;
                case "event":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Split given description
                    split = description.split("/at ");

                    // Check if given description has sufficient details.
                    if (description.isBlank()) {
                        throw new DukeException("My apologies, " +
                                "event description has insufficient details. :(");
                    }

                    // Create new Task object of event task type.
                    Task event = new Task(Duke.Tasks.event, split[0], split[1]);

                    // Add event to list.
                    Duke.tasks.addTask(event);

                    // Output success message.
                    this.print("\n  " + taskAdd + event + "\n  There are currently "
                            + Duke.tasks.getSize() + " tasks in the list.\n");

                    // Update save file.
                    Duke.storage.write(event);

                    break;
                case "delete":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Check if given description has sufficient details.
                    if (description.isBlank()) {
                        throw new DukeException("My apologies," +
                                " task number to be deleted not found :(");
                    }

                    // Parse given int in description to int type.
                    int delete = Integer.parseInt(description.trim());

                    // Check if given int is greater than number of tasks in list.
                    if (delete > Duke.tasks.getSize()) {
                        throw new DukeException("My apologies," +
                                " task number to be deleted not found :(");
                    }

                    // Retrieve details on Task object to be deleted.
                    Task removed = Duke.tasks.getTask(delete - 1);

                    // Remove task from list.
                    Duke.tasks.deleteTask(delete - 1);

                    // Output success message.
                    this.print("\n  Noted. Task " + delete + " has been removed:\n  "
                            + removed + "\n  There are currently " + Duke.tasks.getSize()
                            + " tasks in the list.\n");

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    break;
                default:
                    throw new DukeException("My apologies," +
                            " I do not recognise the command. :(");
                }
                } catch (DukeException e) {
                    this.print("\n  " + e.getMessage() + "\n");
                }
                input = sc.next();
            }
        this.print("\n Goodbye. I hope I was useful. See you again.\n");
        sc.close();
    }
}
