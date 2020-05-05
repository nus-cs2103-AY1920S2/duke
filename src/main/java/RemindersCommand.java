import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class RemindersCommand extends Command {

    private ArrayList<Deadline> deadlineList;
    private HashSet<Deadline> deadlineSet = new HashSet<>();

    /**
     * Create a reminders command to show reminder for the upcoming deadline objects.
     */
    public RemindersCommand() {
        super();


    }

    /**
     * Executes the reminders command, showing all deadline task that are currently in the program.
     * @param tasksStorage storage for the task, deals with storing data and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException NIL
     */
    @Override
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        setUpDeadlineList(taskList);
        printDeadlineList();


    }

    /**
     * Retrieve all deadline tasks and store them into a list. Sort them and show the reminders as a response.
     * @param taskList the list where all the tasks is being stored.
     */
    private void setUpDeadlineList(TaskList taskList) {

        if (this.deadlineList == null) {

            this.deadlineList = new ArrayList<>();

            for (Task tasks : taskList.getList()) {

                if (tasks instanceof Deadline) {
                    this.deadlineList.add((Deadline) tasks);
                    this.deadlineSet.add((Deadline) tasks);
                }
            }

        } else {
            for (Task tasks : taskList.getList()) {

                if (tasks instanceof Deadline) {
                    if (deadlineSet.contains(tasks)) {
                        continue;

                    } else {
                        this.deadlineList.add((Deadline) tasks);
                        this.deadlineSet.add((Deadline) tasks);

                    }
                }
            }
        }
        Collections.sort(this.deadlineList);

    }

    /**
     * Print out the deadline list.
     */
    private void printDeadlineList() {

        if (this.deadlineList.size() <= 0) {

            System.out.println("     You do not have any upcoming deadline tasks in your list.");
        } else {
            System.out.println("     Here are upcoming deadline tasks in your list:");
            for (int i = 0; i < this.deadlineList.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + deadlineList.get(i));
            }


        }
    }
}
