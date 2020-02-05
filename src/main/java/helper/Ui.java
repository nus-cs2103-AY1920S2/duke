package helper;

import exception.IncorrectInputException;
import task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }

    public String welcomeMessage(){
        return "  Welcome to Duke Program";
    }

    public String getTopTwoLine() {
        return "----------------- " + '\n' + "   Got it. I've added this task:";
    }

    public String getBottomTwoLine(ArrayList<Task> tList) {
        return "   Now you have " + tList.size() + " tasks in the list.\n" +
                "-----------------  ";
    }

    public String returnExitsMessage() {
        return "  --------------\n" +
                "    Bye. Hope to see you again\n" +
                "  --------------";
    }

    public String markAsDone() {
        return "  --------------\n" +
                "    Nice! I've marked this task as done: ";
    }

    public String removeTaskMes() {
        return "  --------------\n" +
                "   Got it. I've removed this task:";
    }

    public String parserOutputMess(String topMessage, String output, String bottomMessage) {
        return topMessage + "\n" + output + "\n" + bottomMessage;
    }

    public String findTaskMes() {
        return "  --------------\n" +
                "    Here are the matching tasks in your list: ";
    }
    public void checkIsTaskEmpty(String input) throws IncorrectInputException {
        String [] commandArray = input.split(" ");
        if(commandArray.length == 1 ){
            throw new IncorrectInputException("☹ OOPS!!! The description of a "+ commandArray[0] +" cannot be empty.");
        }
    }

    public void printError(String message){
        System.out.println("-----------------");
        System.out.println("   "+message);
        System.out.println("-----------------");
    }

    public String printTaskList(ArrayList<Task> tList){
        String outputString = "";
        for (int i = 0; i < tList.size(); i++) {
            String temp = ((i + 1) + ". " + tList.get(i).toString())+"\n";
            outputString += temp;
        }
        return outputString;
    }

}
