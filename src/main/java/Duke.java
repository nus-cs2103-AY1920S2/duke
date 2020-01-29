import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Scanner sc;
    private ArrayList<Task> tasks;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<>(100);
    }

    private void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    private void echo(String input) {
        System.out.println("I'm sorry, but I don't know what that means!");
        this.waitInput();
    }

    private void printTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
        this.waitInput();
    }

    private void waitInput() {
        String input = sc.nextLine();
        String[] cmd = input.split("\\s", 2);
        switch (cmd[0].toLowerCase()) {
        case "todo":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a todo cannot be empty.");
                this.waitInput();
            } else {
                Task newTask = new Todo(cmd[1].trim());
                this.addTask(newTask);
            }
            break;
        case "event":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a event cannot be empty.");
                this.waitInput();
            } else {
                String[] info = cmd[1].split("/at");
                if (info.length < 2 || info[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /at Date Time (e.g. /at Mon 2-4pm)");
                    this.waitInput();
                } else {
                    String timeEvent = convertDateAndTime(info[1].trim());
                    Task newTask = new Event(info[0].trim(), timeEvent);
                    this.addTask(newTask);
                }
            }
            break;
        case "deadline":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a deadline cannot be empty.");
                this.waitInput();
            } else {
                String[] info = cmd[1].split("/by");
                if (info.length < 2 || info[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /by Date (e.g. /by Feb 3rd)");
                    this.waitInput();
                } else {
                    String timeDeadline = convertDateAndTime(info[1].trim());
                    Task newTask = new Deadline(info[0].trim(), timeDeadline);
                    this.addTask(newTask);
                }
            }
            break;
        case "delete":
            deleteTask(cmd[1]);
            break;
        case "list":
            printTasks();
            break;
        case "done":
            markAsDone(cmd[1]);
            break;
        case "bye":
            System.out.println("\tBye. Hope to see you again soon!");
            break;
        default:
            echo(input);
            break;
        }
    }

    private void markAsDone(String index) {
        int num = Integer.parseInt(index.trim());
        if (num < 1 || num > this.tasks.size()) {
            System.out.println("\tThe index inputted is not in the list!");
        } else {
            Task toBeDone = this.tasks.get(num - 1);
            toBeDone.toggleIsDone();
            System.out.println("\tGood job! You have completed this task!");
            System.out.println("\t\t" + toBeDone);
        }
        this.waitInput();
    }

    private void addTask(Task task) {
        System.out.println("\tGot it! I've added this task:");
        this.tasks.add(task);
        System.out.println("\t\t" + task);
        System.out.println("\tYou have total of " + this.tasks.size() + " tasks in the list.");
        this.waitInput();
    }

    private void deleteTask(String index) {
        int num = Integer.parseInt(index.trim());
        if (num < 1 || num > this.tasks.size()) {
            System.out.println("\tThe index inputted is not in the list!");
        } else {
            Task deleted = this.tasks.get(num - 1);
            System.out.println("\tOkay! I've removed this task!");
            System.out.println("\t\t" + deleted);
            this.tasks.remove(num - 1);
            System.out.println("\tYou have total of " + this.tasks.size() + " tasks in the list.");
        }
        this.waitInput();
    }

    private String convertDateAndTime(String time) {
        String[] arr = time.split("\\s");
        String day = "";
        if (arr.length == 2) {
            String[] dateArray = arr[0].split("/");
            if (dateArray.length == 3) {
                if (dateArray[0].equals("1") || dateArray[0].equals("21") || dateArray[0].equals("31")) {
                    day = dateArray[0] + "st";
                } else if ((dateArray[0].equals("2") || dateArray[0].equals("22"))) {
                    day = dateArray[0] + "nd";
                } else if ((dateArray[0].equals("3") || dateArray[0].equals("23"))) {
                    day = dateArray[0] + "rd";
                } else {
                    day = dateArray[0] + "th";
                }
                switch (dateArray[1]) {
                case "1":
                    day += " of January";
                    break;
                case "2":
                    day += " of February ";
                    break;
                case "3":
                    day += " of March ";
                    break;
                case "4":
                    day += " of April ";
                    break;
                case "5":
                    day += " of May ";
                    break;
                case "6":
                    day +=" of June ";
                    break;
                case "7":
                    day += " of July ";
                    break;
                case "8":
                    day += " of August ";
                    break;
                case "9":
                    day += " of September ";
                    break;
                case "10":
                    day += " of October ";
                    break;
                case "11":
                    day += " of November ";
                    break;
                case "12":
                    day += " of December ";
                    break;
                }
                day += dateArray[2] + ", ";
                try {
                    String t = arr[1];
                    String[] timeArr = t.split("-");
                    if (timeArr.length == 1) {
                        SimpleDateFormat input = new SimpleDateFormat("HHmm");
                        SimpleDateFormat output = new SimpleDateFormat("hh:mmaa");
                        Date date = input.parse(t);
                        String outputStr = output.format(date);
                        if (outputStr.charAt(0) == '0') {
                            day += outputStr.substring(1).toLowerCase();
                        } else {
                            day += outputStr.toLowerCase();
                        }
                    } else if (timeArr.length == 2) {
                        SimpleDateFormat input = new SimpleDateFormat("HHmm");
                        SimpleDateFormat output = new SimpleDateFormat("hh:mmaa");
                        Date start = input.parse(timeArr[0]);
                        Date end = input.parse(timeArr[1]);
                        String startTime = output.format(start).toLowerCase();
                        String endTime = output.format(end).toLowerCase();
                        if (startTime.charAt(0) == '0') {
                            startTime = startTime.substring(1);
                        }
                        if (endTime.charAt(0) == '0') {
                            endTime = endTime.substring(1);
                        }
                        day += startTime + " to " + endTime;
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                return time;
            }
        } else {
            return time;
        }
        return day;
    }

    public void run() {
        this.intro();
        this.waitInput();
    }
}