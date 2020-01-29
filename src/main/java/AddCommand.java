import java.time.DateTimeException;
import java.util.ArrayList;

/**
 * AddCommand allows instructions to be added to the task list.
 */
public class AddCommand extends Command {

    private String type;

    /**
     * Constructs an AddCommand class.
     * @param response instruction to be executed.
     * @param type the type of instruction consisting one of the following: todo, deadline or event.
     */
    public AddCommand(String response, String type) {

        super(response);
        this.type = type;

    }

    /**
     * Execute the command that is given, based on it being a todo task, a deadline task or an event task.
     * @param tasksStorage storage for the task, deals with storing data and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException if it is not a valid instruction.
     */
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        if (this.type.equals("todo")) {

            createTodo(this.response, taskList.getList());

        } else if (this.type.equals("deadline")) {

            createDeadline(this.response, taskList.getList());

        } else if (this.type.equals("event")) {

            createEvent(this.response, taskList.getList());

        } else {

            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }


    /**
     * Creates a todo task.
     * @param response the instruction that was given by the user to be carried out.
     * @param mylist the list where the todo task is to be stored.
     * @throws DukeException if the description is empty.
     */
    private void createTodo(String response, ArrayList<Task> mylist) throws DukeException {

        String description = response.replace("todo", "").trim();

        if (!description.equals("")) {

            Task newTask = new Todo(description);
            mylist.add(newTask);
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("     Now you have %d tasks in the list.\n", mylist.size());

        } else {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Creates a deadline task.
     * @param response the instruction that was given by the user to be carried out.
     * @param mylist the list where the deadline task is to be stored.
     * @throws DukeException if there is any error in the date and time or the description is empty.
     */
    private void createDeadline(String response, ArrayList<Task> mylist) throws DukeException {

        try {

            String deadline = response.split("/")[1];
            String description = response.split("/")[0].replace("deadline ", "");
            Task newTask = new Deadline(description, deadline);
            mylist.add(newTask);
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("     Now you have %d tasks in the list.\n", mylist.size());

        } catch (DateTimeException ex) {

            throw new DukeException("     ☹ OOPS!!! Please format your date and time correctly.");

        } catch (Exception ex) {

            throw new DukeException("     ☹ OOPS!!! The description or deadline of a deadline cannot be empty.");

        }

    }

    /**
     * Creates an event task.
     * @param response the instruction that was given by the user to be carried out.
     * @param mylist the list where the event task is to be stored.
     * @throws DukeException if the description is empty.
     */
    private void createEvent(String response, ArrayList<Task> mylist) throws DukeException {

        try {

            String eventTiming = response.split("/")[1];
            String description = response.split("/")[0].replace("event ", "");
            Task newTask = new Event(description, eventTiming);
            mylist.add(newTask);
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("      Now you have %d tasks in the list.\n", mylist.size());

        } catch (Exception ex) {

            throw new DukeException("     ☹ OOPS!!! The description or event timing of a event cannot be empty.");

        }

    }
}
