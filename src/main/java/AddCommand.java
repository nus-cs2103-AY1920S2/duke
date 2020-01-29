import java.time.DateTimeException;
import java.util.ArrayList;

public class AddCommand extends Command {

    private String type;

    public AddCommand(String response, String type) {

        super(response);
        this.type = type;

    }

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
