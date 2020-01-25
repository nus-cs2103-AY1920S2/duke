package dukebot.ui;

import dukebot.tasklist.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = "*******   **     ** **   ** ********\n"
            + "/**////** /**    /**/**  ** /**/////\n"
            + "/**    /**/**    /**/** **  /**\n"
            + "/**    /**/**    /**/****   /*******\n"
            + "/**    /**/**    /**/**/**  /**////\n"
            + "/**    ** /**    /**/**//** /**\n"
            + "/*******  //******* /** //**/********\n"
            + "///////    ///////  //   // //////// \n";
    private boolean sayFirst = true;
    private final Scanner sc;

    public Ui() {
        sc  = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("\nHi hi I'm \n" + LOGO);
        dukeSays("Master! Duke's so glad Master used Duke!");
        dukeSays("What will Master do today?");
    }

    public String readCommand() {
        sayFirst = true;
        System.out.println("\nMaster: ");
        String inp = sc.nextLine();
        System.out.println();
        return inp;
    }

    //    public void setSayFirst() {
    //        sayFirst = true;
    //    }

    public void sayLine(LineName lineName) {
        switch (lineName) {
        case HELP:
            dukeSays("todo <name> -- Adds a todo to your task list.");
            dukeSays("deadline <name> /by <time> -- Adds a dead line to your task list.");
            dukeSays("event <name> /at <time> -- Adds a event to your task list.");
            dukeSays("list -- Displays your task list.");
            dukeSays("done <task index> -- Marks the task as done.");
            dukeSays("delete <task index> -- Deletes the task.");
            dukeSays("bye -- Exits this application");
            break;
        case NO_INPUT:
            dukeSays("Duke can't hear anything Master... Is Master all right?");
            break;
        case SAY_DUKE:
            dukeSays("Master!");
            break;
        case LIST:
            dukeSays("Master already forgotten what Master wanted to do?!");
            dukeSays("Duke has no choice but to remind Master then!");
            break;
        case LIST_EMPTY:
            dukeSays("Huh there are no tasks! Master is so forgetful...");
            break;
        case LIST_EXISTS:
            dukeSays("These are the tasks which Master forgot:");
            break;
        case DONE_EMPTY:
            dukeSays("Duke doesn't think Master has done anything yet...");
            break;
        case DONE_OUT_OF_INDEX:
            dukeSays("Duke can't seem to recall that item...");
            break;
        case DONE_ALREADY:
            dukeSays("Didn't Master already do that?");
            break;
        case DONE_SUCCESS:
            // use another function;
            break;
        case NOT_A_NUMBER:
            dukeSays("Stop teasing Duke... Even Duke knows that isn't a number...");
            break;
        case DELETE_EMPTY:
            dukeSays("Master, please don't delete Duke...");
            break;
        case DELETE_OUT_OF_INDEX:
            dukeSays("That item already doesn't exist in Duke's memory...");
            break;
        case TODO_EMPTY:
            dukeSays("Duke doesn't see any description of the todo...");
            break;
        case DEADLINE_EMPTY:
            dukeSays("Duke doesn't see any deadline...");
            break;
        case DATE_TIME_PARSE_FAIL:
            dukeSays("Master gave a date that Duke cannot read...");
            break;
        case DEADLINE_BY_MISSING:
            dukeSays("Master, use '/by' to indicate deadline, Duke wouldn't know otherwise...");
            break;
        case EVENT_EMPTY:
            dukeSays("Duke doesn't see any start time...");
            break;
        case EVENT_AT_MISSING:
            dukeSays("Master, use '/at' to indicate starting time, Duke wouldn't know otherwise...");
            break;
        case INVALID_COMMAND:
            dukeSays("Duke doesn't understand Master...");
            break;
        case EXIT:
            dukeSays("Is Master leaving already?");
            dukeSays("Please come back and play with Duke soon...");
            break;
        case SAVE_FAIL:
            dukeSays("Duke feels dizzy...");
            dukeSays("It seems that Duke will forget everything when Master leaves...");
            break;
        case LOAD_FAIL:
            dukeSays("There does not seem to be any existing save file.");
            dukeSays("Is this the first time Master has ever used Duke?");
            dukeSays("Type 'help' to see the list of commands.");
            dukeSays("Thanks for using Duke. Duke is really happy.");
            break;
        case ERROR_PLACEHOLDER:
            // Purely for testing, should never be called in deployment
            // Fallthrough
        default:
            dukeSays("There is an unexpected error :(");
            break;
        }
    }

    public void sayTasks(ArrayList<Task> tasks) {
        dukeSays("These are the tasks which Master forgot:");
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

    public void doneSuccess(Task task) {
        dukeSays("So Master finally completed " + task + "?");
        dukeSays("Duke's really proud of Master!");
    }

    public void newTask(Task task) {
        dukeSays("So Master has " + task.getType() + ": " + task + "...");
    }

    public void deleteSuccess(Task task) {
        dukeSays("For Master, Duke can forget anything, even the:");
        dukeSays("[" + task.getType() + "] " + task + (task.getDone() ? " [Done!]" : ""));
    }

    public void dukeSays(String line) {
        if (this.sayFirst) {
            System.out.print("Duke: ");
            this.sayFirst = false;
        } else {
            System.out.print("      ");
        }
        System.out.println(line);
    }
}