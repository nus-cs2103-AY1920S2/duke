/**
 * The user interface which contains outputs to be returned to the user.
 */

public class Ui {

    public String emptyList() {
        return "There is nothing in your list now";
    }

    /**
     * Creates and sends a welcome message to the user.
     * @return a welcome 'Hi' message to the user.
     */
    public static String welcomeMessage() {
        return "\n" + ".---.  .---..-./`)  \n"
                + "|   |  |_ _|\\ .-.') \n"
                + "|   |  ( ' )/ `-' \\ \n"
                + "|   '-(_{;}_)`-'`\"` \n"
                + "|      (_,_) .---.  \n"
                + "| _ _--.   | |   |  \n"
                + "|( ' ) |   | |   |  \n"
                + "(_{;}_)|   | |   |  \n"
                + "'(_,_) '---' '---'  \n"
                + "                    \n" + "Hello ! I'm Cat Bot\n What u want\n";
    }

    public String lineBarrier() {
        return "---------------------------------------";
    }

    public static String sayBye() {
        return "Your List has been saved, Bye! Hope to see you again soon! Press the exit button!";
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
        return "Please give me an approximate timing /by or /at!";
    }

    public String inputAtCmd() {
        return "Please give me an approximate timing using '/at'!";
    }

    public String emptyCmd() {
        return "Please do not give an empty command :(";
    }

    public String emptyDesc() {
        return "Please do not give an empty description :(";
    }

    public String noMatchingTasks() {
        return "There are no matching tasks :/";
    }

    public String matchingTasks() {
        return "Here are the matching tasks in your list:";
    }

    public String currList() {
        return "Here are the tasks in your list:\n";
    }

    public String deleteAll() {
        return "Your whole list has been cleared!";
    }

    public String error() {
        return "There has been an error! Contact @seanlo_oy for more details!";
    }

    public String dateRequired() {
        return "Follow the format dd/MM/YYYY HHMM if you want the timing to be recorded!\n\n";
    }
}