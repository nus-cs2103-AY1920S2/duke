import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> list=new ArrayList<String>(100);
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("____________________________________________________________\n Hello! I'm Duke\n What can I do for you?\n____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            String sentence = sc.nextLine();
            switch(sentence){
                case "list":
                    System.out.println("____________________________________________________________");
                    for(int i=0;i<list.size();i++){
                        System.out.println(i+1+". "+list.get(i));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("____________________________________________________________\n Bye. Hope to see you again soon!\n____________________________________________________________");
                    flag=false;
                    break;
                default:
                    list.add(sentence);
                    System.out.println("____________________________________________________________\nadded: " + sentence + "\n____________________________________________________________");
            }

        }
    }
}
