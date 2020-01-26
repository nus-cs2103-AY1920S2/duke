import java.util.ArrayList;

// Deals with interactions with the user
// Shows the lines, errors etc
class Ui {

    //private TaskList taskList = new TaskList();
    private Deadline_event_hash deadline_event_hash = new Deadline_event_hash();
    String lines = "        ____________________________________________________________";
    String space = "        ";

    void printBye() {
        System.out.println(lines);
        System.out.println("        Bye. Hope to see you again soon");
        System.out.println(lines);
    }


    void printList(TaskList taskList) {
        System.out.println(lines);
        taskList.print_elements();
        System.out.println(lines);
    }

    // When the user enters a number which is larger than the taskList
    void invalid_number_exception() throws DukeException {
        throw new DukeException("You have entered an invalid number!");
    }

    // Tells the user that the task is done.
    void printDone(Task finished_task) {
        System.out.println(lines);
        System.out.println(space + "Nice! I've marked this task as done");
        System.out.println(space + " [" + finished_task.getStatusIcon() + "]"
                + " " + finished_task.getDescription());
        System.out.println(lines);
    }

    void printTasks(Task task, ArrayList<Task> list) {
        System.out.println(lines);
        System.out.println(space + " Got it. I've added this task: ");
        System.out.println(space + task);
        System.out.println(space + " Now you have " + (list.size()) + " tasks in the list.");
        System.out.println(lines);
    }

    void printDelete(Task deleted_task, TaskList taskList) {
        System.out.println(lines);
        System.out.println(space + "Noted. I've removed this task:");
        System.out.println(space + deleted_task);
        System.out.println(space + "Now you have " + taskList.size_of_list() + " tasks in the list.");
        System.out.println(lines);
    }

    void printDontUnderstandInput () throws DukeException{
        System.out.println(lines);
        throw new DukeException( "I'm sorry but I do not know what taht means :-(");
    }

    // Handles any other forms of exceptions. Eg in deadline etc.
    void duke_exceptions(String s) throws DukeException{
        throw new DukeException(s);
    }
}
