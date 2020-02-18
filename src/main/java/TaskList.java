import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.IOException;

/**
 * Represents a TaskList which contains functions which handles adding,
 * removing or listing out tasks as depicted by commands from the
 * user.
 */

public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;
    private int latest_index;
    private Storage storage;
    private StringBuilder sb;

    public TaskList(ArrayList<Task> list, int latest_index, Storage storage) {
        this.list = list;
        this.ui = new Ui();
        this.latest_index = latest_index;
        this.storage = storage;
    }

    public int getLatest_index() {

        assert latest_index < Integer.MAX_VALUE : "Too many tasks!";
        return latest_index;
    }

    /**
     * Updates the Index of each task whenever a task is deleted.
     */
    public void updateIndex() {
        int count = 1;
        for (Task task : list) {
            task.set_Index(count++);
        }
    }

    /**
     * Deletes the task at the position that is inputted or deletes all tasks if 'delete all' in inputted. Also
     * informs the user if there is no such position in the list or if the input is not a number.
     * @param input the input, normally a number, that is entered.
     * @return the reply message to be sent back to the user.
     */
    public String delete(String input) {
        assert input != null : "Delete command input is empty!";

        sb = new StringBuilder();
        input = input.replace("delete","");
        input = input.trim();

        if (input.equals("all")) {
            list.clear();
            latest_index = 0;
            return ui.deleteAll();
        }

        try {
            int i = Integer.parseInt(input) - 1;

            if (!list.get(i).isDone) {
                sb.append(ui.unfinishedTask() + list.get(i).toString() + "\n");
            } else {
                sb.append(ui.finishedTask() + list.get(i).toString() + "\n");
            }

            list.remove(i);
            updateIndex();
            latest_index--;

            sb.append("\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (IndexOutOfBoundsException e) {
            sb.append(ui.nosuchNumber());

        } catch (NumberFormatException e) {
            sb.append(ui.inputNumber());
        }
        return sb.toString();
    }


    /**
     * Produces a Todo class object which is added into the list. The user will also be informed of the
     * Todo object created. Should the 'todo' command be given more than once, the user will be informed
     * and a Todo class object will not be produced.
     *
     * @param input the description of the todo task that is inputted by the user
     * @return the reply message to be sent back to the user
     */
    public String todo(String input) {
        assert input != null : "Todo command input is empty!";

        int repeat = checkRepeats(input,"todo");

        if (repeat > 1) {
            return ui.oneCommand();
        }
        input = input.replace("todo", "");
        input = input.trim();

        if (checkEmpty(input)) {
            return ui.emptyCmd();
        }
        Todo todo = new Todo(input, ++latest_index);
        list.add(todo);
        return "Got it. I have added this task:\n" + todo.toString() +
                "\nNow you have a total of " + latest_index + " Tasks in your list";

    }

    /**
     * Handles the case where a Task is done. The task will be marked as done and the user will be informed
     * Tasks that are done cannot be done again - the user will be informed should he/she try to do the task
     * more than once.
     *
     * @param input the description of the task that is inputted by the user.
     * @return the reply message to be sent back to the user.
     */
    public String done(String input) {
        assert input != null : "Done command input is empty!";

        input = input.replace("done","");
        input = input.trim();
        try {
            int i = Integer.parseInt(input) - 1;

            if (!list.get(i).isDone) {
                list.get(i).isDone = true;
                return "Nice! You have done this:\n" + list.get(i).toString();
            } else {
                return "You have already done\n" + list.get(i).toString() + "\nNo need to do it again!";
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.nosuchNumber();

        } catch (NumberFormatException e) {
            return ui.inputNumber();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Produces a Deadline class object which is added into the list. The user will also be informed of the
     * Deadline object that is created. Should the 'deadline'
     * command be given more than once or the '/by' command not given, the user will be informed and
     * the object will not be created.
     * @param input the description of the deadline task that is inputted by the user.
     * @return the reply message to be sent back to the user.
     */
    public String deadline(String input) {
        assert input != null : "Deadline command input is empty!";

        int repeat = checkRepeats(input,"/by");

        if (repeat > 1) {
            return ui.oneCommand();
        }

        input = input.replace("deadline","");
        input = input.trim();

        String[] strings = input.split("/by");

        try {
            if (checkEmpty(strings[0])) {
                return ui.emptyCmd();
            }

            Deadline deadline = new Deadline(strings[0], strings[1],++latest_index);
            list.add(deadline);


            return "Got it. I have added this task:\n" + deadline.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list";

        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.inputByCmd();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Finds Tasks contains the description highlighted by the user. The tasks will then be printed out
     * for the user to see. If there is no such task found, a message telling the user that there
     * is no matching tasks will be displayed.
     *
     * @param desc String of the Description of the task the user wants to find.
     * @return the reply message to be sent back to the user
     */
    public String find(String desc) {
        assert desc != null : "Find command input is empty!";

        desc = desc.replace("find","");
        desc = desc.trim();
        boolean at_least_one = false;
        int count = 1;
        sb = new StringBuilder();

        for (Task task : list) {
            if (task.description.contains(desc)) {
                at_least_one = true;
                sb.append(task.get_Index() + ". " + task.toString());
                sb.append("\n");
            }
        }

        if (!at_least_one) {
            return ui.noMatchingTasks();
        } else {
            sb.insert(0, ui.matchingTasks() + "\n");
            return sb.toString();
        }
    }


    /**
     * Produces an Event class object which is added into the list. The user will also be informed of the
     * Event object that is created. Should the 'event'
     * command be given more than once or the '/at' command not given, the user will be informed and
     * the object will not be created.
     *
     * @param input the description of the task that is inputted by the user
     * @return the reply message to be sent back to the user
     */
    public String event(String input) {
        assert input != null : "Event command input is empty!";

        int repeat = checkRepeats(input,"/at");

        if (repeat > 1) {
            return ui.oneCommand();
        }

        input = input.replace("event","");
        input = input.trim();

        String[] strings = input.split("/at");

        try {
            if (checkEmpty(strings[0])) {
                return ui.emptyCmd();
            }

            Event event = new Event(strings[0],strings[1],++latest_index);
            list.add(event);


            return "Got it. I have added this task:\n" + event.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list";

        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.inputAtCmd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks Repeats within a string. This function is used for checking repeats of commands entered.
     *
     * @param input string input entered by the user.
     * @param repeat string that is checked for repeats.
     * @return Number of repeats in the string.

     */
    public int checkRepeats(String input, String repeat) {
        assert input != null : "checkRepeats input string is empty!";
        assert repeat != null : "checkRepeats repeat string is empty!";

        int count = 0;
        String[] strings = input.split(" ");
        for (String s : strings) {
            if (s.equals(repeat)) {
                count++;
            }
        }
        assert count < Integer.MAX_VALUE : "Too many repeats!";
        return count;
    }


    /**
     * Checks if the input by the user is empty.
     *
     * @param cmd Command entered by the user.
     * @return boolean value whether it is empty.
     */
    public boolean checkEmpty(String cmd) {
        assert cmd != null : "checkEmpty input is empty!";

        if (cmd.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * Bye function which runs when the user inputs 'bye'. The list is then written into a text file and saved
     * until future running of the application.
     * @return the reply message to be sent back to the user
     * @throws Exception If any issue with any function
     */
    public String bye() throws Exception {
        try {
            storage.writeFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.sayBye();
    }


    /**
     * Returns the list in the form of a string when the user inputs the 'list' function into the UI.
     * Iterates through all the tasks in the list and calls
     * the overloaded toString function of the respective Tasks.
     * @return the list of Tasks to be sent back to the user.
     */
    public String printList() {
        sb = new StringBuilder();
        if (latest_index == 0) {
            sb.append(ui.emptyList());
        }

        for (Task task : list) {
            sb.append(task.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves the full details of a particular task. If all details are requested,
     * details of all the Tasks in the list are returned.
     * @return sends the full list of Tasks along with all additional details in a string format.
     */

    public String findDetails(String input) {
        assert input != null : "Details command input is empty!";

        String taskNumber = input.replace("details","");
        taskNumber = taskNumber.trim();
        sb = new StringBuilder();

        if (taskNumber.equals("all")) {
            for (Task t : list) {
                sb.append(t.details() + "\n");
            }
        } else {
            try {
                int i = Integer.parseInt(taskNumber) - 1;
                sb.append(list.get(i).details() + "\n");

            } catch (IndexOutOfBoundsException e) {
                sb.append(ui.nosuchNumber());

            } catch (NumberFormatException e) {
                sb.append(ui.inputNumber());
            }
        }
        return sb.toString();
    }
}