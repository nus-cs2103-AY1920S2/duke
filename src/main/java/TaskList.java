import java.io.IOException;
import java.util.List;

public class TaskList {
    private List<Task> storingList;
    Storage storage;

    public TaskList(List<Task> load, Storage storage) {
        //contains the task list e.g., it has operations to add/delete tasks in the list
        storingList = load;
        this.storage = storage;
    }

    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        String lines[] = contentStr.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.println("      " + lines[i]);
        }

        //System.out.println(contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    public void printAddingTask(Task task) {
        String printStr = "Alrighty. I've added this task: \n"
                + task + "\n"
                + "Now you have " + storingList.size() + " tasks in the list.";

        formattingDivider(printStr);
    }

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
                    storage.convertToHardDiskFormatAndStore(event, "T", event.time);

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
                    storage.convertToHardDiskFormatAndStore(deadline, "T", deadline.time);
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

    public boolean isNumber(String numStr) {
        try {
            Integer.parseInt(numStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


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

    public void deleteTask(String inputStr) throws GrapieExceptions, IOException {
        if (inputStr.length() <= 7) {
            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
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

    public void listTheList() {
        int sizeOfList = storingList.size();
        String stringList = "Here are the tasks in your list: \n";

        for (int i = 1; i <= sizeOfList; i++) {
            stringList = stringList + "" + i + ". " + storingList.get(i - 1) + "\n"; //add tasks
        }

        formattingDivider(stringList);
    }

}
