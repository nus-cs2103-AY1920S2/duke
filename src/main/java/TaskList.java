import java.util.ArrayList;

/**
 * TaskList contains the task list and all its operations to add/delete tasks in the list.
 * Contains a .run() method as well to run the Command related to the Task.
 */
public class TaskList {
    private ArrayList<Task> listOfTexts;

    /**
     * Constructor for TaskList called when there is an existing task list to show
     *
     * @param listOfTasks is the list of tasks in duke.txt
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTexts = listOfTasks;
    }

    public TaskList() {
        this.listOfTexts = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.listOfTexts.add(t);
    }

    public ArrayList<Task> getTasks() {
        return this.listOfTexts;
    }

    /**
     * Prints out all tasks currently in the to do list.
     */
    public void showCurrentTasks() {
        int counter = 1;
        if (listOfTexts.size() == 0) {
            Ui.printEmptyToDoList();
        }
        for (int i = 0; i < listOfTexts.size(); i++) {
            System.out.println(counter + ". " + listOfTexts.get(i));
            counter++;
        }
    }

    public void showSearch(String stringToSearch) {
        ArrayList<Task> searchList = new ArrayList<>();
        for (int i = 0; i < listOfTexts.size(); i++) {
            String taskDescription = listOfTexts.get(i).getDescription();
            if (taskDescription.contains(stringToSearch)) {
                searchList.add(listOfTexts.get(i));
            }
        }
        Ui.printBeforeSearch();
        Ui.printSearch(searchList);
    }

    /**
     * Applies the action on the TaskList as indicated by the type of Command.
     *
     * @param command Type of Command described by .getCommand() which return a <code>String</code> description.
     */
    public void runCommand(Command command) {
        assert (command != null) : "Input something correct into me or I will stop! todo/deadline(/by)/event(/at)/fixedtask(/needs)/find/list/delete/done";
        try {
            switch (command.getCommand()) {
                case "bye": {
                    break;
                }
                case "list": {
                    showCurrentTasks();
                    break;
                }
                case "find": {
                    showSearch(command.getTaskToSearch());
                    break;
                }
                case "done": {
                    Task currentTask = this.listOfTexts.get(command.getIndex());
                    currentTask.markAsDone();
                    Ui.printAfterDone(currentTask);
                    break;
                }
                case "delete": {
                    Task currentTask = this.listOfTexts.get(command.getIndex());
                    listOfTexts.remove(command.getIndex());
                    Ui.printAfterDelete(currentTask, listOfTexts);
                    break;
                }
                case "todo": {
                    Task currentTask = command.getTask();
                    addTask(currentTask);
                    Ui.printAfterTodo((Todo) currentTask, listOfTexts);
                    break;
                }
                case "deadline": {
                    Task currentTask = command.getTask();
                    addTask(currentTask);
                    Ui.printAfterDeadline((Deadline) currentTask, listOfTexts);
                    break;

                }
                case "event": {
                    Task currentTask = command.getTask();
                    addTask(currentTask);
                    Ui.printAfterEvent((Event) currentTask, listOfTexts);
                    break;
                }
                case "fixedTask": {
                    Task currentTask = command.getTask();
                    addTask(currentTask);
                    Ui.printAfterFixedTask((FixedDurationTask) currentTask, listOfTexts);
                    break;
                }
                default: {
                    throw new DukeException("☹ OOPS!!! I'm sorry, you have entered wrong command. Error in TaskList! ☹ OOPS!!!");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
