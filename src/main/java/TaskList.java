import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private enum Command{
        TASK, TODO, DEADLINE, EVENT, DONE, LIST, DELETE, BYE;
        public static Command convert(String cmd){
            if(cmd.equals("bye")){
                return BYE;
            }else if(cmd.equals("list")){
                return LIST;
            }else if(cmd.equals("done")){
                return DONE;
            }else if(cmd.equals("todo")){
                return TODO;
            }else if(cmd.equals("deadline")){
                return DEADLINE;
            }else if(cmd.equals("event")){
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

    private void addDead(String task, String timing){
        taskList.add(new Deadline(task, timing));
        System.out.println("Affirmative. Adding a task with a deadline. :) \n" + "added: " + task + " " + timing);
        totalTask();
    }

    private void addEvent(String task, String timing){
        taskList.add(new Event(task, timing));
        System.out.println("Affirmative. Adding an event. :) \n" + "added: " + task + " " + timing);
        totalTask();
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
                    }catch(Exception e){
                        System.out.println("Missing description. Describe abit more leh.");
                    }
                    break;
                case DEADLINE:
                    try{
                        addDead(makeTask(inputAsArray), makeTiming(inputAsArray));
                    }catch(Exception e){
                        System.out.println("Missing description. Describe abit more leh.");
                    }
                    break;
                case EVENT:
                    try{
                        addEvent(makeTask(inputAsArray), makeTiming(inputAsArray));
                    }catch(Exception e){
                        System.out.println("Missing description. Describe abit more leh.");
                    }
                    break;
                case DONE:
                    int num = Integer.valueOf(inputAsArray[1]);
                    taskList.set(num - 1, taskList.get(num - 1).completeTask());
                    break;
                case DELETE:
                    deleteTask(Integer.valueOf(inputAsArray[1]));
                    break;
                default:
                    System.out.println("What you talking? Me no understand your command :(");
                    break;
            }
            if(cmd == Command.BYE){break;}
        }
    }



}