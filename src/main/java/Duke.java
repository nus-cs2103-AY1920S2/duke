import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();


    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/M/d");
    private static final DateValidator DATE_VALIDATOR = new DateValidator(DATE_FORMATTER);

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateValidator TIME_VALIDATOR = new DateValidator(TIME_FORMATTER);



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

            if (taskType.equals("Todo")) {
                outputToFile += "T>" + taskIsDone + ">" + taskDesc + "\n";
            } else if (taskType.equals("Event")) {
                if (((Event) task).isTime) {
                    outputToFile += "E>" + taskIsDone + ">" + taskDesc + ">" + ((Event) task).getDate() + ">"
                            + ((Event) task).getTime() + "\n";
                } else {
                    outputToFile += "E>" + taskIsDone + ">" + taskDesc + ">" + ((Event) task).getDate() + "\n";
                }
            } else {
                if (((Deadline) task).isTime) {
                    outputToFile += "D>" + taskIsDone + ">" + taskDesc + ">" + ((Deadline) task).getDate() + ">"
                            + ((Deadline) task).getTime() + "\n";
                } else {
                    outputToFile += "D>" + taskIsDone + ">" + taskDesc + ">" + ((Deadline) task).getDate() + "\n";
                }
            }
        }
        return outputToFile;
    }

    private static void fileToArray(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while(sc.hasNext()) {
            String note = sc.nextLine();
            String[] noteArr = note.split(">");
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
                if(noteArr.length == 5) {
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), LocalTime.parse(noteArr[4]),
                            taskIsDone));
                } else if(noteArr.length == 4) {
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), taskIsDone));
                }
            } else {
                if(noteArr.length == 5) {
                taskList.add(new Deadline(taskDesc, LocalDate.parse(noteArr[3]), LocalTime.parse(noteArr[4]),
                        taskIsDone));
                } else if(noteArr.length == 4){
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), taskIsDone));
                }
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
                } else if (instruction.equals("date")) {
                    dateInstruction(replyArr, taskList);
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

    public static void dateInstruction(String[] replyArr, ArrayList<Task> taskList) throws DukeException {
        if(DATE_VALIDATOR.isValidDate(replyArr[1])) {
            LocalDate date = LocalDate.parse(replyArr[1], DATE_FORMATTER);
            String taskOnDate = "";
            for(Task task : taskList) {
                if(task instanceof TaskDate) {
                    if(((TaskDate) task).getDate().equals(date)) {
                        taskOnDate += "\n      " + task.toString();
                    }
                }
            }
            String tasksToday = "The task(s) you have on " + replyArr[1] + ":" + taskOnDate;
            printWithBorder(tasksToday);
        } else {
            throw new DukeException("Please enter a valid date in <YYYY/M/D> format\n" +
                                    "    i.e. 2020/10/28");
        }

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
        String[] taskReplyArr = reply.split("/by ");
        if (taskReplyArr.length < 2) {
            throw new DukeException("Specify deadline with data and/or time with: /by <YYYY/MM/DD> <HH:MM> \n" +
                    "    i.e. deadline Submit assignment /by 2020/01/28 18:00");
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }

            String timeDate = taskReplyArr[1];
            String[] timeDateArr = timeDate.split(" ");
            if(timeDateArr.length == 2) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[1], TIME_FORMATTER);

                    Deadline deadLine = new Deadline(task, formattedDate, formattedTime, false);
                    taskList.add(deadLine);
                    taskAdded(deadLine, taskList);
                } else {
                    throw new DukeException("Invalid date and/or time format! \n" +
                            "    Specify deadline with data and/or time with: /by <YYYY/MM/DD> <HH:MM> \n" +
                            "    i.e. deadline Submit assignment /by 2020/01/28 18:00");
                }
            } else if (timeDateArr.length == 1) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    Deadline deadLine = new Deadline(task, formattedDate, false);
                    taskList.add(deadLine);
                    taskAdded(deadLine, taskList);
                } else if (TIME_VALIDATOR.isValidTime(timeDateArr[0])) {
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[0], TIME_FORMATTER);
                    Deadline deadLine = new Deadline(task, LocalDate.now(), formattedTime, false);
                    taskList.add(deadLine);
                    taskAdded(deadLine, taskList);
                } else {
                    throw new DukeException("Invalid date and/or time format! \n" +
                            "    Specify deadline with data and/or time with: /by <YYYY/MM/DD> <HH:MM> \n" +
                            "    i.e. deadline Submit assignment /by 2020/01/28 18:00");
                }

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Specify deadline with data and/or time with: /by <YYYY/MM/DD> <HH:MM> \n" +
                    "    i.e. deadline Submit assignment /by 2020/01/28 18:00");
        }
    }

    public static void handleEvent(String reply, ArrayList<Task> taskList) throws DukeException {
        String[] taskReplyArr = reply.split("/at ");
        if (taskReplyArr.length < 2) {
            throw new DukeException("Specify event with: /at <YYYY/MM/DD> <HH:MM>\n" +
                    "    i.e. event Project Meeting /at 2020/01/28 18:00");
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];

            String[] timeDateArr = timeDate.split(" ");
            if(timeDateArr.length == 2) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[1], TIME_FORMATTER);

                    Event event = new Event(task, formattedDate, formattedTime, false);
                    taskList.add(event);
                    taskAdded(event, taskList);
                } else {
                    throw new DukeException("Invalid date and/or time format! \n" +
                            "    Specify event with: /at <YYYY/MM/DD> <HH:MM>\n" +
                            "    i.e. event Project Meeting /at 2020/01/28 18:00");
                }
            } else if (timeDateArr.length == 1) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    Event event = new Event(task, formattedDate, false);
                    taskList.add(event);
                    taskAdded(event, taskList);
                } else if (TIME_VALIDATOR.isValidTime(timeDateArr[0])) {
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[0], TIME_FORMATTER);
                    Event event = new Event(task, LocalDate.now(), formattedTime, false);
                    taskList.add(event);
                    taskAdded(event, taskList);
                } else {
                    throw new DukeException("Invalid date and/or time format! \n" +
                            "    Specify event with: /at <YYYY/MM/DD> <HH:MM>\n" +
                            "    i.e. event Project Meeting /at 2020/01/28 18:00");
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Specify event with: /at <YYYY/MM/DD> <HH:MM>\n" +
                    "    i.e. event Project Meeting /at 2020/01/28 18:00");
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
        System.out.println("    ###################################################\n"
                + "    " + message + "\n"
                + "    ###################################################\n");
    }
}
