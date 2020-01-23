import java.util.Scanner;

public class TaskList{
    private Task[] taskList;
    int count;

    public TaskList(){
        this.taskList = new Task[100];
        this.count = 1;
    }


    private enum Command{
        TASK, DONE, LIST, BYE;
        public static Command convert(String cmd){
            if(cmd.equals("bye")){
                return BYE;
            }else if(cmd.equals("list")){
                return LIST;
            }else if(cmd.equals("done")){
                return DONE;
            }
            else{
                return TASK;
            }
        }
    }

    private void addTask(int ID, String task){
        taskList[ID - 1] = new Task(task, ID);
        System.out.println("added: " + task);
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
                case TASK:
                    addTask(count++, input);
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