import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.Integer.*;

public class Duke {
    static String PATH = "./src/main/java/savedTaskList.txt";
    static Task[] totalTasks = new Task[100];
    static int totalTasksCount = 0;
    static boolean isProblem = false;

    public static void testMessage(String userInput) throws DukeException {
        if (!userInput.contains("bye") && !userInput.contains("list") && !userInput.contains("done 2")
                && !userInput.contains("delete") && !userInput.contains("todo")
                && !userInput.contains("deadline") && !userInput.contains("event")) {
            System.out.println("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________\n");
            throw new DukeException("I don't know what that means");

        } else if (!userInput.contains(" ") && !userInput.contains("bye") && !userInput.contains("list")){
            System.out.println("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description of a " + userInput + " cannot be empty.\n"
                    + "    ____________________________________________________________\n");
            throw new DukeException("Empty");
        }
    }

    public static void init(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public static String input(){
        Scanner myObj = new Scanner(System.in);
        String inputData = myObj.nextLine();
        try{
            testMessage(inputData);
        } catch(DukeException ex){
            isProblem = true;
        }
        return inputData;
    }

    public static void bye(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void list(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        int i = 0;
        int j = 1;
        while (i < totalTasksCount) {
            System.out.println("     " + j + "." + totalTasks[i].toString());
            i++;
            j++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void done2(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        int i = 0;
        while (i < totalTasksCount) {
            if (totalTasks[i].getDone()) {
                System.out.println("       " + totalTasks[i].getStatusIcon() + " " + totalTasks[i].getDescription());
            }
            i++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void delete(String userInput){
        String deleteSelect = userInput.substring(7);
        int deleteSelectNo = parseInt(deleteSelect) - 1;
        if (deleteSelectNo != totalTasksCount) {
            while (deleteSelectNo <= totalTasksCount) {
                totalTasks[deleteSelectNo] = totalTasks[deleteSelectNo + 1];
                deleteSelectNo++;
            }
        }
        totalTasksCount = totalTasksCount - 1;
        Task.taskNo = Task.taskNo - 1;
    }

    public static void toDo(String userInput){
        String content = userInput.substring(5);
        Task t = new Todo(content);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
    }

    public static void deadline(String userInput){
        userInput = userInput.substring(9);
        String contentTasks = userInput.substring(0, userInput.indexOf("/by") - 1);
        String taskDeadline = userInput.substring(userInput.indexOf("/by") + 4);
        Task t = new Deadline(contentTasks, taskDeadline);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
    }

    public static void event(String userInput){
        userInput = userInput.substring(6);
        String contentTasks = userInput.substring(0, userInput.indexOf("/at") - 1);
        String taskTime = userInput.substring(userInput.indexOf("/at") + 4);
        Task t = new Event(contentTasks, taskTime);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
    }

    public static void writeFile() throws IOException {
        FileWriter fw = new FileWriter(PATH);
        int i = 0;
        while (i < totalTasksCount){
            fw.write(totalTasks[i].toString() + System.lineSeparator());
            i++;
        }
        fw.close();
    }

    public static void readFile() throws FileNotFoundException {
        File savedTaskList = new File(PATH);
        Scanner scanSavedTaskList = new Scanner(savedTaskList);
        while (scanSavedTaskList.hasNext()){
            String content =  scanSavedTaskList.nextLine();

            if (content.contains("[T]")) {
                toDo("todo" + content.substring(content.indexOf(" ")));
            } else if (content.contains("[D]")) {
                deadline("deadline" + content.substring(content.indexOf(" "), content.indexOf("by:") - 1)
                        + "/by " + content.substring(content.indexOf("by:") + 4, content.length() - 1));
            } else {
                event("event" + content.substring(content.indexOf(" "), content.indexOf("at:") - 1)
                        + "/at " + content.substring(content.indexOf("at:") + 4, content.length() - 1));
            }
        }
    }

    public static void main(String[] args){
        try {
            readFile();
        } catch (FileNotFoundException e) {
            // creating new file
        }
        init();
        String userInput = input();

        while(!userInput.equals("bye")){
            if(isProblem) {
                isProblem = false;
            } else {
                if (userInput.equals("list")) {
                    list();
                } else if (userInput.equals("done 2")) {
                    done2();
                } else if (userInput.contains("delete")){
                    delete(userInput);
                    try {
                        writeFile();
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else if (userInput.contains("todo")) {
                    toDo(userInput);
                    totalTasks[totalTasksCount-1].printInit();
                    try {
                        writeFile();
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else if (userInput.contains("deadline")) {
                    deadline(userInput);
                    totalTasks[totalTasksCount-1].printInit();
                    try {
                        writeFile();
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else {
                    event(userInput);
                    totalTasks[totalTasksCount-1].printInit();
                    try {
                        writeFile();
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                }
            }
            userInput = input();
        }
        bye();
    }
}
