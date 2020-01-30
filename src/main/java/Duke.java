import java.util.*;
import java.util.stream.Collectors;

/**
 * Main UI method
 */
public class Duke {
    /**
     * Main functional object Duke
     */
    static DukeFile mainObj = new DukeFile();
    static Responder r = new Responder();

    private static class Responder {
        // common responds
        String stupidLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        static String resSpace = "    ";
        static String line = "____________________________________________________________";
        static String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
        static String taskDoneNote = "Nice! I've marked this task as done:";
        static String bye = "Bye. Hope to see you again soon!";
        static String dunno = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        private boolean isOpen = false;

        void start(String... initials) {
            assert !this.isOpen : "illegal usage of responder";
            System.out.print(resSpace);
            System.out.println(line);
            Scanner sc2;
            for (String str : initials) {
                sc2 = new Scanner(str);
                while (sc2.hasNext()) {
                    System.out.print(resSpace + " ");
                    System.out.println(sc2.nextLine());
                }
            }
            this.isOpen = true;
        }

        void respondLine(String... respondStr) {
            respondLine(Arrays.asList(respondStr));
        }

        void respondLine(List<String> respondStr) {
            assert this.isOpen : "illegal usage of responder";
            Scanner sc2;
            for (String str : respondStr) {
                sc2 = new Scanner(str);
                while (sc2.hasNext()) {
                    System.out.print(resSpace + " ");
                    System.out.println(sc2.nextLine());
                }
            }
        }

        void over(String... remarks) {
            Scanner sc2;
            for (String str : remarks) {
                sc2 = new Scanner(str);
                while (sc2.hasNext()) {
                    System.out.print(resSpace + " ");
                    System.out.println(sc2.nextLine());
                }
            }
            System.out.print(resSpace);
            System.out.println(line);
            System.out.println();
            this.isOpen = false;
        }

        void respond(String... respondStr) {
            respond(Arrays.asList(respondStr));
        }

        void respond(List<String> respondStr) {
            start();
            respondLine(respondStr);
            over();
        }
    }

    static boolean isAddTaskCmd(String cmd) {
        return cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event");
    }

    static boolean addTask(String first, Scanner terms) {
        Task t = null;
        try {
            switch (first) {
            case "todo":
                t = new ToDoTask(terms.nextLine().trim());
                break;
            case "deadline":
                t = new DeadlineTask(terms.useDelimiter("/").next().trim(), terms.useDelimiter(" ").next().substring(1),
                        terms.nextLine());
                break;
            case "event":
                t = new EventTask(terms.useDelimiter("/").next().trim(), terms.useDelimiter(" ").next().substring(1),
                        terms.nextLine());
                break;
            default:
            }
        } catch (Exception e) {
            r.respond("☹ OOPS!!! The description of a " + first + " cannot be empty.");
            return true;
        }
        if (t != null) {
            mainObj.add(t);
            r.respond("Got it. I've added this task:", "  " + t.toString(),
                    "Now you have " + mainObj.count() + " tasks in the list.");
            mainObj.save();
            return true;
        } else {
            return false;
        }
    }

    static void listTask() {
        List<Task> lst = mainObj.getTaskList();
        r.start("Here are the tasks in your list:");
        r.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
        r.over();
    }

    static void markAsDone(int num) {
        mainObj.get(num - 1).setToDone();
        r.respond(Responder.taskDoneNote, mainObj.get(num - 1).toString());
    }

    static void remove(int num) {
        r.respond("Got it. I've added this task:", "  " + mainObj.remove(num - 1).toString(),
                    "Now you have " + mainObj.count() + " tasks in the list.");
    }

    static boolean isCommand(String cmd) {
        return cmd.length() > 0;
    }

    static void execCommand(String cmd) {
        Scanner terms = new Scanner(cmd);
        String first = terms.next();

        try {
            if (!addTask(first, terms)) {
                switch (first) {
                case "exit":
                    r.respond(Responder.bye);
                    System.exit(0);
                    break;
                case "list":
                    listTask();
                    break;
                case "done":
                    markAsDone(Integer.parseInt(terms.next()));
                    mainObj.save();
                    break;
                case "delete":
                    remove(Integer.parseInt(terms.next()));
                    mainObj.save();
                    break;
                default:
                    r.respond(Responder.dunno);
                }
            }
        } catch (Exception e) {
            r.respond(Responder.dunno);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r.respond(Responder.greetings);
        String cmd;
        while (isCommand(cmd = sc.nextLine())) {
            execCommand(cmd);
        }
        sc.close();
    }
}
