import java.util.Scanner;


/**
 * create a complete class
 * Todo: Regular task
 * Event: start and end time
 * Deadline: By a certain timing
 */


public class TaskList{
    private Task[] taskList;
    int count;

    public TaskList(){
        this.taskList = new Task[100];
        this.count = 1;
    }


    private enum Command{
        TASK, TODO, DEADLINE, EVENT, DONE, LIST, BYE;
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
            }else{
                return TASK;
            }
        }
    }

    private void totalTask(){
        System.out.println("You have " + (count -1) + " task on your list.");
    }

    private void addTodo(int ID, String task){
        taskList[ID - 1] = new Todo(task, ID);
        System.out.println("Affirmative. Adding a to-do task :) \n" + "added: " + task);
        totalTask();
    }

    private void addDead(int ID, String task, String timing){
        taskList[ID - 1] = new Deadline(task, ID, timing);
        System.out.println("Affirmative. Adding a task with a deadline. :) \n" + "added: " + task + " " + timing);
        totalTask();
    }

    private void addEvent(int ID, String task, String timing){
        taskList[ID - 1] = new Event(task, ID, timing);
        System.out.println("Affirmative. Adding an event. :) \n" + "added: " + task + " " + timing);
        totalTask();
    }

    private String makeTask(String[] arr){
        String task = "";
        int i = 1;
        while(i < arr.length && !arr[i].startsWith("/")){
            task += arr[i++] + " ";
        }
        return task;
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
        for(Task x: taskList){
            if(x != null) {
                System.out.println(x);
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
                    addTodo(count++, makeTask(inputAsArray));
                    break;
                case DEADLINE:
                    addDead(count++, makeTask(inputAsArray), makeTiming(inputAsArray));
                    break;
                case EVENT:
                    addEvent(count++, makeTask(inputAsArray), makeTiming(inputAsArray));
                    break;
                case DONE:
                    int num = Integer.valueOf(inputAsArray[1]);
                    taskList[num - 1].completeTask();
                    break;
            }
            if(cmd == Command.BYE){break;}
        }
    }



}