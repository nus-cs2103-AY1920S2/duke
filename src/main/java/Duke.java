import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Konnichiwa! Watashi no namaeha Duke desu! Whatchu can i duwu for yuwu? (^.___.^)");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasksArr = new ArrayList<>();
        while(sc.hasNext()) {
            try {
                String command = sc.next();
                if (command.equals("list")) {
                    System.out.println("Hewe awu de tasku in ywour listu! uwu \n");
                    for(int i=0; i<tasksArr.size(); i++) {
                        System.out.println(i+1 + " "+ tasksArr.get(i));
                    }
                } else if (command.equals("done")) {
                    int index = sc.nextInt()-1;
                    if (index < 1 || index > tasksArr.size()-1) {
                        throw new DukeException("done");
                    } else {
                        tasksArr.get(index).markAsDone();
                        System.out.println("Nicesu! watashi hasu marku the tasku as done desu ʕ ᵒ ᴥ ᵒʔ: \n");
                        System.out.println("        " + tasksArr.get(index) + "\n");
                    }
                } else if (command.equals("todo")) {
                    String str = sc.nextLine();
                    if (str.split("").length > 1) {
                        ToDo t = new ToDo(str);
                        tasksArr.add(t);
                        System.out.println("Got it desu! uwu Watashi hasu added des tasku (´・(oo)・｀): \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("nuw omae has " + tasksArr.size()+ " tasks in the list");
                    } else {
                        throw new DukeException("todo");
                    }
                } else if (command.equals("deadline")) {
                    String str = sc.nextLine();
                    if (str.split("").length > 1) {
                        String[] splitStr = str.split("/by");
                        DeadLine t = new DeadLine(splitStr[0], splitStr[1]);
                        tasksArr.add(t);
                        System.out.println("Gotcha! Watashi hasu added des tasku [^._.^]ﾉ: \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("nuw omae has " + tasksArr.size()+ " tasks in the list");
                    } else {
                        throw new DukeException("todo");
                    }
                } else if (command.equals("event")) {
                    String str = sc.nextLine();
                    if (str.split("").length > 1) {
                        String[] splitStr = str.split("/at");
                        Event t = new Event(splitStr[0], splitStr[1]);
                        tasksArr.add(t);
                        System.out.println("Gotcha! Watashi hasu added des tasku (=＾● ⋏ ●＾=): \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("nuw omae has " + tasksArr.size()+ " tasks in the list");
                    } else {
                        throw new DukeException("event");
                    }
                } else {
                    throw new DukeException("invalid");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}