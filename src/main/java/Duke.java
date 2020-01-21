import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";

        printWithBorder(greeting);
        output();
    }

    public static void output() {
        String reply = SCANNER.nextLine();

        ArrayList<Task> taskList = new ArrayList<>();


        while(!reply.equals("bye")) {
            String[] replyArr = reply.split(" ");
            String instruction = replyArr[0];
            if(instruction.equals("list")) {
                String completeList = "Task(s) in your list:";
                for (Task task : taskList) {
                    completeList += "\n    " + ((taskList.indexOf(task) + 1) + ". [" + task.getStatusIcon() + "] "
                            + task.getDescription());
                }
                printWithBorder(completeList);
                reply = SCANNER.nextLine();
            } else if (instruction.equals("done")) {
                int taskNum = Integer.parseInt(replyArr[1]) - 1;
                Task currTask = taskList.get(taskNum);
                currTask.isDone = true;

                String doneMsg = "Nice! Task marked as done: \n    " + " [" + currTask.getStatusIcon() + "] "
                                + currTask.getDescription();
                printWithBorder(doneMsg);

                reply = SCANNER.nextLine();

            } else {
                Task currTask = new Task(reply);
                taskList.add(currTask);
                printWithBorder("Added: " + reply);
                reply = SCANNER.nextLine();
            }
        }

        printWithBorder("Bye! See ya later, alligator!");
    }


    public static void printWithBorder(String message) {
        System.out.println("    #############################\n"
                + "    " + message +"\n"
                + "    #############################\n");

    }

}
