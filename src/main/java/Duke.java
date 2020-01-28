import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);
        processInput();
    }

    static LocalDateTime getDate(String next) {
        StringBuilder forTime = new StringBuilder(next.substring(next.indexOf(" ", next.indexOf(" ") +1) + 1));
        forTime.insert(2, ':');
        String time = forTime.toString();
        //System.out.println(time);
        String[] reverse = next.substring(next.indexOf(" ") + 1, next.indexOf(" ", next.indexOf(" ") +1))
            .split("/");
        String[] reversed = reverse;
        String temp = String.format("%2s",reverse[0]).replace(" ", "0");
        reversed[0] = String.format("%4s",reverse[2]).replace(" ", "0");
        reversed[2] = temp;
        reversed[1] = String.format("%2s", reverse[1]).replace(" ", "0");
        String date = String.join("-", reversed);
        LocalDateTime taskDate = LocalDate.parse(date)
                .atTime(LocalTime.parse(time))
                ;
        return taskDate;
    }

    static void processInput() {
        String line = "   ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String next = sc.nextLine();
        while (!next.trim().equals("bye")) {
            System.out.println(line);
            try {
                if (next.trim().equals("list")) {
                    printList(tasks);
                } else if ((next.trim().split(" ")[0]).equals("done")) {
                    try {
                        Integer taskNumber = Integer.valueOf((next.split(" ")[1]));
                        Task updatedTask = tasks.get(taskNumber - 1).setDone();
                        tasks.set(taskNumber - 1, updatedTask);
                        printDone(updatedTask);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
                    }
                } else if (next.trim().split(" ")[0].equals("delete")) {
                    try {
                        Integer taskNumber = Integer.valueOf((next.split(" ")[1]));
                        Task removedTask = tasks.get(taskNumber - 1);
                        tasks.remove(removedTask);
                        printRemove(removedTask, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException(" ☹ OOPS!!! The description of a delete cannot be empty.");
                    }
                } else {
                    Task newTask;
                    String taskName;
                    String instruction = next.trim().split(" ")[0];
                    if (next.trim().split(" ")[0].equals("todo")) {
                        try {
                            taskName = next.trim().substring(5);
                            newTask = new ToDo(taskName);
                            tasks.add(newTask);
                            printTask(newTask, tasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (next.trim().split(" ")[0].equals("deadline")) {
                        try {
                            taskName = next.substring(next.indexOf(" ") + 1, next.indexOf("/"));
                            LocalDateTime time = getDate(next.substring(next.indexOf("/") + 1));
                            String taskDate = time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:ss a"));
                            newTask = new Deadline(taskName, taskDate);
                            tasks.add(newTask);
                            printTask(newTask, tasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                    } else if (next.trim().split(" ")[0].equals("event")) {
                        try {
                            taskName = next.substring(next.indexOf(" ") + 1, next.indexOf("/"));
                            LocalDateTime time = getDate(next.substring(next.indexOf("/") + 1));
                            String taskDate = time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:ss a"));
                            newTask = new Event(taskName, taskDate);
                            tasks.add(newTask);
                            printTask(newTask, tasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
                        }
                    } else {
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException de) {
                System.out.println(de);
            }
            System.out.println(line);
            next = sc.nextLine();
        }
        printBye();
    }

    static void printTask(Task newTask, int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    static void printList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        int i = 1;
        for (Task s : tasks) {
            System.out.println("\t" + i + "." + s.toString());
            i++;
        }
    }

    static void printDone(Task updatedTask) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + updatedTask);
    }

    static void printRemove(Task removedTask, int size) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + removedTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    static void printBye() {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

}
