import java.util.Scanner;

public class Ui {
//            String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
    String logo = "DUKE!\n";
    Scanner sc;
    Parser p;
    TaskList taskList;

    public Ui(){
        sc = new Scanner(System.in);
        p = new Parser();
        System.out.println("Hello from " + logo);
    }

    public void setTaskList(TaskList taskList){
        this.taskList = taskList;
        p.setTaskList(taskList);
    }

    public void awaitUserInput(){
        String in = sc.nextLine();
        while (!in.equals("bye")){
            p.getInput(in);
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
