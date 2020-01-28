/**
 * The user interface which contains outputs to be returned to the user.
 */

public class Ui {

    public String emptyList() {
        return "There is nothing in your list now";
    }

    public String welcomeMessage() {
        return "Hello ! I'm Ashley Bot\nOi What u want\n";
    }

    public String lineBarrier() {
        return "--------------------------------------------------------------";
    }

    public String sayBye() {
        return "Your List has been saved, Bye! Hope to see you again soon!";
    }

    public String unknownCommand() {
        return "OOPs Idk what that means :/\n Try 'todo', 'event' or 'deadline' commands instead!";
    }

    public String unfinishedTask() {
        return "I have removed an unfinished task:\n";
    }

    public String finishedTask() {
        return "I have removed a finished task:\n";
    }

    public String nosuchNumber() {
        return "There is no such number on your List! :/";
    }

    public String inputNumber() {
        return "Please input a number instead!";
    }

    public String oneCommand() {
        return "Please enter the command only once!";
    }

    public String inputByCmd() {
        return "Please give me an approximate timing using '/by'!";
    }

    public String inputAtCmd() {
        return "Please give me an approximate timing using '/at'!";
    }

    public String emptyCmd() {
        return "Please do not give an empty command :(";
    }

    public String noMatchingTasks() {
        return "There are no matching tasks :/";
    }

    public String matchingTasks() {
        return "Here are the matching tasks in your list:";
    }

    public String deleteAll() {
        return "Your whole list has been cleared!";
    }
}