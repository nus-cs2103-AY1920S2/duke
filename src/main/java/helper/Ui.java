package helper;

import exception.IncorrectInputException;
import task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }

    /**
     * print out the welcome message .
     *
     * @return message to user
     */
    public String welcomeMessage() {
        return "  Welcome to Duke Program";
    }

    /**
     * return the todo task's interaction string with user.
     *
     * @return message of todo task.
     */
    public String getTopTwoLine() {
        return "----------------- " + '\n' + "   Got it. I've added this task:";
    }

    /**
     * print the task size to user.
     *
     * @param tList arrayList of task .
     * @return size of tlist to user.
     */
    public String getBottomTwoLine(ArrayList<Task> tList) {
        return "   Now you have " + tList.size() + " tasks in the list.\n"
                + "-----------------  ";
    }

    /**
     * return exit message to user
     *
     * @return string exit message to user
     */
    public String returnExitsMessage() {
        return "  --------------\n"
                + "    Bye. Hope to see you again\n"
                + "  --------------";
    }

    /**
     * update the status of task to done!
     *
     * @return message of task completed
     */
    public String markAsDone() {
        return "  --------------\n"
                + "    Nice! I've marked this task as done: ";
    }

    /**
     * customize the remove message to user.
     *
     * @return remove task message to user.
     */
    public String removeTaskMes() {
        return "  --------------\n"
                + "   Got it. I've removed this task:";
    }

    /**
     * combine all the message together
     *
     * @param topMessage
     * @param output
     * @param bottomMessage
     * @return desired output to user
     */
    public String parserOutputMess(String topMessage, String output, String bottomMessage) {
        return topMessage + "\n" + output + "\n" + bottomMessage;
    }

    /**
     * customize the message of find command.
     *
     * @return customize string of find task
     */
    public String findTaskMes() {
        return "  --------------\n"
                + "    Here are the matching tasks in your list: ";
    }

    /**
     * check is task's description is empty or not
     *
     * @param input
     * @throws IncorrectInputException if the task's description is empty
     */
    public void checkIsTaskEmpty(String input) throws IncorrectInputException {
        String[] commandArray = input.split(" ");
        if (commandArray.length == 1) {
            throw new IncorrectInputException("☹ OOPS!!! The description of a " + commandArray[0] + " cannot be empty.");
        }
    }

    /**
     * combine all the task in arrayList
     *
     * @param tList
     * @return all task in arrayList
     */
    public String printTaskList(ArrayList<Task> tList) {
        String outputString = "";

        for (int i = 0; i < tList.size(); i++) {
            String temp = ((i + 1) + ". " + tList.get(i).toString()) + "\n";
            outputString += temp;
        }
        return outputString;
    }

}
