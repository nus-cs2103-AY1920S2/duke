import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    static final String ExitCommand = "bye";
    static final String ListCommand = "list";
    static final String DoneCommand = "done";
    static final String TodoCommand = "todo";
    static final String DeadlineCommand = "deadline";
    static final String EventCommand = "event";

    static final String[] validCommands = {
            ExitCommand,
            ListCommand,
            DoneCommand,
            TodoCommand,
            DeadlineCommand,
            EventCommand
    };

    public static void verifyDukeCommand(String command) throws InvalidDukeCommandException {
        if (!Arrays.asList(validCommands).contains(command)) {
            throw new InvalidDukeCommandException();
        }
    }

    public static void addTaskReport(Task task, int numOfTasks){
        System.out.println("\t Got it. I've added this task: \n" +
                "\t\t"+ task +"\n" +
                "\t Now you have "+ numOfTasks+ " tasks in the list.");
    }

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");


        Scanner scanner = new Scanner(System.in);
        String desc = "";
        String timestamp = "";

        main:
        while (scanner.hasNextLine()) {
            System.out.println("\t____________________________________________________________");
            String line = scanner.nextLine().trim();
            String[] separateLine = line.split(" ", 2);
            String command = separateLine[0];
            String parameters = separateLine.length > 1 ? separateLine[1] : "";

            try {
                verifyDukeCommand(command);
            } catch (InvalidDukeCommandException invalidDukeCommandException) {
                System.out.println("\t " + invalidDukeCommandException);
                System.out.println("\t____________________________________________________________");
                continue;
            }

            Task task;
            String[] taskInfo;
            switch (command) {
                case ExitCommand:
                    System.out.println("\t Bye. Hope to see you again soon!");
                    System.out.println("\t____________________________________________________________");
                    break main;
                case ListCommand:
                    for(int i = 1; i <= tasks.size(); i++){
                        System.out.println("\t " + i + ". " + tasks.get(i-1));
                    }
                    break;
                case DoneCommand:
                    try {
                        String[] splited = line.split(" ");
                        if(splited.length < 2){
                            throw new InvalidDukeFormatException("The index of a done cannot be empty.");
                        }
                        int taskId = Integer.parseInt(splited[1]);
                        if (taskId <= 0 || taskId > tasks.size()){
                            throw new InvalidDukeFormatException("Invalid task index provided!");
                        }

                        task = tasks.get(taskId  - 1);
                    } catch (InvalidDukeFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t " + e);
                        System.out.println("\t____________________________________________________________");
                        continue;
                    }

                    task.markAsDone();
                    System.out.println("\t Nice! I've marked this task as done: ");
                    System.out.println("\t\t" + task);
                    break;

                case TodoCommand:
                    try {
                        task = new TodoTask(parameters);
                    } catch (InvalidDukeFormatException e) {
                        System.out.println("\t " + e);
                        System.out.println("\t____________________________________________________________");
                        continue;
                    }
                    tasks.add(task);
                    addTaskReport(task, tasks.size());
                    break;
                case DeadlineCommand:
                    taskInfo = parameters.split("/by");
                    desc = "";
                    timestamp = "";
                    if (taskInfo.length > 0){
                        desc = taskInfo[0].trim();
                    }
                    if (taskInfo.length > 1 ){
                        timestamp = taskInfo[1].trim();
                    }
                    try {
                        task = new DeadlineTask(desc, timestamp);
                    } catch (InvalidDukeFormatException e) {
                        System.out.println("\t " + e);
                        System.out.println("\t____________________________________________________________");
                        continue;
                    }
                    tasks.add(task);
                    addTaskReport(task, tasks.size());
                    break;

                case EventCommand:
                    taskInfo = parameters.split("/at");

                    if (taskInfo.length > 0){
                        desc = taskInfo[0].trim();
                    }
                    if (taskInfo.length > 1 ){
                        timestamp = taskInfo[1].trim();
                    }
                    try {
                        task = new EventTask(desc, timestamp);
                    } catch (InvalidDukeFormatException e) {
                        System.out.println("\t " + e);
                        System.out.println("\t____________________________________________________________");
                        continue;
                    }
                    tasks.add(task);
                    addTaskReport(task, tasks.size());
                    break;
            }

            System.out.println("\t____________________________________________________________");
        }
    }
}
