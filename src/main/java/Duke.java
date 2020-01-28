import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {
        Path path = Paths.get("data/duke.txt");
        File noteFile = new File(path.toString());
        if(Files.notExists(path)) {
            noteFile = new File("data/duke.txt");
        } else {
            try {
                fileToArray(noteFile.toString());

            } catch (FileNotFoundException ex) {
                System.out.print("File not found");
            }
        }

        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";

        printWithBorder(greeting);
        output();

        String filePathStr = noteFile.getAbsolutePath();
        try {
            writeToFile(filePathStr, arrayToFile(taskList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String arrayToFile(ArrayList<Task> taskList) {
        String outputToFile = "";
        for(Task task : taskList) {
            String taskType = task.getClass().getSimpleName();
            String taskDesc = task.getDescription();
            boolean taskIsDone = task.isDone;

            if(taskType.equals("Todo")) {
                outputToFile += "T/" + taskIsDone + "/" + taskDesc + "\n";
            } else if (taskType.equals("Event")) {
                outputToFile += "E/" + taskIsDone + "/" + taskDesc + "/" + ((Event)task).getAt() + "\n";
            } else {
                outputToFile += "D/" + taskIsDone + "/" + taskDesc + "/" + ((Deadline)task).getBy() + "\n";
            }
        }
        return outputToFile;
    }

    private static void fileToArray(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while(sc.hasNext()) {
            String note = sc.nextLine();
            String[] noteArr = note.split("/");
            String noteType = noteArr[0];
            boolean taskIsDone = (noteArr[1]).equals("true")  ? true : false;
            String taskDesc = noteArr[2];
            String dateTime = "";
            if(!noteType.equals("T")) {
                dateTime = noteArr[3];
            }

            if(noteType.equals("T")) {
                taskList.add(new Todo(taskDesc, taskIsDone));
            } else if(noteType.equals("E")) {
                taskList.add(new Event(taskDesc, dateTime ,taskIsDone));
            } else {
                taskList.add(new Deadline(taskDesc, dateTime, taskIsDone));
            }
        }
    }


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToAdd);
        bw.close();
    }

    public static void output() throws DukeException {
        String reply = SCANNER.nextLine();
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
            Deadline deadLine = new Deadline(task, timeDate, false);
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
            Event event = new Event(task, timeDate, false);
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
            Todo toDo = new Todo(task, false);
            taskList.add(toDo);
            taskAdded(toDo, taskList);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Specify your todo \n" +
                    "    i.e. todo Complete tutorials ");
        }
    }


    public static void otherInstructions(String instruction) throws DukeException {
        throw new DukeException("Sorry! I don't understand what is " + instruction);
    }

    public static void printWithBorder(String message) {
        System.out.println("    ###############################################\n"
                + "    " + message + "\n"
                + "    ###############################################\n");
    }
}
