package Grapie;

import Grapie.Exceptions.ErrorMsg;
import Grapie.Exceptions.GrapieExceptions;

import java.io.IOException;
import java.util.List;

public class TaskList {
    private List<Task> storingList;
    private Storage storage;

    /**
     * Constructor for Grapie.TaskList.
     *
     * @param load    the arrayList obtained from Grapie.Storage class's load method. Contains task from
     *                hard disk.
     * @param storage Grapie.Storage class previously created from Duke.
     */
    public TaskList(List<Task> load, Storage storage) {
        //contains the task list e.g., it has operations to add/delete tasks in the list
        storingList = load;
        this.storage = storage;
    }

    /**
     * Format for printing the task added.
     *
     * @param task The task to be formatted.
     */
    public String printAddingTask(Task task) {
        String printStr = "Alrighty. I've added this task: \n"
                + task + "\n"
                + "Now you have " + storingList.size() + " tasks in the list.";

        return printStr;
    }

    /**
     * Check for todo, event, deadline and add to list correspondingly.
     * Also checks for invalid input.
     *
     * @param inputStr the user input.
     * @throws GrapieExceptions Errors thrown.
     * @throws IOException      Throws away the exception.
     */
    public String addToList(String inputStr) throws GrapieExceptions, IOException {
        if (inputStr.contains("todo")) {
            if (inputStr.substring(0, 4).equals("todo") && inputStr.length() > 5) {
                String detailsStr = inputStr.substring(5, inputStr.length());
                String checkIfTodoIsEmpty = detailsStr.replaceAll("\\s", "");

                assert(checkIfTodoIsEmpty.length() >= 0);

                if (checkIfTodoIsEmpty.length() == 0) {
                    //That means it is empty behing todo
                    throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
                } else {
                    Task todo = new Todo(detailsStr);
                    storingList.add(todo);
                    String result = printAddingTask(todo);

                    //store into hard disk
                    storage.convertToHardDiskFormatAndStore(todo, "T", "");

                    return result;
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }

        } else if (inputStr.contains("event")) {
            if (inputStr.substring(0, 5).equals("event") && inputStr.length() > 6) {
                inputStr = inputStr.trim();
                String[] eventAndTime = inputStr.substring(6, inputStr.length()).split(" /at ");

                assert(eventAndTime.length >= 0);

                if (eventAndTime.length <= 1) {
                    //not able to split string properly
                    throw new GrapieExceptions(ErrorMsg.eventFormatError);
                } else {
                    Event event = new Event(eventAndTime[0], eventAndTime[1]);
                    storingList.add(event);

                    //printing
                    String result = printAddingTask(event);

                    //store into hard disk
                    storage.convertToHardDiskFormatAndStore(event, "E", eventAndTime[1]);
                    return result;
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }

        } else if (inputStr.contains("deadline")) {
            if (inputStr.substring(0, 8).equals("deadline") && inputStr.length() > 9) {

                inputStr.trim();
                String[] eventAndTime = inputStr.substring(9, inputStr.length()).split(" /by ");

                assert(eventAndTime.length >= 0);

                if (eventAndTime.length > 1) {
                    Deadline deadline = new Deadline(eventAndTime[0], eventAndTime[1]);
                    storingList.add(deadline);

                    //print
                    String result = printAddingTask(deadline);

                    //store into hard disk
                    storage.convertToHardDiskFormatAndStore(deadline, "D", eventAndTime[1]);
                    return result;
                } else {
                    throw new GrapieExceptions(ErrorMsg.deadlineFormatError);
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }
        } else {
            throw new GrapieExceptions(ErrorMsg.wakarimasenError);
        }

    }

    /**
     * Help mark a task as completed (O).
     * Also checks if its a valid task number or if the task is already completed.
     *
     * @param doneTaskStr Grapie.Task the user wants to be marked as complete.
     * @throws GrapieExceptions Invalid task number, or already completed task thrown as error.
     * @throws IOException      Throws away the exception.
     */
    public String completeTask(String doneTaskStr) throws GrapieExceptions, IOException {
        //remember to add check for already completed task
        String strNumberDone = doneTaskStr.substring(5, doneTaskStr.length());
        strNumberDone.replaceAll("\\s+", ""); //remove all white spaces

        int numDone = Integer.parseInt(strNumberDone); //convert to number
        if (storingList.size() >= numDone && numDone != 0) {
            if (storingList.get(numDone - 1).isDone) {
                throw new GrapieExceptions(ErrorMsg.taskNumIsAlreadyDone(numDone));
            } else {
                storingList.get(numDone - 1).isDone = true;

                String printStr =
                        "Nice! I've marked this task as done: \n" + storingList.get(numDone - 1);
                storage.replaceLineFromHardDisk(numDone);
                return printStr;
            }
        } else {
            throw new GrapieExceptions(ErrorMsg.numberDoNotExistError(numDone));
        }


    }

    /**
     * Delete task the user specifies.
     * Help check if the task number exists.
     *
     * @param inputStr User's input, delete command and task to be deleted.
     * @throws GrapieExceptions Error if the task number to be deleted do not exist.
     * @throws IOException      Throws away the exception.
     */
    public String deleteTask(String inputStr) throws GrapieExceptions, IOException {
        String strNumberDeleted = inputStr.substring(7, inputStr.length());
        strNumberDeleted.replaceAll("\\s+", ""); //remove all white spaces

        int numToDelete = Integer.parseInt(strNumberDeleted);

        if (storingList.size() >= numToDelete) {
            int newSize = storingList.size() - 1;
            assert (newSize >= 0) ;

            String toPrint = " Alrighty. I've removed this task: \n"
                    + storingList.get(numToDelete - 1)
                    + "\n Now you have " + newSize + " tasks in the list.";

            storingList.remove(numToDelete - 1);
            storage.deleteLineFromHardDisk(numToDelete);
            return toPrint;
        } else {
            throw new GrapieExceptions(ErrorMsg.numberDoNotExistError(numToDelete));

        }


    }

    /**
     * Returns the list of tasks having the user input.
     *
     * @param command The user input.
     * @throws GrapieExceptions Throws grapieExceptions.
     */
    public String findFromList(String command) throws GrapieExceptions {
        //remember to add check for already completed task
        String keyword = command.substring(5, command.length());

        int counter = 1;
        String finalStr = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < storingList.size(); i++) {
            Task task = storingList.get(i);

            if (task.description.contains(keyword)) {
                finalStr += counter + "." + task + "\n";
                counter++;
            }
        }
        return finalStr;

    }

    /**
     * List the tasks in task list.
     */
    public String listTheList() {
        int sizeOfList = storingList.size();
        String stringList = "Here are the tasks in your list: \n";

        for (int i = 1; i <= sizeOfList; i++) {
            stringList = stringList + "" + i + ". " + storingList.get(i - 1) + "\n"; //add tasks
        }

        return stringList;
    }
}
