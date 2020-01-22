import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";

        printWithBorder(greeting);
        output();
    }

    public static void output() throws DukeException {
        String reply = SCANNER.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();
        while (!reply.equals("bye")) {
            try {
                String[] replyArr = reply.split(" ");
                String instruction = replyArr[0];
                if (instruction.equals("list")) {
                    listInstruction(taskList);
                    reply = SCANNER.nextLine();
                } else if (instruction.equals("done")) {
                    doneInstruction(replyArr, taskList);
                    reply = SCANNER.nextLine();
                } else if(instruction.equals("deadline")) {
                    handleDeadline(reply, taskList);
                    reply = SCANNER.nextLine();
                } else if (instruction.equals("todo")) {
                    handleTodo(reply, taskList);
                    reply = SCANNER.nextLine();
                } else if (instruction.equals("event")) {
                    handleEvent(reply, taskList);
                    reply = SCANNER.nextLine();
                } else if (instruction.equals("delete")) {
                    deleteInstruction(replyArr, taskList);
                    reply = SCANNER.nextLine();
                } else {
                    otherInstructions(instruction);
                    reply = SCANNER.nextLine();
                }
            } catch (DukeException ex) {
                printWithBorder(ex.toString());
                reply = SCANNER.nextLine();
            }
        }
        printWithBorder("Bye! See ya later, alligator!");
    }


    public static void doneInstruction(String[] replyArr, ArrayList<Task> taskList) throws DukeException {
        int taskNum = Integer.parseInt(replyArr[1]) - 1;
        if (taskNum > taskList.size() - 1) {
            throw new DukeException("Task doesn't exist!");
        } else {
            Task currTask = taskList.get(taskNum);
            currTask.isDone = true;
            String doneMsg = "Nice! Task marked as done: \n    " + currTask.toString();
            printWithBorder(doneMsg);
        }
    }

    public static void listInstruction(ArrayList<Task> taskList) {
        String completeList = "Task(s) in your list:";
        for (Task task : taskList) {
            completeList += "\n    " + ((taskList.indexOf(task) + 1) + "." + task.toString());
        }
        printWithBorder(completeList);
    }

    public static void deleteInstruction(String[] replyArr, ArrayList<Task> taskList) throws DukeException {
        int taskNum = Integer.parseInt(replyArr[1]) - 1;
        try {
            Task currTask = taskList.get(taskNum);
            String deleteMsg = "Okay! Task removed: \n      " + currTask.toString() + "\n    Now you have "
                    + (taskList.size() - 1) + " task(s) in your list!";
            printWithBorder(deleteMsg);
            taskList.remove(taskNum);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Task doesn't exist! Add a new task!");
        }
    }


    public static void taskAdded(Task task, ArrayList<Task> taskList) {
        printWithBorder("Alright! Task added:\n      " + task.toString() + "\n    Now you have " + taskList.size()
                + " task(s) in your list!");
    }

    public static void handleDeadline(String reply, ArrayList<Task> taskList) throws DukeException {
        String[] taskReplyArr = reply.split("/by");
        if (taskReplyArr.length < 2) {
            throw new DukeException("Specify deadline with: /by \n" +
                    "    i.e. deadline Submit assignment /by 6th Jan, 6pm ");
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];
            Deadline deadLine = new Deadline(task, timeDate);
            taskList.add(deadLine);
            taskAdded(deadLine, taskList);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Specify deadline with: /by \n" +
                    "    i.e. deadline Submit assignment /by 6th Jan, 6pm ");
        }
    }

    public static void handleEvent(String reply, ArrayList<Task> taskList) throws DukeException {
        String[] taskReplyArr = reply.split("/at");
        if (taskReplyArr.length < 2) {
            throw new DukeException("Specify event with: /at \n" +
                    "    i.e. event Project Meeting /at 6th Jan, 6pm ");
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];
            Event event = new Event(task, timeDate);
            taskList.add(event);
            taskAdded(event, taskList);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Specify event with: /at \n" +
                    "    i.e. event Project Meeting /at 6th Jan, 6pm ");
        }
    }

    public static void handleTodo(String reply, ArrayList<Task> taskList) throws DukeException {
        String[] taskReplyArr = reply.split(" ");
        try {
            String task = taskReplyArr[1];
            for (int i = 2; i < taskReplyArr.length; i++) {
                task += " " + taskReplyArr[i];
            }
            Todo toDo = new Todo(task);
            taskList.add(toDo);
            taskAdded(toDo, taskList);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Specify your todo \n" +
                    "    i.e. todo Complete tutorials ");
        }
    }

//    public static void taskVariants(String instruction, String reply, ArrayList<Task> taskList, String[] replyArr) {
//        if (instruction.equals("deadline")) {
//            handleDeadline(reply, taskList);
//        } else if (instruction.equals("event")) {
//            handleEvent(reply, taskList);
//        } else if (instruction.equals("todo")) {
//            handleTodo(reply, taskList);
//        }
//    }

    public static void otherInstructions(String instruction) throws DukeException {
        throw new DukeException("Sorry! I don't understand what is " + instruction);
    }

    public static void printWithBorder(String message) {
        System.out.println("    ###############################################\n"
                + "    " + message + "\n"
                + "    ###############################################\n");
    }
}
