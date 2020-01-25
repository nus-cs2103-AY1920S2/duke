import java.util.Scanner;
import java.util.ArrayList;
import dukebot.Task;
import dukebot.InvalidTaskException;
import dukebot.TaskList;

/**
 * Main class.
 */
public class Duke {
    private static final String STORAGEPATH = "./dukeStore.txt";
    private static final String LOGO = "*******   **     ** **   ** ********\n"
            + "/**////** /**    /**/**  ** /**/////\n"
            + "/**    /**/**    /**/** **  /**\n"
            + "/**    /**/**    /**/****   /*******\n"
            + "/**    /**/**    /**/**/**  /**////\n"
            + "/**    ** /**    /**/**//** /**\n"
            + "/*******  //******* /** //**/********\n"
            + "///////    ///////  //   // //////// \n";
    private boolean sayFirst = true;

    private void run() {

        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList(STORAGEPATH);

        System.out.println("\nHi hi I'm \n" + LOGO);
        dukeSays("Master! Duke's so glad Master used Duke!");
        dukeSays("What will Master do today?");
        boolean running = true;
        while (running) {
            sayFirst = true;
            System.out.println("\nMaster: ");
            String inp = sc.nextLine();
            System.out.println();
            String[] inpArr = inp.split(" ");
            switch (inpArr[0]) {
            case "":
                dukeSays("I can't hear anything Master... Is Master all right?");
                break;
            case "Duke":
            case "duke":
            case "Master":
            case "master":
                dukeSays("Master!");
                break;
            case "bye":
                running = false;
                break;
            case "list":
                dukeSays("Master already forgotten what Master wanted to do?!");
                dukeSays("Duke has no choice but to remind Master then!");
                if (tasks.size() == 0) {
                    dukeSays("Huh there are no tasks! Master is so forgetful...");
                } else {
                    dukeSays("These are the tasks which Master forgot:");
                    sayTasks(tasks.taskList);
                }
                break;
            case "done":
                if (inpArr.length == 1) {
                    dukeSays("Duke doesn't think Master has done anything yet...");
                } else {
                    try {
                        int taskInd = Integer.parseInt(inpArr[1]) - 1;
                        if (taskInd >= tasks.size() || taskInd < 0) {
                            dukeSays("Duke can't seem to recall that item...");
                        } else {
                            if (tasks.getIsDone(taskInd)) {
                                dukeSays("Didn't Master already do that?");
                            } else {
                                dukeSays("So Master finally completed " + tasks.getTask(taskInd) + "?");
                                dukeSays("Duke's really proud of Master!");
                                tasks.setDone(taskInd);
                            }
                        }
                    } catch (NumberFormatException e) {
                        dukeSays("Stop teasing Duke... Even Duke knows that isn't a number...");
                    }
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    Task newTask = tasks.addNewTask(inpArr);
                    dukeSays("So Master has " + newTask.getType() + ": " + newTask + "...");
                } catch (InvalidTaskException e) {
                    dukeSays(e.getMessage());
                }
                break;
            case "delete":
                if (inpArr.length == 1) {
                    dukeSays("Master, please don't delete Duke...");
                } else {
                    try {
                        int taskInd = Integer.parseInt(inpArr[1]) - 1;
                        Task task = tasks.deleteTask(taskInd);
                        if (task == null) {
                            dukeSays("That item already doesn't exist in Duke's memory...");
                        } else {
                            dukeSays("For Master, Duke can forget anything, even the:");
                            dukeSays("[" + task.getType() + "] " + task + (task.getDone() ? " [Done!]" : ""));
                        }
                    } catch (NumberFormatException e) {
                        dukeSays("Stop teasing Duke... Even Duke knows that isn't a number...");
                    }
                }
                break;
            default:
                dukeSays("Duke doesn't understand Master...");
                break;
            }
        }
        dukeSays("Is Master leaving already?");
        dukeSays("Please come back and play with Duke soon...");
        System.exit(0);
    }

    //    private void dukeSays(String[] text) {
    //        boolean first = this.sayFirst;
    //        for (String line : text) {
    //            if (first) {
    //                System.out.print("Duke: ");
    //                first = false;
    //                this.sayFirst = false;
    //            } else {
    //                System.out.print("      ");
    //            }
    //            System.out.println(line);
    //        }
    //    }

    private void dukeSays(String line) {
        if (this.sayFirst) {
            System.out.print("Duke: ");
            this.sayFirst = false;
        } else {
            System.out.print("      ");
        }
        System.out.println(line);
    }

    private void sayTasks(ArrayList<Task> tasks) {
        int i = 1;
        for (Task task : tasks) {
            System.out.println("      "
                    + i + ". "
                    + "[" + task.getType() + "] "
                    + task
                    + (task.getDone() ? " [Done!]" : "")
            );
            i += 1;
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}