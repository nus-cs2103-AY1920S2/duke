import java.util.Scanner;

public class Duke {
    static String[] taskList = new String[100];
    static int count = 1;


    public enum Command{
        TASK, BYE, LIST;
        public static Command convert(String cmd){
            if(cmd.equals("bye")){
                return BYE;
            }else if(cmd.equals("list")){
                return LIST;
            }
            else{
                return TASK;
            }
        }
    }

    private static void addTask(int ID, String task){
        taskList[ID] = ID + ". " + task;
        System.out.println("added: " + task);
    }

    private static void printList(){
        for(String x: taskList){
            if(x != null) {
                System.out.println(x);
            }
        }
    }


    public static void inputLoop() {
        Scanner sc = new Scanner(System.in);
        while(true){
            String task = sc.nextLine();
            Command cmd = Command.convert(task);
            switch(cmd){
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case LIST:
                    printList();
                    break;
                case TASK:
                    addTask(count++, task);
                    break;
            }
            if(cmd == Command.BYE){break;}

                //System.out.println("Bye. Hope to see you again soon!");

                //addTask(count++, cmd);

        }
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Oi\n" + logo + "\n" +
                "     What you waaaaaant?\n" +
                "    _________________________________");

        inputLoop();

    }

}

