import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LevelMethods {
    List<Task> storingList = new ArrayList<>();
    String ha;

    public LevelMethods() {
        //no stuff
    }

    /**
     * Display format
     *
     * @param contentStr
     */
    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        String lines[] = contentStr.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.println("      " + lines[i]);
        }

        //System.out.println(contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    /**
     * Greet the user
     */
    public void greetings() {
        String intro = "Hello! I'm Grapie! \n"
                + "   _____                 _      \n"
                + "  / ____|               (_)     \n"
                + " | |  __ _ __ __ _ _ __  _  ___ \n"
                + " | | |_ | '__/ _` | '_ \\| |/ _ \\ \n"
                + " | |__| | | | (_| | |_) | |  __/ \n"
                + "  \\_____|_|  \\__,_| .__/|_|\\___| \n"
                + "                  | |           \n"
                + "                  |_|           \n"


                + "What do ya need from me?\n";

        formattingDivider(intro);
    }

    public void printAddingTask(Task task) {
        String printStr = "Got it. I've added this task: \n"
                + task + "\n"
                + "Now you have " + storingList.size() + " tasks in the list.";

        formattingDivider(printStr);
    }

    public void time() {
        /*
        Minimal: Store deadline dates as a java.time.LocalDate in your task objects.
        Accept dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15)
        and print in a different format such as MMM d yyyy e.g., (Oct 15 2019).
         */



    }


    /**
     * Grapie's replies
     *
     * @param inputStr
     */
    public void echo(String inputStr) throws GrapieExceptions {

        if (inputStr.contains("todo")) {
            if (inputStr.substring(0, 4).equals("todo") && inputStr.length() > 5) {
                String detailsStr = inputStr.substring(5, inputStr.length());

                String checkIfTodoIsEmpty = detailsStr.replaceAll("\\s","");

                if (checkIfTodoIsEmpty.length() == 0) {
                    //That means it is empty behing todo
                    throw new GrapieExceptions("OOPS!!! The description of a todo cannot be empty.");
                } else {

                    Task todo = new Todo(detailsStr);
                    storingList.add(todo);

                    printAddingTask(todo);
                }
            } else {
                throw new GrapieExceptions("OOPS!!! The description of a todo cannot be empty.");
            }

        } else if (inputStr.contains("event")) {
            if (inputStr.substring(0, 5).equals("event") && inputStr.length() > 6) {
                String[] eventAndTime = inputStr.substring(6, inputStr.length()).split(" /at ");

                if (eventAndTime.length <= 1) {
                    //not able to split string properly
                    throw new GrapieExceptions("OOPS!!! Event in wrong format. Please use: event your_event /at YYYY-MM-DD TTTT");
                } else {
//                    LocalDate localDate = LocalDate.parse(eventAndTime[1]);
                    Event event = new Event(eventAndTime[0], eventAndTime[1]);
                    storingList.add(event);

                    //printing
                    printAddingTask(event);
                }

            } else {
                throw new GrapieExceptions("OOPS!!! The description of a event cannot be empty.");
            }

        } else if (inputStr.contains("deadline")) {
            if (inputStr.substring(0, 8).equals("deadline") && inputStr.length() > 9) {

                String[] eventAndTime = inputStr.substring(9, inputStr.length()).split(" /by ");

                if (eventAndTime.length > 1) {
                    //LocalDate localDate = LocalDate.parse(eventAndTime[1]);
                    //String haha = "2019-12-01";
//                    LocalDate localDate = LocalDate.parse(eventAndTime[1]);
                    Deadline deadline = new Deadline(eventAndTime[0], eventAndTime[1]);
                    storingList.add(deadline);

                    //print
                    printAddingTask(deadline);
                } else {
                    throw new GrapieExceptions("OOPS!!! Deadline in wrong format. Please use: deadline your_deadline /by YYYY-MM-DD TTTT");
                }

            } else {
                throw new GrapieExceptions("OOPS!!! The description of a deadline cannot be empty.");
            }
        } else {
            throw new GrapieExceptions("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

//        Task task = new Task(inputStr);
//        storingList.add(task);

    }

    public static boolean isNumber(String numStr) {
        try {
            Integer.parseInt(numStr);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void completeTask(String doneTaskStr) throws GrapieExceptions {
        if (doneTaskStr.length() <= 5) {
            //no number behind
            throw new GrapieExceptions("Please input a valid number behind 'done'!!");
        } else {

            String strNumberDone = doneTaskStr.substring(5, doneTaskStr.length());
            strNumberDone.replaceAll("\\s+",""); //remove all white spaces

            boolean isANumber = isNumber(strNumberDone);

            if (isANumber) {
                int numDone = Integer.parseInt(strNumberDone); //convert to number

                if (storingList.size() >= numDone && numDone != 0) {

                    storingList.get(numDone - 1).isDone = true;
                    //storingList.set(taskNum - 1, updatedTask);

                    String printStr = "Nice! I've marked this task as done: \n" + storingList.get(numDone - 1);
                    formattingDivider(printStr);

                } else {
                    //formattingDivider("There is no task " + taskNum + "!!!");
                    throw new GrapieExceptions("OOPS!!! There is no task " + numDone + "!!! Please create task " + numDone + " first.");
                }
            } else {
                throw new GrapieExceptions("Please input a valid number behind 'done'!!");
            }
        }

    }

    public void deleteTask(String inputStr) throws GrapieExceptions {
        if (inputStr.length() <= 7) {
            throw new GrapieExceptions("Please input a valid number behind 'delete'!!");
        } else {
            String strNumberDeleted = inputStr.substring(7, inputStr.length());
            strNumberDeleted.replaceAll("\\s+",""); //remove all white spaces

            boolean isANumber = isNumber(strNumberDeleted);

            if (isANumber) {
                int numToDelete = Integer.parseInt(strNumberDeleted);

                if (storingList.size() >= numToDelete) {

                    int newSize = storingList.size() - 1;
                    String toPrint = " Noted. I've removed this task: \n"
                            + storingList.get(numToDelete-1)
                            + "\n Now you have " +  newSize + " tasks in the list." ;

                    storingList.remove(numToDelete - 1);

                    formattingDivider(toPrint);

                } else {
                    throw new GrapieExceptions("No number " + numToDelete + " in task list!!!");
                }
            } else {
                throw new GrapieExceptions("Please input a valid number behind 'delete'!!");
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

    public void sayonara() {
        formattingDivider("Okie!! Goodbye!");
    }

}
