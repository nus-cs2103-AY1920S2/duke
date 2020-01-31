import java.io.IOException;
import java.util.List;

public class TaskList {
    private List<Task> storingList;
    Storage storage;

    /**
     * Constructor for TaskList.
     *
     * @param load the arrayList obtained from Storage class's load method. Contains task from hard disk.
     * @param storage Storage class previously created from Duke.
     */
    public TaskList(List<Task> load, Storage storage) {
        //contains the task list e.g., it has operations to add/delete tasks in the list
        storingList = load;
        this.storage = storage;
    }

    /**
     * Format the output.
     *
     * @param contentStr The string to be formatted.
     */
    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        String[] lines = contentStr.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.println("      " + lines[i]);
        }

        //System.out.println(contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    /**
     * Format for printing the task added.
     *
     * @param task The task to be formatted.
     */
    public void printAddingTask(Task task) {
        String printStr = "Alrighty. I've added this task: \n"
                + task + "\n"
                + "Now you have " + storingList.size() + " tasks in the list.";

        formattingDivider(printStr);
    }

    /**
     * Check for todo, event, deadline and add to list correspondingly.
     * Also checks for invalid input.
     *
     * @param inputStr the user input.
     * @throws GrapieExceptions Errors thrown.
     * @throws IOException Throws away the exception.
     */
    public void addToList(String inputStr) throws GrapieExceptions, IOException {
        if (inputStr.contains("todo")) {
            if (inputStr.substring(0, 4).equals("todo") && inputStr.length() > 5) {
                String detailsStr = inputStr.substring(5, inputStr.length());
                String checkIfTodoIsEmpty = detailsStr.replaceAll("\\s", "");

                if (checkIfTodoIsEmpty.length() == 0) {
                    //That means it is empty behing todo
                    throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
                } else {
                    Task todo = new Todo(detailsStr);
                    storingList.add(todo);
                    printAddingTask(todo);

                    //store into hard disk
                    storage.convertToHardDiskFormatAndStore(todo, "T", "");
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }

        } else if (inputStr.contains("event")) {
            if (inputStr.substring(0, 5).equals("event") && inputStr.length() > 6) {
                String[] eventAndTime = inputStr.substring(6, inputStr.length()).split(" /at ");

                if (eventAndTime.length <= 1) {
                    //not able to split string properly
                    throw new GrapieExceptions(ErrorMsg.eventFormatError);
                } else {
                    Event event = new Event(eventAndTime[0], eventAndTime[1]);
                    storingList.add(event);

                    //printing
                    printAddingTask(event);

                    //store into hard disk
                    storage.convertToHardDiskFormatAndStore(event, "E", eventAndTime[1]);
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }

        } else if (inputStr.contains("deadline")) {
            if (inputStr.substring(0, 8).equals("deadline") && inputStr.length() > 9) {

                String[] eventAndTime = inputStr.substring(9, inputStr.length()).split(" /by ");

                if (eventAndTime.length > 1) {
                    Deadline deadline = new Deadline(eventAndTime[0], eventAndTime[1]);
                    storingList.add(deadline);

                    //print
                    printAddingTask(deadline);

                    //store into hard disk
                    storage.convertToHardDiskFormatAndStore(deadline, "D", eventAndTime[1]);
                } else {
                    //"OOPS!!! Deadline in wrong format. Please use: deadline your_deadline /by YYYY-MM-DD TTTT"
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
     * Check if a valid number is inputted in String form.
     *
     * @param numStr the string to be checked.
     * @return Boolean stating if the String is a valid number.
     */
    public boolean isNumber(String numStr) {
        try {
            Integer.parseInt(numStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Help mark a task as completed (O).
     * Also checks if its a valid task number or if the task is already completed.
     *
     * @param doneTaskStr Task the user wants to be marked as complete.
     * @throws GrapieExceptions Invalid task number, or already completed task thrown as error.
     * @throws IOException Throws away the exception.
     */
    public void completeTask(String doneTaskStr) throws GrapieExceptions, IOException {
        if (doneTaskStr.length() <= 5) {
            //no number behind
            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
        } else {
            //remember to add check for already completed task
            String strNumberDone = doneTaskStr.substring(5, doneTaskStr.length());
            strNumberDone.replaceAll("\\s+", ""); //remove all white spaces

            boolean isANumber = isNumber(strNumberDone);

            if (isANumber) {
                int numDone = Integer.parseInt(strNumberDone); //convert to number
                if (storingList.size() >= numDone && numDone != 0) {
                    if (storingList.get(numDone - 1).isDone) {
                        //if already true
                        throw new GrapieExceptions(ErrorMsg.taskNumIsAlreadyDone(numDone));
                    } else {

                        storingList.get(numDone - 1).isDone = true;
                        //storingList.set(taskNum - 1, updatedTask);

                        String printStr = "Nice! I've marked this task as done: \n" + storingList.get(numDone - 1);
                        formattingDivider(printStr);

                        //update hard disk :(
                        storage.replaceLineFromHardDisk(numDone);
                    }

                } else {
                    throw new GrapieExceptions(ErrorMsg.numberDoNotExistError(numDone));
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.invalidNumberError);
            }
        }
    }

    /**
     * Delete task the user specifies.
     * Help check if the task number exists.
     *
     * @param inputStr User's input, delete command and task to be deleted.
     * @throws GrapieExceptions Error if the task number to be deleted do not exist.
     * @throws IOException Throws away the exception.
     */
    public void deleteTask(String inputStr) throws GrapieExceptions, IOException {
        if (inputStr.length() <= 7) {
            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
        } else {
            if (!inputStr.substring(6,7).equals(" ")) {
                throw new GrapieExceptions(ErrorMsg.noSpaceError);
            } else {
                String strNumberDeleted = inputStr.substring(7, inputStr.length());
                strNumberDeleted.replaceAll("\\s+", ""); //remove all white spaces

                boolean isANumber = isNumber(strNumberDeleted);

                if (isANumber) {
                    int numToDelete = Integer.parseInt(strNumberDeleted);

                    if (storingList.size() >= numToDelete) {

                        int newSize = storingList.size() - 1;
                        String toPrint = " Alrighty. I've removed this task: \n"
                                + storingList.get(numToDelete - 1)
                                + "\n Now you have " + newSize + " tasks in the list.";

                        storingList.remove(numToDelete - 1);

                        formattingDivider(toPrint);
                        //delete from hard disk
                        storage.deleteLineFromHardDisk(numToDelete);

                    } else {
                        throw new GrapieExceptions(ErrorMsg.numberDoNotExistError(numToDelete));
                    }
                } else {
                    throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                }
            }
        }
    }

    /**
     * Returns the list of tasks having the user input.
     *
     * @param command The user input.
     * @throws GrapieExceptions Throws grapieExceptions.
     */
    public void findFromList(String command) throws GrapieExceptions {
        if (command.length() <= 5) {
            throw new GrapieExceptions(ErrorMsg.emptyKeywordError);
        } else {
            //remember to add check for already completed task
            String keyword = command.substring(5, command.length());

            int counter = 1;
            System.out.println("    #__________________________________________________________# \n");
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < storingList.size(); i++) {
                Task task = storingList.get(i);

                if (task.description.contains(keyword)) {
                    System.out.println("      " + counter + "." + task);
                    counter++;
                }
            }
            System.out.println("    #__________________________________________________________# \n");
        }
    }

    /**
     * List the tasks in task list.
     */
    public void listTheList() {
        int sizeOfList = storingList.size();
        String stringList = "Here are the tasks in your list: \n";

        for (int i = 1; i <= sizeOfList; i++) {
            stringList = stringList + "" + i + ". " + storingList.get(i - 1) + "\n"; //add tasks
        }

        formattingDivider(stringList);
    }

}
