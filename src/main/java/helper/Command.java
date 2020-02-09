package helper;

import java.text.SimpleDateFormat;

import task.Task;

import java.util.ArrayList;
import java.util.Date;

public class Command {
    private String command;
    private String taskEntered;
    private Storage fileStorage;


    public Command(String command) {
        this.command = command;
    }

    public Command(String command, String taskEntered) {
        this.command = command;
        this.taskEntered = taskEntered;
    }


    public String execute(ArrayList<Task> taskList, Ui uiHelper) throws Exception {
        String responseString = "";

        if (command.equalsIgnoreCase("bye")) {
            responseString = uiHelper.returnExitsMessage();
            return responseString;
        } else if (command.equals("list")) {
            if (taskList.size() == 0) {
                responseString = "There is no task in your current list. Please add in ";
                return responseString;
            } else {
                responseString = uiHelper.printTaskList(taskList);
                return responseString;
            }
        } else if (command.equals("todo")) {
            taskList.add(new Task(taskEntered));
            String topLine = uiHelper.getTopTwoLine();
            String response = "   " + taskList.get(taskList.size() - 1).toString();
            String customeriseBottomMes = uiHelper.getBottomTwoLine(taskList);
            responseString = uiHelper.parserOutputMess(topLine, response, customeriseBottomMes);
            return responseString;
        } else if (command.equals("event")) {
            String[] temp = taskEntered.split("/");
            String taskName = temp[0];
            String date = formatDate(temp[1].substring(3, temp[1].length()));
            String customeriseTopMes = uiHelper.getTopTwoLine(); //  assign user interaction string message
            taskList.add(new task.Deadline(taskName, date)); // add deadline into arrayList of task.Task
            String taskMes = "     " + taskList.get(taskList.size() - 1).toString(); // assign return deadline message
            String customeriseBottomMes = uiHelper.getBottomTwoLine(taskList); // assign user interaction string message
            return uiHelper.parserOutputMess(customeriseTopMes, taskMes, customeriseBottomMes); // parse
        } else if (command.equals("deadline")) {
            String[] temp = taskEntered.split("/"); // split the string to get date and time
            String taskName = temp[0]; // assign task.Task name
            String date = formatDate(temp[1].substring(3, temp[1].length())); // formating the date
            String customeriseTopMes = uiHelper.getTopTwoLine(); //  assign user interaction string message
            taskList.add(new task.Event(taskName, date));  // add deadline into arrayList of task.Task
            String taskMes = "     " + taskList.get(taskList.size() - 1).toString(); // assign return deadline message
            String customeriseBottomMes = uiHelper.getBottomTwoLine(taskList); // assign user interaction string message
            return uiHelper.parserOutputMess(customeriseTopMes, taskMes, customeriseBottomMes); // parse
        } else if (command.equals("delete")) {
            int arrPos = Integer.parseInt(taskEntered);
            assert arrPos > 0 : "Please enter a positive index ";
            if (taskList.size() == 0) {
                return "  Sorry there is no any task for you to do the command";
            } else if (arrPos > taskList.size()) {
                return "You have entered the wrong index of task, please try again!";
            } else {
                String customeriseTopMes = uiHelper.removeTaskMes();
                String taskMes = "    " + taskList.get(arrPos - 1).toString();
                taskList.remove(arrPos - 1);
                String customeriseBottomMes = uiHelper.getBottomTwoLine(taskList);
                return uiHelper.parserOutputMess(customeriseTopMes, taskMes, customeriseBottomMes); // parse
            }

        } else if (command.equals("done")) {
            int arrPos = Integer.parseInt(taskEntered); // assign index
            assert arrPos > 0 : "Please enter a positive index ";
            if (taskList.size() == 0) { // check if size of arrayList is large then 1
                return "  Sorry there is no any task for you to do the command";
            } else if (arrPos > taskList.size()) {
                return "You entered wrong index of task, please try again later";
            } else {
                assert !taskList.get(arrPos - 1).getDoneStatus() : "The task status is set to done already";
                taskList.get(arrPos - 1).setDone(); // set Done to the task object
                String doneMesTop = uiHelper.markAsDone(); // assign done
                String doneResult = "    [" + taskList.get(arrPos - 1).getStatusIcon() + "] " + taskList.get(arrPos - 1).getDescription();
                String doneMesBot = "  -------------";
                return uiHelper.parserOutputMess(doneMesTop, doneResult, doneMesBot); // parse the message together and return to Duke
            }
        } else if (command.equals("find")) {
            assert taskList.size() > 0 : "Execution command failed because there is task created " ;
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
        } else {
            return "Sorry i do not know what is means ";
        }
    }

    private static String formatDate(String date) throws Exception {
        assert !date.substring(2,3).equalsIgnoreCase("-") : "Please Enter date in correct format YYYY-MM-DD";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(date);
        formatter = new SimpleDateFormat("E, MMM d yyyy");
        return (formatter.format(date1));
    }


}
