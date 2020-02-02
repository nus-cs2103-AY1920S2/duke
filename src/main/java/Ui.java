import java.util.ArrayList;

public class Ui {
    public Ui() {
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

    public void removeTaskMes() {
        System.out.println("  --------------\n" +
                "   Got it. I've removed this task:");
    }

    public String parserOutputMess(String topMessage, String output, String bottomMessage) {
        return topMessage + "\n" + output + "\n" + bottomMessage;
    }

    public String findTaskMes(){
        return "  --------------\n" +
                "    Here are the matching tasks in your list: ";
    }

}
