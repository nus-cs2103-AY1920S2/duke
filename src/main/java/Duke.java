import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        input();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void input() {
        Scanner sc;
        ArrayList<Task> arrList = new ArrayList<>();
        String taskType, task, date, word, statement;
        Task t;
        String taskArray[] = new String[2];

        sc = new Scanner(System.in);
        statement = sc.nextLine();

        while (!statement.equals("bye")) {
            if (statement.equals("list") && arrList.size() != 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i=1; i <= arrList.size(); i++) {
                    t = arrList.get(i-1);
                    System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.description);
                }
                System.out.println();
            }
            else if (statement.equals("list")) {
                DukeException error = new DukeException();
                System.out.println(error.empty());
                System.out.println();
            }
            else {
                taskArray = statement.split(" ");
                if (taskArray.length ==1) {
                    DukeException error = new DukeException();
                    System.out.println(error.errorMsg(taskArray[0]));
                    System.out.println("");
                }
                else {
                    taskType = taskArray[0];
                    if ((statement.split(" ")[0]).equals("done")) {
                        int a = Integer.parseInt(statement.split(" ")[1]) - 1;
                        if (a <= arrList.size() && a!=-1) {
                            arrList.get(a).markAsDone();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println("  [" + arrList.get(a).getStatusIcon() + "] " + arrList.get(a).description);
                            System.out.println();
                        }
                        else {
                            DukeException error = new DukeException();
                            System.out.println(error.outOfBound());
                            System.out.println();
                        }
                    }
                    else {
                        statement = statement.substring(statement.indexOf(" "), statement.length());
                        if (!taskType.equals("todo")) {
                            task = statement.split("/")[0];
                            date = statement.split("/")[1];
                            word = date.substring(0, date.indexOf(" "));
                            date = date.substring(date.indexOf(" ") + 1, date.length());
                            if (task.equals("event")) {
                                t = new Event(task, word, date);
                            } else {
                                t = new Deadline(task, word, date);
                            }
                        } else {
                            t = new Todo(statement);
                        }
                        arrList.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  [" + t.getType() + "][" + t.getStatusIcon() + "]" + t.getTask());
                        System.out.println("Now you have " + arrList.size() + " tasks in the list.");
                        System.out.println();
                    }
                }
            }
            sc = new Scanner(System.in);
            statement = sc.nextLine();
        }
    }
}