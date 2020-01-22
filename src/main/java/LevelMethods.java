import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        String intro = "Hello! I'm Grapie\n" +
                "What can I do for you?\n";

        formattingDivider(intro);
    }

    public void printAddingTask(Task task) {
        String printStr = "Got it. I've added this task: \n"
                + task + "\n"
                + "Now you have " + storingList.size() + " tasks in the list.";

        formattingDivider(printStr);
    }


    /**
     * Grapie's replies
     *
     * @param inputStr
     */
    public void echo(String inputStr) throws DukeExceptions {

        if (inputStr.contains("todo")) {
            if (inputStr.substring(0, 4).equals("todo") && inputStr.length() > 5) {
                String detailsStr = inputStr.substring(5, inputStr.length());

                Task todo = new Todo(detailsStr);
                storingList.add(todo);

                printAddingTask(todo);
            } else {
                //formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");

                throw new DukeExceptions("OOPS!!! The description of a todo cannot be empty.");
            }

        } else if (inputStr.contains("event")) {
            if (inputStr.substring(0, 5).equals("event") && inputStr.length() > 6) {
                String[] eventAndTime = inputStr.substring(6, inputStr.length()).split(" /at ");

                if (eventAndTime.length <= 1) {
                    //not able to split string properly
                    throw new DukeExceptions("OOPS!!! Event is not created in correct format. Please use: event your_event /at your_time");
                } else {
                    Task event = new Event(eventAndTime[0], eventAndTime[1]);
                    storingList.add(event);

                    //printing
                    printAddingTask(event);
                }

            } else {
                //formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");
                System.out.println("here");
                throw new DukeExceptions("OOPS!!! The description of a event cannot be empty.");
            }

        } else if (inputStr.contains("deadline")) {
            if (inputStr.substring(0, 8).equals("deadline") && inputStr.length() > 9) {

                String[] eventAndTime = inputStr.substring(9, inputStr.length()).split(" /by ");

                if (eventAndTime.length > 1) {

                    Task deadline = new Deadline(eventAndTime[0], eventAndTime[1]);
                    storingList.add(deadline);

                    //print
                    printAddingTask(deadline);
                } else {
                    //System.out.println("hie");
                    //throw new DukeExceptions("OOPS!! Please format your Deadline correctly!!!");
                    throw new DukeExceptions("OOPS!!! Deadline is not created in correct format. Please use: deadline your_deadline /by your_time");
                }

            } else {
                //formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");

                throw new DukeExceptions("OOPS!!! The description of a deadline cannot be empty.");
            }
        } else {
            //correctFormat = false;
            //formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");

            throw new DukeExceptions("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

//        Task task = new Task(inputStr);
//        storingList.add(task);

    }


    public void completeTask(int taskNum) throws DukeExceptions {
        if (storingList.size() >= taskNum && taskNum != 0) {

            storingList.get(taskNum - 1).isDone = true;
            //storingList.set(taskNum - 1, updatedTask);

            String printStr = "Nice! I've marked this task as done: \n" + storingList.get(taskNum - 1);
            formattingDivider(printStr);


        } else {
            //formattingDivider("There is no task " + taskNum + "!!!");
            throw new DukeExceptions("OOPS!!! There is no task " + taskNum + "!!! Please create task " + taskNum + "first.");
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
