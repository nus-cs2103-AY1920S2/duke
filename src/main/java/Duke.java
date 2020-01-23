import java.util.Scanner;

public class Duke {
    public static void inputLoop() {
        Scanner sc = new Scanner(System.in);
        while(true){
            String cmd = sc.next();
            if(cmd.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else{
                System.out.println(cmd);
            }
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

