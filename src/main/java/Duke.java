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
                listInstruction(taskList);
                reply = SCANNER.nextLine();
            } else if (instruction.equals("done")) {
                doneInstruction(replyArr, taskList);
                reply = SCANNER.nextLine();

            } else if(instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")) {
                taskVariants(instruction, reply, taskList, replyArr);
                reply = SCANNER.nextLine();

            } else {
//                Task currTask = new Task(reply);
//                taskList.add(currTask);
//                printWithBorder("Added: " + reply);
                reply = SCANNER.nextLine();
            }
        }

        printWithBorder("Bye! See ya later, alligator!");
    }


    public static void printWithBorder(String message) {
        System.out.println("    ###################################\n"
                + "    " + message +"\n"
                + "    ###################################\n");

    }

    public static void doneInstruction(String[] replyArr, ArrayList<Task> taskList ) {
        int taskNum = Integer.parseInt(replyArr[1]) - 1;
        Task currTask = taskList.get(taskNum);
        currTask.isDone = true;

        String doneMsg = "Nice! Task marked as done: \n    " + " [" + currTask.getStatusIcon() + "] "
                + currTask.getDescription();
        printWithBorder(doneMsg);
    }

    public static void listInstruction(ArrayList<Task> taskList) {
        String completeList = "Task(s) in your list:";
        for (Task task : taskList) {
            completeList += "\n    " + ((taskList.indexOf(task) + 1) + "." + task.toString());
        }
        printWithBorder(completeList);
    }



    public static void taskAdded(Task task, ArrayList<Task> taskList) {
        printWithBorder("Alright! Task added:\n      " + task.toString() + "\n    Now you have " + taskList.size()
                + " task(s) in your list!");
    }

    public static void taskVariants(String instruction, String reply, ArrayList<Task> taskList, String[] replyArr) {
        if(instruction.equals("deadline")) {
            String[] taskReplyArr = reply.split("/by");
            String[] taskInstrArr = taskReplyArr[0].split(" ");
            String task = "";
            for(int i = 1; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];
            Deadline deadLine = new Deadline(task, timeDate);
            taskList.add(deadLine);
            taskAdded(deadLine, taskList);
        } if(instruction.equals("event")) {
            String[] taskReplyArr = reply.split("/at");
            String[] taskInstrArr = taskReplyArr[0].split(" ");
            String task = "";
            for(int i = 1; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }

            String timeDate = taskReplyArr[1];
            Event event = new Event(task, timeDate);
            taskList.add(event);
            taskAdded(event, taskList);

        } else if (instruction.equals("todo")) {
            String task = "";
            for(int i = 1; i < replyArr.length; i++) {
                task += " " + replyArr[i];
            }
            Todo toDo = new Todo(task);
            taskList.add(toDo);
            taskAdded(toDo, taskList);
        }
    }




}
