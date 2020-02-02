import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

/**
 * takes in user's command and
 */
public class Parser {
    private String command;
    private Ui uiDisplay;
    private Storage fileStorage;

    public Parser() {
    }

    public Parser(String command, Ui uiDisplay, Storage fileStorage) {
        this.command = command;
        this.uiDisplay = uiDisplay;
        this.fileStorage = fileStorage;
    }

    /**
     * parse user's command into "todo" , "event" and "deadline"
     *
     * @param command
     * @return command type "todo" , "event" and "deadline"
     */
    public String getCommandType(String command) {
        String[] s;
        s = command.split(" ");
        return s[0];
    }

    /**
     * This method takers in user's input and arrayList of task and Ui class to
     * handle the todo task command and write it into file,
     * it return the desired string output to user
     *
     * @param command   command and task that entered by user
     * @param tasks     arrayList that used to store task created
     * @param uiDisplay class object that deals with the interaction with user.
     * @param f         File object to write task into file.
     * @return output
     * @throws Exception
     */
    public String todoTaskCommand(String command, ArrayList<Task> tasks, Ui uiDisplay, File f) throws Exception {
        String[] s = command.split("todo "); // split the user's input with todo commadn to get the task
        tasks.add(new Task(s[1])); // initialize Task
        String customeriseTopMes = uiDisplay.getTopTwoLine(); // assign user interaction string message
        String taskMes = "   " + tasks.get(tasks.size() - 1).toString(); // the assign return task message
        String customeriseBottomMes = uiDisplay.getBottomTwoLine(tasks); // assign user interaction string message
        fileStorage.writeFile("todo", s[1], f); // create task into file
        return uiDisplay.parserOutputMess(customeriseTopMes, taskMes, customeriseBottomMes); // parse the string and return to Duke class
    }

    /**
     * This method takers in user's input and arrayList of task and Ui class to
     * handle event task command and write it into file
     * it return the desired string output to user
     *
     * @param command   command and task that entered by user
     * @param tasks     arrayList that used to store task created
     * @param uiDisplay class object that deals with the interaction with user.
     * @param f         File object to write task into file.
     * @return output
     * @throws Exception
     */
    public String eventCommand(String command, ArrayList<Task> tasks, Ui uiDisplay, File f) throws Exception {
        String[] s = command.split("event "); // split the user's input with event command to get the task
        String[] temp = s[1].split("/"); // split the string to get date and time
        String taskName = temp[0]; // assign Task name
        String date = formatDate(temp[1].substring(3, temp[1].length())); // formating the date
        String customeriseTopMes = uiDisplay.getTopTwoLine(); //  assign user interaction string message
        tasks.add(new Deadline(taskName, date)); // add deadline into arrayList of Task
        String taskMes = "     " + tasks.get(tasks.size() - 1).toString(); // assign return deadline message
        String customeriseBottomMes = uiDisplay.getBottomTwoLine(tasks); // assign user interaction string message
        fileStorage.writeFile("deadline", taskName + date, f); // create task into file
        return uiDisplay.parserOutputMess(customeriseTopMes, taskMes, customeriseBottomMes); // parse the string and return to Duke class
    }

    /**
     * This method takers in user's input and arrayList of task and Ui class to
     * handle deadline task command and write it into file
     * and return the desired string output t user
     *
     * @param command   command and task that entered by user
     * @param tasks     arrayList that used to store task created
     * @param uiDisplay class object that deals with the interaction with user.
     * @param f         File object to write task into file.
     * @return output
     * @throws Exception
     */
    public String deadlineCommand(String command, ArrayList<Task> tasks, Ui uiDisplay, File f) throws Exception {
        String[] s = command.split("deadline "); // split the user's input with event command to get the task
        String[] temp = s[1].split("/"); // split the string to get date and time
        String taskName = temp[0]; // assign Task name
        String date = formatDate(temp[1].substring(3, temp[1].length())); // formating the date
        String customeriseTopMes = uiDisplay.getTopTwoLine(); //  assign user interaction string message
        tasks.add(new Event(taskName, date));  // add deadline into arrayList of Task
        String taskMes = "     " + tasks.get(tasks.size() - 1).toString(); // assign return deadline message
        String customeriseBottomMes = uiDisplay.getBottomTwoLine(tasks); // assign user interaction string message
        fileStorage.writeFile("deadline", taskName + date, f);  // create task into file
        return uiDisplay.parserOutputMess(customeriseTopMes, taskMes, customeriseBottomMes); // parse the string and return to Duke class
    }

    /**
     * This method takers in user's input and arrayList of task and Ui class to handle done command
     * and return the desired string output to user
     *
     * @param command   command and task that entered by user
     * @param tasks     arrayList that used to store task created
     * @param uiDisplay class object that deals with the interaction with user.
     * @return output
     * @throws Exception
     */
    public String doneCommand(String command, ArrayList<Task> tasks, Ui uiDisplay) {
        if (tasks.size() > 0) { // check if size of arrayList is large then 1
            String[] temp = command.split(" "); // split the user command to get index
            int arrPos = Integer.parseInt(temp[1]); // assign index
            tasks.get(arrPos - 1).setDone(); // set Done to the task object
            String doneMesTop = uiDisplay.markAsDone(); // assign done message
            String doneResult = "    [" + tasks.get(arrPos - 1).getStatusIcon() + "] " + tasks.get(arrPos - 1).getDescription();
            String doneMesBot = "  -------------";
            return uiDisplay.parserOutputMess(doneMesTop, doneResult, doneMesBot); // parse the message together and return to Duke
        } else {
            return "  Sorry there is no any task for you to do the command";
        }
    }

    /**
     * This method handle delete task command
     * and return the desired string output to user
     *
     * @param command   command and task that entered by user
     * @param tasks     arrayList that used to store task created
     * @param uiDisplay class object that deals with the interaction with user.
     * @return
     * @throws Exception
     */
    public void deleteCommand(String command, ArrayList<Task> tasks, Ui uiDisplay) {
        String[] temp = command.split(" "); // split the user's command to get index
        int arrPos = Integer.parseInt(temp[1]); // assign index to params
        uiDisplay.removeTaskMes(); // print the ui message
        System.out.println("    " + tasks.get(arrPos - 1).toString());
        tasks.remove(arrPos - 1); // remove the task
        uiDisplay.getBottomTwoLine(tasks); // print out the ui message
    }

    /**
     * This method helps to format date and time format that entered by user
     *
     * @param date
     * @return Date in format Tue 12, 2019
     * @throws Exception
     */
    public static String formatDate(String date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // parse the formater
        Date date1 = formatter.parse(date); // assign input (date) into formater
        formatter = new SimpleDateFormat("E, MMM d yyyy"); // convert the format
        return (formatter.format(date1)); // format the date
    }
}


