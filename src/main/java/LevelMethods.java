import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelMethods {
    List<Task> storingList = new ArrayList<>();

    public LevelMethods() {

    }

    /**
     * Display format
     * @param contentStr
     */
    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        System.out.println(contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    /**
     * Greet the user
     */
    public void greetings() {
        String intro = "    Hello! I'm Grapie\n" +
                "    What can I do for you?\n";

        formattingDivider(intro);

    }


    /**
     * Level 1 task - echo
     * @param inputStr
     */
    public void echo (String inputStr) {
        formattingDivider("    added: " + inputStr);
        Task task = new Task(inputStr);

        storingList.add(task);
    }


    public void completeTask(int taskNum) {
        if (storingList.size() >= taskNum) {
            Task currTask = storingList.get(taskNum - 1);


        } else {
            formattingDivider("    There is no task " + taskNum + "!!!");
        }

    }

    public void listTheList() {
        int sizeOfList = storingList.size();
        String stringList = "     Here are the tasks in your list: \n";

        for (int i = 1; i <= sizeOfList; i++) {
            stringList = stringList + "    " + i + ". " + storingList.get(i - 1) + "\n"; //add tasks
        }

        formattingDivider(stringList);
    }

    public void sayonara() {
        formattingDivider("    Okie!! Goodbye!");
    }

}
