import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelMethods {
    List<Task> storingList = new ArrayList<>();

    public LevelMethods() {

    }

    /**
     * Display format
     *
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

    public void printAddingTask(Task task) {
        String printStr = "     Got it. I've added this task: \n      "
                + task + "\n"
                + "     Now you have " + storingList.size() + " tasks in the list.";
        formattingDivider(printStr);
    }


    /**
     * Level 1 task - echo
     *
     * @param inputStr
     */
    public void echo(String inputStr) {

        if (inputStr.contains("todo")) {
            if (inputStr.substring(0, 4).equals("todo")) {
                Task todo = new Todo(inputStr);
                storingList.add(todo);

                printAddingTask(todo);
            } else {
                formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");
            }

        } else if (inputStr.contains("event")) {
            if (inputStr.substring(0, 5).equals("event")) {
                String[] eventAndTime = inputStr.substring(6, inputStr.length()).split(" /at ");

                Task event = new Event(eventAndTime[0], eventAndTime[1]);
                storingList.add(event);

                //printing
                printAddingTask(event);


            } else {
                formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");
            }

        } else if (inputStr.contains("deadline")) {
            if (inputStr.substring(0, 8).equals("deadline")) {
                String[] eventAndTime = inputStr.substring(9, inputStr.length()).split(" /by ");
                Task deadline = new Deadline(eventAndTime[0], eventAndTime[1]);
                storingList.add(deadline);

                //print
                printAddingTask(deadline);

            } else {
                formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");
            }
        } else {
            //correctFormat = false;
            formattingDivider("    Eh, tell me if it is todo, deadline or event please :(");
        }

//        Task task = new Task(inputStr);
//        storingList.add(task);





    }


    public void completeTask(int taskNum) {
        if (storingList.size() >= taskNum && taskNum != 0) {
            //Task currTask = storingList.get(taskNum - 1);

            //currTask.isDone = true;
            //Task updatedTask = new Task(currTask.description);
            //updatedTask.isDone = true;

            storingList.get(taskNum - 1).isDone = true;
            //storingList.set(taskNum - 1, updatedTask);

            String printStr = "    Nice! I've marked this task as done: \n    " + storingList.get(taskNum - 1);
            formattingDivider(printStr);


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
