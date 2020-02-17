package dukebot.ui;

import dukebot.contactlist.ContactDetail;
import dukebot.gui.DukeExpression;
import dukebot.gui.DukeVoice;
import dukebot.tasklist.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles output to use.
 */
public class Ui {
    private static final String LOGO = " ____, __   _, __  _, ____,\n"
            + "(-|  \\(-|  |  (-|_/  (-|_, \n"
            + " _|__/  |__|_, _| \\_, _|__,\n"
            + "(             (      (    \n";
    private final Scanner sc;
    private final boolean hasGui;
    private boolean sayFirst = true;
    private StringBuilder guiOutput = new StringBuilder();
    private DukeExpression dukeExpression = DukeExpression.HAPPY;
    private boolean hasVoice = true;
    private DukeVoice dukeVoice = DukeVoice.NO_VOICE;

    /**
     * Generates the Ui with GUI.
     */
    public Ui(boolean hasGui) {
        this.hasGui = hasGui;
        sc = new Scanner(System.in);
    }

    /**
     * Generates the Ui.
     */
    public Ui() {
        hasGui = false;
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("\nYahallo! Duke's name is \n" + LOGO);
        dukeSays("Duke's so glad Master used Duke!");
        dukeSays("What will Master do to Duke today?");
    }

    /**
     * Prints welcome message for GUI.
     */
    public void showWelcomeGui() {
        dukeVoice = DukeVoice.HELLO;
        dukeSays("Yahallo! Duke's name is \n" + LOGO);
        dukeSays("Duke's so glad Master used Duke!");
        dukeSays("What will Master do to Duke today?");
    }

    /**
     * Prompts and reads the input given.
     *
     * @return The read input.
     */
    public String readCommand() {
        sayFirst = true;
        System.out.println("\nMaster: ");
        String inp = sc.nextLine();
        System.out.println();
        return inp;
    }

    /**
     * Prints lines based on lineName.
     *
     * @param lineName Line to say.
     */
    public void sayLine(LineName lineName) {
        dukeExpression = DukeExpression.HAPPY;
        dukeVoice = DukeVoice.WHAT;
        switch (lineName) {
        case ADD_CONTACT_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.HEY, DukeVoice.ACTUALLY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Duke doesn't understand the contact which Master wishes to add...");
            dukeSays("To add a new contact, use:");
            dukeSays("add_contact <name> number");
            break;
        case ALIAS_ALREADY_EXISTS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.HEY, DukeVoice.ACTUALLY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("That command already exists... Duke can't replace it...");
            break;
        case ALIAS_COMMAND_FAIL:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.WHAT);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Duke doesn't know what should replace what...");
            dukeSays("The command for new alias is: alias <old> <new>");
            break;
        case ALIAS_DOES_NOT_EXIST:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.HEY, DukeVoice.WHAT);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Huh? Duke doesn't know what command Master is talking about...");
            break;
        case HELP:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.HEY, DukeVoice.WHAT_YOU_LIKE);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Master wants to know more about Duke?");
            dukeSays("Exposing myself to master... Duke's so embarrassed...");
            dukeSays("But if it's for Master... Duke'll do it!");
            dukeSays("Duke's precious secrets:\n\n"
                    + "alias <old> <new>\n-- Changes the name of a command.\n\n"
                    + "todo <name>\n-- Adds a todo to your task list.\n\n"
                    + "deadline <name> /by <time>\n-- Adds a deadline to your task list.\n\n"
                    + "event <name> /at <time>\n -- Adds a event to your task list.\n\n"
                    + "list\n-- Displays your task list.\n\n"
                    + "find <query>\n-- Finds a task which has <query> as a substring of the task's name.\n\n"
                    + "done <task index>\n-- Marks the task as done.\n\n"
                    + "delete <task index>\n-- Deletes the task.\n\n"
                    + "reschedule <task index> <time>\n-- Reschedules the task.\n\n"
                    + "add_contact <name> <number>\n-- Adds a new contact.\n\n"
                    + "contacts \n-- Displays your contact list.\n\n"
                    + "delete_contact <task index>\n-- Deletes the contact.\n\n"
                    + "bye\n-- Exits this application.\n\n"
                    + "credits\n-- See credits for the resources which went into Duke!\n\n"
                    + "reset\n-- The forbidden Command. Please never use it... Duke'll forget everything."
            );
            break;
        case NO_INPUT:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.WHAT, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke can't hear anything... Is Master all right?");
            break;
        case SAY_DUKE:
            dukeSays("Master!");
            break;
        case CONTACT_LIST_EMPTY:
            dukeExpression = DukeExpression.BLUSH;
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeSays("Duke doesn't mind that Master doesn't have any friends...");
            dukeSays("Duke'll be with Master forever...");
            break;
        case CONTACT_LIST_EXIST:
            dukeExpression = DukeExpression.SURPRISED;
            dukeVoice = DukeVoice.OKAY;
            dukeSays("Master already has Duke right? Master doesn't need anyone else!");
            dukeSays("But if Master really needs it... Duke cant deny it...\n");
            break;
        case LIST_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeSays("Huh there are no tasks! Master is so forgetful...");
            break;
        case LIST_EXISTS:
            dukeExpression = DukeExpression.SURPRISED;
            dukeSays("Master already forgotten what Master wanted to do?!");
            dukeSays("Duke has no choice but to remind Master then!");
            dukeSays("These are the tasks which Master forgot:");
            break;
        case DEFAULT_OUT_OF_INDEX:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke can't seem to recall that item...");
            break;
        case DONE_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.WHAT, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't think Master has done anything yet...");
            break;
        case DONE_ALREADY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.SURPRISED;
            dukeSays("Didn't Master already do that?");
            break;
        case NOT_A_NUMBER:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.WHAT, DukeVoice.THING_YOURE_INTO);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Stop teasing Duke... Even Duke knows that isn't a number...");
            break;
        case DELETE_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master, please don't delete Duke...");
            break;
        case DELETE_OUT_OF_INDEX:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("That item already doesn't exist in Duke's memory...");
            break;
        case TODO_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't see any description of the todo...");
            break;
        case DEADLINE_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't see any deadline...");
            break;
        case DATE_TIME_PARSE_FAIL:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.THING_YOURE_INTO, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master gave a date that Duke cannot read...");
            break;
        case DEADLINE_BY_MISSING:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Master, use '/by' to indicate deadline, Duke wouldn't know otherwise...");
            break;
        case EVENT_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't see any start time...");
            break;
        case EVENT_AT_MISSING:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Master, use '/at' to indicate starting time, Duke wouldn't know otherwise...");
            break;
        case FIND_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("There's nothing for Duke to find...");
            break;
        case FIND_FAIL:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.ACTUALLY, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("There isn't any tasks which matches Master's queries.");
            break;
        case FIND_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.LAUGHTER, DukeVoice.HEY);
            dukeSays("Master! Duke found all these tasks!");
            break;
        case INVALID_COMMAND:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.WHAT, DukeVoice.OKAY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't understand Master...");
            break;
        case EXIT:
            dukeVoice = DukeVoice.GOODBYE;
            dukeExpression = DukeExpression.SAD;
            dukeSays("Is Master leaving already?");
            dukeSays("Please come back and play with Duke soon...");
            break;
        case SAVE_FAIL:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke feels dizzy...");
            dukeSays("It seems that Duke will forget everything when Master leaves...");
            break;
        case LOAD_FAIL:
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Yahallo! Duke's name is \n" + LOGO);
            dukeSays("Is this the first time Master has used Duke?\nType 'help' for the list of commands.");
            break;
        case RESCHEDULE_EMPTY:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.WHAT, DukeVoice.HEY);
            dukeExpression = DukeExpression.SAD;
            dukeSays("What exactly does Master want Duke to reschedule?");
            dukeSays("Master has to say the magic command:");
            dukeSays("Reschedule <task index> <time>");
            break;
        case RESET_STORAGE_INIT:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master wants to wipe Duke's memories? Please don't say yes...");
            break;
        case RESET_STORAGE_FAIL:
            dukeVoice = DukeVoice.LAUGHTER;
            dukeSays("Duke'll pretend Master didn't say that.");
            break;
        case RESET_STORAGE_SUCCESS:
            dukeVoice = DukeVoice.GOODBYE;
            dukeExpression = DukeExpression.SAD;
            dukeSays("It was nice knowing Master... Duke'll go somewhere far away now...");
            break;
        case CREDITS:
            dukeVoice = DukeVoice.LAUGHTER;
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Duke would like to thank the many people who made Duke possible!\n");
            dukeSays("Author:\nhttps://github.com/gerhean/duke\n\n"
                    + "Background:\nhttps://wallpapersafari.com/w/SQfFKk\n\n"
                    + "Duke Images:\nhttps://liah0227.itch.io/female-student-1\n\n"
                    + "User Image:\nhttps://www.uihere.com/free-cliparts/koyomi-araragi-counter-"
                    + "strike-nexon-zombies-monogatari-series-anime-others-6828824\n\n"
                    + "Duke Voice:\nhttps://cicifyre.itch.io/free-voice-clips-pack-bright-female");
            break;
        case ERROR_PLACEHOLDER:
            // Purely for testing, should never be called in deployment
            // Fallthrough
        default:
            // Purely for testing, should never be called in deployment
            dukeSays("There is an unexpected error :(");
            assert false;
            break;
        }
    }

    /**
     * Prints array of tasks.
     *
     * @param tasks The array of tasks to print.
     */
    public void sayTasks(ArrayList<Task> tasks) {
        int i = 1;
        for (Task task : tasks) {
            dukeSays(i + ". "
                    + "[" + task.getType() + "] "
                    + task
                    + (task.getDone() ? " [Done!]" : "")
            );
            i += 1;
        }
    }

    /**
     * Prints array of tasks.
     *
     * @param contactList The array of contacts to print.
     */
    public void sayContacts(ArrayList<ContactDetail> contactList) {
        int i = 1;
        for (ContactDetail contact : contactList) {
            dukeSays(i + ". "
                    + contact.getName()
                    + " [" + contact.getPhoneNumber() + "] "
            );
            i += 1;
        }
    }

    /**
     * Prints message based on LineName with task information.
     *
     * @param lineName Line to say.
     * @param task     Task to use.
     */
    public void sayLineWithTask(LineNameWithTask lineName, Task task) {
        switch (lineName) {
        case DELETE_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.THING_YOURE_INTO,
                    DukeVoice.WHAT_YOU_LIKE, DukeVoice.OKAY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("For Master, Duke can forget anything, even the:");
            dukeSays("[" + task.getType() + "] " + task + (task.getDone() ? " [Done!]" : ""));
            break;
        case DONE_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.LAUGHTER, DukeVoice.OKAY);
            dukeExpression = DukeExpression.HAPPY;
            dukeSays("So Master finally completed " + task + "?");
            dukeSays("Duke's really proud of Master!");
            break;
        case NEW_TASK_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.THING_YOURE_INTO,
                    DukeVoice.WHAT_YOU_LIKE, DukeVoice.OKAY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("So Master has " + task.getType() + ":\n" + task + "...");
            break;
        case RESCHEDULE_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.LAUGHTER, DukeVoice.OKAY);
            dukeExpression = DukeExpression.HAPPY;
            dukeSays("Duke has rescheduled task:\n" + task.getDescription());
            dukeSays("to be at:\n" + task.dateTimeToString());
            dukeSays("Try not to forget it Master!");
            break;
        case RESCHEDULE_BAD_TASK:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.HEY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Is Master teasing Duke?");
            dukeSays("The task: " + task.getDescription());
            dukeSays("is a: " + task.getType());
            dukeSays("which does not have a scheduled time.");
            break;
        case ERROR_PLACEHOLDER:
            // Purely for testing, should never be called in deployment
            // Fallthrough
        default:
            // Purely for testing, should never be called in deployment
            dukeSays("There is an unexpected error :(");
            assert false;
            break;
        }
    }

    /**
     * Prints message based on LineName with contact information.
     *
     * @param lineName Line to say.
     * @param contact  Contact to use.
     */
    public void sayLineWithContact(LineNameWithContact lineName, ContactDetail contact) {
        switch (lineName) {
        case DELETE_CONTACT_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.THING_YOURE_INTO,
                    DukeVoice.WHAT_YOU_LIKE, DukeVoice.OKAY);
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("So Master had a falling out with " + contact.getName() + "?");
            dukeSays("Will Master spend more time with Duke now?");
            break;
        case ADD_CONTACT_SUCCESS:
            dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.LAUGHTER, DukeVoice.OKAY);
            dukeExpression = DukeExpression.HAPPY;
            dukeSays("Duke has successfully added:");
            dukeSays(contact.getName());
            dukeSays("with the number:");
            dukeSays(Integer.toString(contact.getPhoneNumber()));
            dukeSays("into the contact list");
            break;
        case ERROR_PLACEHOLDER:
            // Purely for testing, should never be called in deployment
            // Fallthrough
        default:
            // Purely for testing, should never be called in deployment
            assert false;
            dukeSays("There is an unexpected error :(");
            break;
        }
    }

    /**
     * Prints task successfully deleted message.
     */
    public void aliasSuccess(String oldAlias, String newAlias) {
        dukeVoice = DukeVoice.randomVoice(hasVoice, DukeVoice.THING_YOURE_INTO,
                DukeVoice.WHAT_YOU_LIKE, DukeVoice.LAUGHTER);
        dukeExpression = DukeExpression.BLUSH;
        dukeSays("Duke has successfully changed the name of <" + oldAlias + "> into <" + newAlias + ">!");
    }

    /**
     * Formats and prints the string input.
     *
     * @param line Line to print.
     */
    private void dukeSays(String line) {
        if (hasGui) {
            guiOutput.append(line);
            guiOutput.append("\n");
        } else {
            if (this.sayFirst) {
                System.out.print("Duke: ");
                this.sayFirst = false;
            } else {
                System.out.print("      ");
            }
            System.out.println(line);
        }
    }

    /**
     * Resets text output.
     */
    public void resetGuiOutput() {
        if (hasGui) {
            guiOutput = new StringBuilder();
        }
    }

    /**
     * Gets text output for GUI.
     */
    public String getGuiOutput() {
        if (hasGui) {
            return guiOutput.toString();
        } else {
            return "";
        }
    }

    /**
     * Gets Duke's expression.
     */
    public DukeExpression getDukeExpression() {
        return dukeExpression;
    }

    /**
     * Get's Duke's voice.
     */
    public DukeVoice getDukeVoice() {
        if (hasVoice) {
            return dukeVoice;
        } else {
            return DukeVoice.NO_VOICE;
        }
    }
}