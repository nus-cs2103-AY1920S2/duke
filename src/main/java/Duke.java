import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
        ArrayList<Task> newList = input(arrList);
        //arrList - will be number of lines in the file
        //newList - updated list
        System.out.println("Original= " +arrList.size());
        System.out.println("New= " +newList.size());
        if (newList.size() > arrList.size()) {
            try {
                appendToFile(newList, arrList.size());
            } catch (IOException e) {
                System.out.println("Something went wrong: " +e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void appendToFile(ArrayList<Task> newList, int start) throws IOException {
        String s = "";
        FileWriter fw = new FileWriter("data\\duke.txt", true);
        for (int i = start; i < newList.size(); i++) {
            s += System.lineSeparator() + newList.get(i).getType() + " " + newList.get(i).getDone() + newList.get(i).getTask();
            if (i != newList.size()-1) {
                s = s + System.lineSeparator();
            }
        }
        fw.write(s);
        fw.close();
    }

    private static ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> arrList = new ArrayList<>();
        File f = new File("data\\duke.txt");
        Scanner s = new Scanner(f);
        Task t;
        boolean done;
        while (s.hasNext()) {
            String c = s.next();
            if (c.equals("T")) {
                done = (s.nextInt())==1;
                t = new Todo(s.nextLine());
                arrList.add(t);
            }
            else if (c.equals("E")) {
                done = (s.nextInt())==1;
                t = new Event(s.nextLine());
                arrList.add(t);
            }
            else {
                done = (s.nextInt())==1;
                t = new Deadline(s.nextLine());
                arrList.add(t);
            }
            if (done == true) {
                t.markAsDone();
            }
        }

        return arrList;
    }

    private static ArrayList<Task> input(ArrayList<Task> arrList) {
        Scanner sc;
        String taskType, task, date, word, statement;
        Task t;
        String taskArray[] = new String[2];
        ArrayList<Task> tempList = new ArrayList<Task>(arrList.size());

        int index = 0;
        for(Task tempTask : arrList) {
            tempList.add(arrList.get(index));
            index++;
        }

        sc = new Scanner(System.in);
        statement = sc.nextLine();

        while (!statement.equals("bye")) {
            //just list out the items
            if (statement.equals("list") && tempList.size() != 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i=1; i <= tempList.size(); i++) {
                    t = tempList.get(i-1);
                    System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.getTask());
                }
                System.out.println();
            }
            //if empty throw error
            else if (statement.equals("list")) {
                DukeException error = new DukeException();
                System.out.println(error.empty());
                System.out.println();
            }
            //any command that's not list
            else {
                //task array is each line from input file
                taskArray = statement.split(" ");
                if (taskArray.length ==1) {
                    DukeException error = new DukeException();
                    System.out.println(error.errorMsg(taskArray[0]));
                    System.out.println("");
                }
                else if (taskArray[0].equals("delete") || taskArray[0].equals("done")) {
                    int a = Integer.parseInt(taskArray[1]);
                    Action action = new Action(a, tempList);
                    if (action.checkNum() == 0) {
                        DukeException error = new DukeException();
                        System.out.println(error.outOfBound());
                        System.out.println("");
                    }
                    else {
                        if (taskArray[0].equals("delete")) {
                            Delete delete = new Delete(a, tempList);
                            String deleteAction = delete.printAction();
                            System.out.println(delete.deleteTask());
                            System.out.println(deleteAction);
                            System.out.println("Now you have " + tempList.size() + " tasks in the list.");
                            System.out.println("");
                        } else {
                            Done done = new Done(a, tempList);
                            System.out.println(done.markDone());
                            System.out.println(done.printAction());
                            System.out.println("Now you have " + tempList.size() + " tasks in the list.");
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
                    tempList.add(t);
//                    arrList.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + t.getType() + "][" + t.getStatusIcon() + "]" + t.getTask());
                    System.out.println("Now you have " + tempList.size() + " tasks in the list.");
                    System.out.println();
                }
            }
            statement = sc.nextLine();
        }
        return tempList;
    }

    private static type(String statement) {

    }
}