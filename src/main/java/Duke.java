import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    private static final String logo = "*******   **     ** **   ** ********\n"
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
        boolean running = true;
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("\nHi hi I'm \n" + logo);
        dukeSays(new String[] {"Master! I'm so glad you used me!", "What will you do today?"});
        while (running) {
            System.out.println("\nMaster: ");
            String inp = sc.nextLine();
            System.out.println();
            String[] inpArr = inp.split(" ");
            switch (inpArr[0]) {
            case "":
                dukeSays("I can't hear anything Master... Is Master all right?");
                break;
            case "bye":
                running = false;
                break;
            case "list":
                dukeSays(new String[] {"Master already forgotten what Master wanted to do?!",
                    "Duke has no choice but to remind Master then!"});
                sayFirst = false;
                if (tasks.size() == 0) {
                    dukeSays("Huh there are no tasks! Master is so forgetful...");
                } else {
                    dukeSays("These are the tasks which Master forgot:");
                    sayTasks(tasks);
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
                            Task task = tasks.get(taskInd);
                            if (task.getDone()) {
                                dukeSays("Didn't Master already do that?");
                            } else {
                                dukeSays(new String[] {"So master finally completed " + task + "?",
                                        "Duke's really proud of Master!"
                                });
                                task.setDone();
                            }
                        }
                    } catch (NumberFormatException e) {
                        dukeSays("Stop teasing Duke... Even Duke knows that isn't a number...");
                    }
                }
                break;
            default:
                dukeSays("So Master wants to " + inp + "...");
                tasks.add(new Task(inp));
            }
        }
        dukeSays(new String[] {"Are you leaving already?", "Please come back and play with Duke soon..."});
    }

    private void dukeSays(String[] text) {
        boolean first = this.sayFirst;
        for (String line : text) {
            if (first) {
                System.out.print("Duke: ");
                first = false;
            } else {
                System.out.print("      ");
            }
            System.out.println(line);
        }
        this.sayFirst = true;
    }

    private void dukeSays(String line) {
        dukeSays(new String[] {line});
    }

    private void sayTasks(ArrayList<Task> tasks) {
        int i = 1;
        for (Task task : tasks) {
            System.out.println("      "
                + i + ". "
                + task
                + (task.getDone() ? " (Done!)" : "")
            );
            i += 1;
        }
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}
