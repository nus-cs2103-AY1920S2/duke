import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * main class of Duke program
 * controlling the main logic
 */
public class Duke {

    public static final String LINE = "    ____________________________________________________________";
    public static String markAsDone = "     Nice! I've marked this task as done:";
    public static String showList = "     Here are the tasks in your list:";
    public static String gotIt = "     Got it. I've added this task:";
    public static String deleteIt = "     Noted. I've removed this task: ";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        ArrayList<Task> list = new ArrayList<>();

        String keyword = "";

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit();
            } else { //not bye
                String[] words = input.split(" ");
                keyword = words[0];
                if (keyword.equals("done")) { // case done
                    try{
                        if(words.length !=2){
                            throw new DukeException("The done message is not valid.");
                        }
                    } catch(DukeException exp){
                        System.out.println(exp.toString() + "\n");
                        continue;
                    }
                    int thisIndex = -1;
                    try{
                        thisIndex = Integer.valueOf(input.substring(5)) - 1;
                    } catch (Exception exp){
                        System.out.println(new DukeException ("Index is not detected.").toString() + "\n");
                        continue;
                    }

                    thisIndex = Integer.valueOf(input.substring(5)) - 1;
                    if(thisIndex >= list.size() || thisIndex < 0){
                        continue;
                    }
                    System.out.println(LINE);
                    System.out.println(markAsDone);
                    list.get(thisIndex).markAsDone();
                    System.out.println("       " + list.get(thisIndex).toString());
                    System.out.println(LINE + "\n");
                } else if (keyword.equals("delete")){ // case delete
                    try{
                        if(words.length !=2){
                            throw new DukeException("The delete message is not valid.");
                        }
                    } catch(DukeException exp){
                        System.out.println(exp.toString() + "\n");
                        continue;
                    }
                    int thatIndex = -1;
                    try{
                        thatIndex = Integer.valueOf(input.substring(7)) - 1;
                    } catch (Exception exp){
                        System.out.println(new DukeException ("Index is not detected.").toString() + "\n");
                        continue;
                    }

                    thatIndex = Integer.valueOf(input.substring(7)) - 1;
                    if(thatIndex >= list.size() || thatIndex < 0){
                        continue;
                    }
                    System.out.println(LINE);
                    System.out.println(deleteIt);
                    System.out.println("       " + list.get(thatIndex).toString());
                    list.remove(thatIndex);
                    System.out.println("     Now you have " + list.size() + " tasks in the list. ");
                    System.out.println(LINE + "\n");

                } else if (input.equalsIgnoreCase("list")) { //case list
                    displayList(list);
                } else { // case tasks
                    Task thisTask;
                    String[] stamps = input.split("/");

                    TaskEnum taskEnum = TaskEnum.OTHERS;
                    if(keyword.equals("todo") || keyword.equals("TODO") || keyword.equals("Todo")){
                        taskEnum = TaskEnum.TODO;
                    } else if (keyword.equals("event") || keyword.equals("EVENT") || keyword.equals("Event")) {
                        taskEnum = TaskEnum.EVENT;
                    } else if(keyword.equals("deadline") || keyword.equals("DEADLINE") || keyword.equals("Deadline")){
                        taskEnum = TaskEnum.DEADLINE;
                    }

                    switch(taskEnum){
                        case TODO:
                            try{
                                if(words.length <2){
                                    throw new DukeException("The description of a todo cannot be empty.");
                                }
                            } catch (DukeException exp){
                                System.out.println(exp.toString() + "\n");
                                continue;
                            }
                            thisTask = new Todo(input.substring(5));
                            break;

                        case EVENT:
                            try{
                                if(words.length < 4 || stamps.length < 2){
                                    throw new DukeException("The content of an event must be complete.");
                                }
                            } catch (DukeException exp){
                                System.out.println(exp.toString() + "\n");
                                continue;
                            }
                            thisTask = new Event(stamps[0].substring(6), stamps[1].substring(3));
                            break;

                        case DEADLINE:
                            try{
                                if(words.length < 4 || stamps.length < 2){
                                    throw new DukeException("The content of a deadline must be complete.");
                                }
                            } catch (DukeException exp){
                                System.out.println(exp.toString() + "\n");
                                continue;
                            }
                            thisTask = new Deadline(stamps[0].substring(9), stamps[1].substring(3));
                            break;

                        default:
                            try{
                                throw new DukeException ("I'm sorry, but I don't know what that means :-(");
                            } catch(DukeException exp){
                                System.out.println(exp.toString() + "\n");
                                continue;
                            }
                    }

                    addItem(list, thisTask);
                    echo(thisTask);
                    System.out.println("     Now you have " + list.size() + " tasks in the list.\n" + LINE + "\n");
                }
            }

        }
    }

    /**
     * This method greet the users at the beginning of the conversation.
     */
    public static void greet(){
        String firstGreet = LINE
                + "\n     Hello! I'm Duke \n"
                + "     What can I do for you? \n"
                + LINE;
        System.out.println(firstGreet + "\n");
    }

    /**
     * This method says goodbye to the user and quit the system.
     */
    public static void exit(){
        String directAnswer = LINE + "\n" + "     Bye. Hope to see you again soon!" + "\n" + LINE;
        System.out.println(directAnswer + "\n");
        System.exit(0);
    }

    /**
     * This method echos whatever the user inputs.
     */
    public static void echo(Task thisTask){
        System.out.println(LINE);
        System.out.println(gotIt);
        String directAnswer = "     " + thisTask.toString();
        System.out.println("  " + directAnswer);
    }

    /**
     * @param list
     * @param thisTask
     * This method add a new task object to the array list.
     */
    public static void addItem(ArrayList<Task> list, Task thisTask){
        list.add(thisTask);
    }

    /**
     * @param list
     * This method displays the list of tasks.
     */
    public static void displayList(ArrayList<Task> list){
        System.out.println(LINE);
        System.out.println(showList);
        int marker = 1;
        if(list.isEmpty()){
            System.out.println(LINE + "\n");
        } else {
            for(int i = 0; i < list.size(); i++){
                System.out.println("     " + (i + 1) + ". " + list.get(i).toString());
            }
            System.out.println(LINE + "\n");
        }
    }
}
