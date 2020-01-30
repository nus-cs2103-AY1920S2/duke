import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingText = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greetingText);

        String filePath = "data/duke.txt";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            loadTasks(filePath, taskList);
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    String byeText = "    ____________________________________________________________\n" +
                            "     Bye. Hope to see you again soon!\n" +
                            "    ____________________________________________________________";
                    System.out.println(byeText);
                    break;
                } else if (input.equals("list")) {
                    printList(taskList);
                } else if (input.contains("done")) {
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]);
                    taskDone(taskList, taskNum);
                    updateFile(filePath, taskList);
                } else if (input.contains("todo")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        addList(new ToDo(input.substring(5)), taskList);
                        updateFile(filePath, taskList);
                    }
                } else if (input.contains("deadline")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        int index = input.indexOf("/");
                        addList(new Deadline(input.substring(9, index), input.substring(index + 4)), taskList);
                    }
                } else if (input.contains("event")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    } else {
                        int index = input.indexOf("/");
                        addList(new Event(input.substring(6, index), input.substring(index + 4)), taskList);
                    }
                } else if (input.contains("delete")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! Please specify a task number.");
                    } else {
                        String[] inputArr = input.split(" ");
                        int taskNum = Integer.parseInt(inputArr[1]);
                        deleteTask(taskList, taskNum);
                        updateFile(filePath, taskList);
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void loadTasks(String filePath, ArrayList<Task> taskList) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            parseLine(s.nextLine(), taskList);
        }
    }

    public static void parseLine(String taskString, ArrayList<Task> taskList) {
//        System.out.println(taskString);
        String[] taskDetails = taskString.split(",");
//        System.out.println(Arrays.toString(taskDetails));
        for (int i = 0; i < taskDetails.length; i++) {
            taskDetails[i] = taskDetails[i].trim();
        }
//        System.out.println(Arrays.toString(taskDetails));
        String taskType = taskDetails[0];
//        System.out.println("taskType: " + taskType);
        boolean isTaskDone = taskDetails[1].equals("1");
//        System.out.println(taskDetails[1]);
//        System.out.println("isTaskDone: " + isTaskDone);
        String taskName = taskDetails[2];
//        System.out.println("taskName: " + taskName);
        if (taskType.equals("T")) {
            Task newTask = new ToDo(taskName);
            newTask.done = isTaskDone;
            taskList.add(newTask);
        } else if (taskType.equals("D")) {
            String taskTime = taskDetails[3];
            Task newTask = new Deadline(taskName, taskTime);
            newTask.done = isTaskDone;
            taskList.add(newTask);
        } else if (taskType.equals("E")) {
            String taskTime = taskDetails[3];
            Task newTask = new Event(taskName, taskTime);
            newTask.done = isTaskDone;
            taskList.add(newTask);
        }
    }

    private static void updateFile(String filePath, ArrayList<Task> taskList) throws IOException {
        String textToAdd = "";
        for (Task t : taskList) {
            textToAdd += t.toFile() + "\n";
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void deleteTask(ArrayList<Task> list, int taskNum) {
        Task removedTask = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        System.out.println("   ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       " + removedTask + "\n" +
                "     Now you have " + list.size() +" tasks in the list.");
    }

    public static void taskDone(ArrayList<Task> list, int taskNum) {
        Task doneTask = list.get(taskNum - 1);
        doneTask.toggleDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + doneTask + "\n" +
                "    ____________________________________________________________");
    }

    public static void addList(Task task, ArrayList<Task> list) {
        list.add(task);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + list.size() + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (Task s : list) {
            System.out.println("     " + (list.indexOf(s)+1) + ". " + s);
        }
        System.out.println("    ____________________________________________________________");
    }

}
