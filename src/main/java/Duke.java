import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> arrList = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        try {
            arrList = loadData();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        input(arrList);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> arrList = new ArrayList<>();
        File f = new File("data\\duke.txt");
        Scanner s = new Scanner(f);
        Task t;
        int done;
        while (s.hasNext()) {
            String c = s.next();
            if (c.equals("T")) {
                done = s.nextInt();
                t = new Todo(s.nextLine());
                arrList.add(t);
            }
            else if (c.equals("E")) {
                done = s.nextInt();
                t = new Event(s.nextLine());
                arrList.add(t);
            }
            else {
                done = s.nextInt();
                t = new Deadline(s.nextLine());
                arrList.add(t);
            }
            if (done == 1) {
                t.markAsDone();
            }
        }

        return arrList;
    }

    private static void input(ArrayList<Task> arrList) {
        Scanner sc;
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
                    System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.getTask());
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
                else if (taskArray[0].equals("delete") || taskArray[0].equals("done")) {
                    int a = Integer.parseInt(taskArray[1]);
                    Action action = new Action(a, arrList);
                    if (action.checkNum() == 0) {
                        DukeException error = new DukeException();
                        System.out.println(error.outOfBound());
                        System.out.println("");
                    }
                    else {
                        if (taskArray[0].equals("delete")) {
                            Delete delete = new Delete(a, arrList);
                            String deleteAction = delete.printAction();
                            System.out.println(delete.deleteTask());
                            System.out.println(deleteAction);
                            System.out.println("Now you have " + arrList.size() + " tasks in the list.");
                            System.out.println("");
                        } else {
                            Done done = new Done(a, arrList);
                            System.out.println(done.markDone());
                            System.out.println(done.printAction());
                            System.out.println("Now you have " + arrList.size() + " tasks in the list.");
                            System.out.println("");
                        }
                    }
                }
                else {
                    taskType = taskArray[0];
                    statement = statement.substring(statement.indexOf(" "), statement.length());
                    if (!taskType.equals("todo")) {
                        if (taskType.equals("event")) {
                            t = new Event(statement);
                        } else {
                            t = new Deadline(statement);
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
            statement = sc.nextLine();
        }
    }
}