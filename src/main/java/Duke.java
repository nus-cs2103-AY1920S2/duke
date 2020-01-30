import java.io.IOException;
import java.sql.SQLOutput;
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
        System.out.println("Konnichiwa! I am Duke the cat! What can I do for you? meow~ (^.___.^)");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasksArr = new ArrayList<>();
        while(sc.hasNext()) {
            try {
                String command = sc.next();
                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list!");
                    System.out.println("____________________________________________________________");
                    for(int i=0; i<tasksArr.size(); i++) {
                        System.out.println("        "+ i+1 + " "+ tasksArr.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.equals("done")) {
                    int index = sc.nextInt()-1;
                    if (index < 0 || index > tasksArr.size()-1) {
                        throw new DukeException("done");
                    } else {
                        tasksArr.get(index).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Good job! I have marked the task as done! meow~ \n");
                        System.out.println("        " + tasksArr.get(index));
                        System.out.println("____________________________________________________________");
                    }
                } else if (command.equals("todo")) {
                    String str = sc.nextLine();
                    if (str.split("").length > 1) {
                        ToDo t = new ToDo(str);
                        tasksArr.add(t);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it! I have added this task: \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("Now you have " + tasksArr.size()+ " tasks in the list! [^._.^]ﾉ");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new DukeException("todo");
                    }
                } else if (command.equals("deadline")) {
                    String str = sc.nextLine();
                    if (str.split("").length > 1) {
                        String[] splitStr = str.split("/by");
                        DeadLine t = new DeadLine(splitStr[0], splitStr[1]);
                        tasksArr.add(t);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it! I have added this task: \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("Now you have " + tasksArr.size()+ " tasks in the list! [^._.^]ﾉ");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new DukeException("deadline");
                    }
                } else if (command.equals("event")) {
                    String str = sc.nextLine();
                    if (str.split("").length > 1) {
                        String[] splitStr = str.split("/at");
                        Event t = new Event(splitStr[0], splitStr[1]);
                        tasksArr.add(t);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it! I have added this task: \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("Now you have " + tasksArr.size()+ " tasks in the list! [^._.^]ﾉ");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new DukeException("event");
                    }
                } else if (command.equals("delete")) {
                    int index = sc.nextInt()-1;
                    if (index < 0 || index > tasksArr.size()-1) {
                        throw new DukeException("delete");
                    } else {
                        Task t = tasksArr.get(index);
                        tasksArr.remove(index);
                        System.out.println("____________________________________________________________");
                        System.out.println("Noted! I have removed this task: \n");
                        System.out.println("        " + t + "\n");
                        System.out.println("Now you have " + tasksArr.size()+ " tasks in the list![^._.^]ﾉ");
                        System.out.println("____________________________________________________________");
                    }
                } else if (command.equals("bye")) {
                    System.out.println("Sayonara~");
                    System.out.println(" (_＼ヽ\n" +
                            "　 ＼＼ .Λ＿Λ.\n" +
                            "　　 ＼(　ˇωˇ)　\n" +
                            "　　　 >　⌒ヽ\n" +
                            "　　　/ 　 へ＼\n" +
                            "　　 /　　/　＼＼\n" +
                            "　　 ﾚ　ノ　　 ヽ_つ\n" +
                            "　　/　/\n" +
                            "　 /　/|\n" +
                            "　(　(ヽ\n" +
                            "　|　|、＼\n" +
                            "　| 丿 ＼ ⌒)\n" +
                            "　| |　　) /\n" +
                            "`ノ ) 　 Lﾉ\n" +
                            "(_／");
                    break;
                } else {
                    throw new DukeException("invalid");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}