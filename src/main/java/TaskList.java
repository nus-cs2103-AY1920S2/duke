import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * create a complete class
 * Todo: Regular task
 * Event: start and end time
 * Deadline: By a certain timing
 */


public class TaskList{
    private ArrayList<Task> taskList;
    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    private String path = "data/duke.txt";

    private enum Command{
        TASK, TODO, DEADLINE, EVENT, DONE, LIST, DELETE, BYE;
        public static Command convert(String cmd){
            if(cmd.equals("bye")){
                return BYE;
            }else if(cmd.equals("list")){
                return LIST;
            }else if(cmd.equals("done")){
                return DONE;
            }else if(cmd.equals("todo")||cmd.equals("T")){
                return TODO;
            }else if(cmd.equals("deadline")||cmd.equals("D")){
                return DEADLINE;
            }else if(cmd.equals("event")||cmd.equals("E")){
                return EVENT;
            }else if(cmd.equals("delete")) {
                return DELETE;
            }else{
                return TASK;
            }
        }
    }

    private void totalTask(){
        System.out.println("You have " + (taskList.size()) + " task on your list.");
    }


    private void addTodo(String task){
        taskList.add(new Todo(task));
        System.out.println("Affirmative. Adding a to-do task :) \n" + "added: " + task);
        totalTask();
    }

    private void addTodo(String task, boolean bool){
        taskList.add(new Todo(task, bool));
    }

    private void addDead(String task, String timing){
        taskList.add(new Deadline(task, timing));
        System.out.println("Affirmative. Adding a task with a deadline. :) \n" + "added: " + task + " " + timing);
        totalTask();
    }

    private void addDead(String task, String timing, boolean bool){
        taskList.add(new Deadline(task, timing, bool));
    }

    private void addEvent(String task, String timing){
        taskList.add(new Event(task, timing));
        System.out.println("Affirmative. Adding an event. :) \n" + "added: " + task + " " + timing);
        totalTask();
    }

    private void addEvent(String task, String timing, boolean bool){
        taskList.add(new Event(task, timing, bool));
    }

    private void deleteTask(int index){
        System.out.println("Deleting this task. now you have less things to do :)");
        taskList.remove(index - 1);
        totalTask();
    }

    private String makeTask (String[] arr) throws Exception{
        String task = "";
        int i = 1;
        while(i < arr.length && !arr[i].startsWith("/")){
            task += arr[i++] + " ";
        }
        if(task.equals("")) throw new Exception();
        else return task;
    }

    private String makeTiming(String[] arr){
        String timing ="";
        int i = 1;
        while(i < arr.length){
            if(arr[i].startsWith("/")){
                timing += arr[i++].split("/")[1] + ":";
                while(i < arr.length){
                    timing += " " + arr[i];
                    i++;
                }
                break;
            }
            i++;
        }
        return timing;
    }

    private void printList(){
        int i = 1;
        for(Task x: taskList){
            if(x != null) {
                System.out.println((i++) + "" + x);
            }
        }
    }

    public void inputLoop() {
        Scanner sc = new Scanner(System.in);
        while(true){
            String input = sc.nextLine();
            String[] inputAsArray = input.split(" ");
            Command cmd = Command.convert(inputAsArray[0]);
            switch(cmd){
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case LIST:
                    printList();
                    break;
                case TODO:
                    try{
                        addTodo(makeTask(inputAsArray));
                        save(toTxt());
                    }catch(Exception e){
                        System.out.println("Missing description. Describe abit more leh.");
                    }
                    break;
                case DEADLINE:
                    try{
                        addDead(makeTask(inputAsArray), makeTiming(inputAsArray));
                        save(toTxt());
                    }catch(Exception e){
                        System.out.println("Missing description. Describe abit more leh.");
                    }
                    break;
                case EVENT:
                    try{
                        addEvent(makeTask(inputAsArray), makeTiming(inputAsArray));
                        save(toTxt());
                    }catch(Exception e){
                        System.out.println("Missing description. Describe abit more leh.");
                    }
                    break;
                case DONE:
                    int num = Integer.valueOf(inputAsArray[1]);
                    taskList.set(num - 1, taskList.get(num - 1).completeTask());
                    save(toTxt());
                    break;
                case DELETE:
                    deleteTask(Integer.valueOf(inputAsArray[1]));
                    save(toTxt());
                    break;
                default:
                    System.out.println("What you talking? Me no understand your command :(");
                    break;
            }
            if(cmd == Command.BYE){break;}
        }
    }

    private void fileToRun(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while(s.hasNextLine()){
            String line = s.nextLine();
            String [] taskArr = line.split("/");
            Command type = Command.convert(taskArr[0]);
            boolean completed = toBool(taskArr[1]);
            String task = taskArr[2];
            switch(type){
                case TODO:
                    try{
                        addTodo(task, completed);
                    }catch(Exception e){
                        System.out.println("wrong input todo");
                    }
                    break;
                case DEADLINE:
                    try{
                        addDead(task, taskArr[3], completed);
                    }catch(Exception e){
                        System.out.println("wrong input deadline");
                    }
                    break;
                case EVENT:
                    try{
                        addEvent(task, taskArr[3], completed);
                    }catch(Exception e){
                        System.out.println("wrong input event");
                    }
                    break;
            }
        }
    }

    private static boolean toBool(String com){
        if(com.equals("1")) return true;
        else return false;
    }

    public void resumeSave(String filePath){
        try{
            fileToRun(filePath);
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    private String toTxt(){
        String main = "";
        for(Task x: taskList){
            String type = "";
            int complete = x.getComplete()? 1: 0;
            String task = x.getTask();
            if(x instanceof Todo){
                type = "T";
            }else if(x instanceof Deadline){
                type = "D";
                Deadline d = (Deadline) x;
                task += "/" + d.getTiming();
            }else if(x instanceof Event){
                type = "E";
                Event e = (Event) x;
                task += "/" + e.getTiming();
            }
            main += type + "/" + complete + "/" + task +"\n";
        }
        return main;
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private void save(String textToAdd){
        try{
            writeToFile(path, textToAdd);
        }catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


}