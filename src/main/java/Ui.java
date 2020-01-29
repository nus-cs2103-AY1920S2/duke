
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Parser parser = new Parser();
    TaskList mylist;

    public Ui(TaskList tasks) {
        this.mylist = tasks;

        System.out.println("Hello! I'm Duke\n"
                + "     What can I do for you?");

    }

    public boolean readLine(Scanner sc) {

        String response = sc.nextLine();
        try {
            String messageType = parser.checkMessageType(response);
            if (messageType.equals("bye")) {
                System.out.println("     ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("     ____________________________________________________________");
                return false;

            } else {
                printAction(messageType, mylist.getList(), response);
                return true;
            }

        } catch (DukeException ex) {
            System.out.println(ex.getMessage());

        }
        return true;

    }

    private void printFormatting() {
        System.out.println("     ____________________________________________________________");
    }

    private void printAction(String messageType, ArrayList<Task> mylist, String response) throws DukeException {
        if (messageType.equals("list")) {

            printFormatting();
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < mylist.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + mylist.get(i));
            }
            printFormatting();

        } else if (messageType.equals("done")) {

            int taskNumberDone = Integer.parseInt(response.split(" ")[1]);
            Task doneTask = mylist.get(taskNumberDone - 1);
            doneTask.completedTask();
            printFormatting();
            System.out.println("      Nice! I've marked this task as done: ");
            System.out.println("        " + doneTask);
            printFormatting();

        } else if (messageType.equals("todo")) {

            createTodo(response, mylist);

        } else if (messageType.equals("deadline")) {

            createDeadline(response, mylist);

        } else if (messageType.equals("event")) {

            createEvent(response, mylist);

        } else if (messageType.equals("delete")) {

            delete(response, mylist);

        }

    }

    private void createTodo(String response, ArrayList<Task> mylist) throws DukeException {

        String description = response.replace("todo", "").trim();

        if (!description.equals("")) {
            Task newTask = new Todo(description);
            mylist.add(newTask);
            printFormatting();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("     Now you have %d tasks in the list.\n", mylist.size());
            printFormatting();
        } else {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }
    }

    private void createDeadline(String response, ArrayList<Task> mylist) throws DukeException {

        try {

            String deadline = response.split("/")[1];
            String description = response.split("/")[0].replace("deadline ", "");
            Task newTask = new Deadline(description, deadline);
            mylist.add(newTask);
            printFormatting();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("     Now you have %d tasks in the list.\n", mylist.size());
            printFormatting();

        } catch (DateTimeException ex) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Please format your date and time correctly.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");

        } catch (Exception ex) {

            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description or deadline of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");

        }

    }

    private void createEvent(String response, ArrayList<Task> mylist) throws DukeException {

        try {

            String eventTiming = response.split("/")[1];
            String description = response.split("/")[0].replace("event ", "");
            Task newTask = new Event(description, eventTiming);
            mylist.add(newTask);
            printFormatting();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("      Now you have %d tasks in the list.\n", mylist.size());
            printFormatting();

        } catch (Exception ex) {

            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description or event timing of a event cannot be empty.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");

        }

    }

    private void delete(String response, ArrayList<Task> mylist) throws DukeException {

        int numberToDelete = Integer.parseInt(response.replace("delete" , "").trim());

        if (numberToDelete <= 0) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Please key in value bigger than 0.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }

        if (mylist.size() < numberToDelete) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! number does not exist in the list.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }


        Task removed = mylist.remove(numberToDelete - 1 );

        printFormatting();
        System.out.println("      Noted. I've removed this task: ");
        System.out.println("       " + removed);
        System.out.printf("      Now you have %d tasks in the list.\n", mylist.size());
        printFormatting();


    }

}
