package helper;

import java.text.SimpleDateFormat;

import task.Task;

import java.util.ArrayList;
import java.util.Date;

public class Command {
    /**
     * @params command - command entered by user like todo,event,deadline....
     * @params taskEntered - task enterd by user like read book, event ....
     */
    private String command;
    private String taskEntered;

    /**
     * Command constructor without task (bye,list).
     *
     * @param command command entered by user like todo,event,deadline....
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Commadn constructor with task entered.
     *
     * @param command
     * @param taskEntered
     */
    public Command(String command, String taskEntered) {
        this.command = command;
        this.taskEntered = taskEntered;
    }

    /**
     * Execute methods to return the desired output to user.
     *
     * @param taskList arrayList to store task created by user
     * @param uiHelper class that helps with interaction of user
     * @return desired output according to the task
     * @throws Exception
     */
    public String execute(ArrayList<Task> taskList, Ui uiHelper) throws Exception {
        String responseString = "";

        switch (command) {
            case "bye":
                responseString = uiHelper.returnExitsMessage();
                return responseString;
            case "list":
                if (taskList.size() == 0) {
                    responseString = "There is no task in your current list. Please add in ";
                    return responseString;
                } else {
                    responseString = uiHelper.printTaskList(taskList);
                    return responseString;
                }
            case "todo":
                taskList.add(new Task(taskEntered));
                String topLine = uiHelper.getTopTwoLine();
                String response = "   " + taskList.get(taskList.size() - 1).toString();
                String customeriseBottomMes = uiHelper.getBottomTwoLine(taskList);
                responseString = uiHelper.parserOutputMess(topLine, response, customeriseBottomMes);
                return responseString;
            case "event":
                String[] temp = taskEntered.split("/");
                String eventName = temp[0];
                String eventDate = formatDate(temp[1].substring(3));
                String eventTopMes = uiHelper.getTopTwoLine(); //  assign user interaction string message
                taskList.add(new task.Deadline(eventName, eventDate)); // add deadline into arrayList of task.Task
                String eventTaskMes = "     " + taskList.get(taskList.size() - 1).toString(); // assign return deadline message
                String eventBotMess = uiHelper.getBottomTwoLine(taskList); // assign user interaction string message
                return uiHelper.parserOutputMess(eventTopMes, eventTaskMes, eventBotMess); // parse
            case "deadline":
                String[] deadlineTemp = taskEntered.split("/"); // split the string to get date and time
                String deadlineName = deadlineTemp[0]; // assign task.Task name
                String date = formatDate(deadlineTemp[1].substring(3)); // formating the date
                String deadlineTopMess = uiHelper.getTopTwoLine(); //  assign user interaction string message
                taskList.add(new task.Event(deadlineName, date));  // add deadline into arrayList of task.Task
                String deadlineMess = "     " + taskList.get(taskList.size() - 1).toString(); // assign return deadline message
                String deadlineBotMess = uiHelper.getBottomTwoLine(taskList); // assign user interaction string message
                return uiHelper.parserOutputMess(deadlineTopMess, deadlineMess, deadlineBotMess); // parse
            case "delete":
                int arrPos = Integer.parseInt(taskEntered);
                assert arrPos > 0 : "Please enter a positive index ";
                if (taskList.size() == 0) {
                    return "  Sorry there is no any task for you to do the command";
                } else if (arrPos > taskList.size()) {
                    return "You have entered the wrong index of task, please try again!";
                } else {
                    String deleteTopMess = uiHelper.removeTaskMes();
                    String deleteMess = "    " + taskList.get(arrPos - 1).toString();
                    taskList.remove(arrPos - 1);
                    String deleteBotMess = uiHelper.getBottomTwoLine(taskList);
                    return uiHelper.parserOutputMess(deleteTopMess, deleteMess, deleteBotMess); // parse
                }
            case "done":
                int doneArrPos = Integer.parseInt(taskEntered); // assign index
                assert doneArrPos > 0 : "Please enter a positive index ";
                if (taskList.size() == 0) { // check if size of arrayList is large then 1
                    return "  Sorry there is no any task for you to do the command";
                } else if (doneArrPos > taskList.size()) {
                    return "You entered wrong index of task, please try again later";
                } else {
                    assert !taskList.get(doneArrPos - 1).getDoneStatus() : "The task status is set to done already";
                    taskList.get(doneArrPos - 1).setDone(); // set Done to the task object
                    String doneMesTop = uiHelper.markAsDone(); // assign done
                    String doneResult = "    [" + taskList.get(doneArrPos - 1).getStatusIcon() + "] " + taskList.get(doneArrPos - 1).getDescription();
                    String doneMesBot = "  -------------";
                    return uiHelper.parserOutputMess(doneMesTop, doneResult, doneMesBot); // parse the message together and return to Duke
                }
            case "find":
                assert taskList.size() > 0 : "Execution command failed because there is task created ";
                ArrayList<String> matchResult = new ArrayList<String>();
                String searchWord = taskEntered;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getDescription().contains(searchWord)) {
                        matchResult.add(taskList.get(i).toString());
                    }
                }
                String topFindMess = uiHelper.findTaskMes();
                String match = "";
                for (int j = 0; j < matchResult.size(); j++) {
                    match = match + " " + (j + 1) + ". " + matchResult.get(j).toString() + "\n";
                }
                return uiHelper.parserOutputMess(topFindMess, match, "------------");
            default:
                return "Sorry i do not know what is means ";
        }
    }

    private static String formatDate(String date) throws Exception {
        assert !date.substring(2, 3).equalsIgnoreCase("-") : "Please Enter date in correct format YYYY-MM-DD";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(date);
        formatter = new SimpleDateFormat("E, MMM d yyyy");
        return (formatter.format(date1));
    }


}
