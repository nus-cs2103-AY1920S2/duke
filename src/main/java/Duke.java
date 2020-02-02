import java.io.IOException;
import java.util.*;
import java.io.File;


public class Duke {
    public static void main(String[] args) throws IOException, Exception {
        Scanner sc = new Scanner(System.in);
        String mesInput = "";
        ArrayList<Task> arrTask = new ArrayList<Task>();
//        TaskList tList = new TaskList(arrTask);
        Ui uiDisplay = new Ui();
        Storage fileStorage = new Storage();
        String filePath = "duke.txt";
        File f = new File(filePath);

        while (!mesInput.equalsIgnoreCase("bye")) {
            mesInput = sc.nextLine();
            String[] s = new String[2];
            Parser userCommand = new Parser(mesInput, uiDisplay, fileStorage);
            if (mesInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userCommand.getCommandType(mesInput).equals("todo") && !mesInput.equalsIgnoreCase("todo")) {
                System.out.println(userCommand.todoTaskCommand(mesInput, arrTask, uiDisplay, f));
            } else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("list")) {
                TaskList tList = new TaskList(arrTask);
                tList.printTaskList();
            } else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("deadline") && !mesInput.equalsIgnoreCase("deadline")) {
                System.out.println(userCommand.deadlineCommand(mesInput, arrTask, uiDisplay, f));
            } else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("event") && !mesInput.equalsIgnoreCase("event")) {
                System.out.println(userCommand.eventCommand(mesInput, arrTask, uiDisplay, f));
            } else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("done") && !mesInput.equalsIgnoreCase("done")) {
                System.out.println(userCommand.doneCommand(mesInput, arrTask, uiDisplay));
            } else if (mesInput.contains("delete") && !mesInput.equalsIgnoreCase("delete")) {
                userCommand.deleteCommand(mesInput, arrTask, uiDisplay);
            } else if (userCommand.getCommandType(mesInput).equals("find")) {
                if (arrTask.size() > 0) {
                    userCommand.findCommand(mesInput, arrTask, uiDisplay);

                } else {
                    System.out.println("There is no task in the list");
                }

            } else if (mesInput.equalsIgnoreCase("todo")) {
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            } else if (mesInput.equalsIgnoreCase("deadline")) {
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a deadlines cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            } else if (mesInput.equalsIgnoreCase("event")) {
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a event cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(uiDisplay.returnExitsMessage());
    }
}

/**
 * Deadline subclass which extends from Task (parent class)
 */
class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return the deadline description and status icon
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() + "(" + by + ")";
    }
}

/**
 * Event subclass which extends from Task ( parent class )
 */
class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * @return the event description and its status icon
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription() + "(" + at + ")";
    }
}

