import java.util.*;
import java.util.stream.Collectors;

public class Duke {
    static DukeMain mainObj = new DukeMain();
    String stupidLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    static String resSpace = "    ";
    static String line = "____________________________________________________________";
    static String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    static String taskDoneNote = "Nice! I've marked this task as done:";

    static void respond(String... respondStr) {
        respond(Arrays.asList(respondStr));
    }

    static void respond(List<String> respondStr) {
        Scanner sc2;
        System.out.print(resSpace);
        System.out.println(line);
        for (String str : respondStr) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                System.out.print(resSpace + " ");
                System.out.println(sc2.nextLine());
            }
        }
        System.out.print(resSpace);
        System.out.println(line);
        System.out.println();
    }

    static void addTask(String taskname) {
        mainObj.add(new Task(taskname));
    }

    static void listTask() {
        List<Task> lst = mainObj.getTaskList();
        respond(lst.stream().map(x -> {
            return "" + (lst.indexOf(x) + 1) + "." + x;
        }).collect(Collectors.toList()));
    }

    static void markAsDone(int num) {
        mainObj.get(num - 1).setToDone();
        respond(taskDoneNote, mainObj.get(num - 1).toString());
    }

    static boolean isCommand(String cmd) {
        return cmd.length() > 0;
    }

    static void execCommand(String cmd) {
        String[] terms = cmd.split(" ");
        switch (terms[0]) {
        case "exit":
            respond("Bye. Hope to see you again soon!");
            System.exit(0);
            break;
        case "list":
            listTask();
            break;
        case "done":
            assert terms.length == 2 : "wrong num of terms";
            markAsDone(Integer.parseInt(terms[1]));
            break;
        default:
            addTask(cmd);
            respond("added: " + cmd);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        respond(greetings);
        String cmd;
        while (isCommand(cmd = sc.nextLine())) {
            execCommand(cmd);
        }
    }
}
