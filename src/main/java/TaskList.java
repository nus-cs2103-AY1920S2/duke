import java.util.*;

/**
 * Represents a TaskList which handles adding, removing or listing out tasks as depicted by commands from the
 * user.
 */

public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;
    private int latest_index;
    private Storage storage;

    public TaskList(ArrayList<Task> list, int latest_index, Storage storage) {
        this.list = list;
        this.ui = new Ui();
        this.latest_index = latest_index;
        this.storage = storage;
    }

    public int getLatest_index() {
        return latest_index;
    }

    /**
     * Updates the Index of each task whenever a task is deleted
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
     * @param input the input, normally a number, that is entered
     */
    public void delete(String input) {
        input = input.replace("delete","");
        input = input.trim();

        if (input.equals("all")) {
            list.clear();
            latest_index = 0;
            System.out.println(ui.deleteAll());
            return;
        }

        try {
            int i = Integer.parseInt(input) - 1;

            if (!list.get(i).isDone) {
                System.out.println(ui.unfinishedTask() + list.get(i).toString());
            } else {
                System.out.println(ui.finishedTask() + list.get(i).toString());
            }

            list.remove(i);
            updateIndex();
            latest_index--;

            System.out.println("\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (IndexOutOfBoundsException e) {
            System.out.println(ui.nosuchNumber());

        } catch (NumberFormatException e) {
            System.out.println(ui.inputNumber());
        }
    }


    /**
     * Produces a Todo class object which is added into the list. The user will also be informed of the
     * Todo object created. Should the 'todo' command be given more than once, the user will be informed
     * and a Todo class object will not be produced.
     *
     * @param input the description of the todo task that is inputted by the user
     */
    public void todo(String input) {

        int repeat = checkRepeats(input,"todo");

        if (repeat > 1) {
            System.out.println(ui.oneCommand());
            return;
        }
        input = input.replace("todo", "");
        input = input.trim();

        if (checkEmpty(input)) {
            return;
        }
        Todo todo = new Todo(input, ++latest_index);
        list.add(todo);
        System.out.println("Got it. I have added this task:\n" + todo.toString() +
                "\nNow you have a total of " + latest_index + " Tasks in your list");
    }

    /**
     * Handles the case where a Task is done. The task will be marked as done and the user will be informed
     * Tasks that are done cannot be done again - the user will be informed should he/she try to do the task
     * more than once.
     *
     * @param input the description of the task that is inputted by the user
     */
    public void done(String input) {
        input = input.replace("done","");
        input = input.trim();
        try {
            int i = Integer.parseInt(input) - 1;

            if (!list.get(i).isDone) {
                list.get(i).isDone = true;
                System.out.println("Nice! You have done this:\n" + list.get(i).toString());
            } else {
                System.out.println("You have already done\n" + list.get(i).toString() + "\nNo need to do it again!");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ui.nosuchNumber());

        } catch (NumberFormatException e) {
            System.out.println(ui.inputNumber());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Produces a Deadline class object which is added into the list. The user will also be informed of the
     * Deadline object that is created. Should the 'deadline'
     * command be given more than once or the '/by' command not given, the user will be informed and
     * the object will not be created.
     *
     * @param input the description of the deadline task that is inputted by the user
     */
    public void deadline(String input) {

        int repeat = checkRepeats(input,"/by");

        if (repeat > 1) {
            System.out.println(ui.oneCommand());
            return;
        }

        input = input.replace("deadline","");
        input = input.trim();

        String[] strings = input.split("/by");

        try {
            if (checkEmpty(strings[0])) {
                return;
            }

            Deadline deadline = new Deadline(strings[0], strings[1],++latest_index);
            list.add(deadline);


            System.out.println("Got it. I have added this task:\n" + deadline.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ui.inputByCmd());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Finds Tasks contains the description highlighted by the user. The tasks will then be printed out
     * for the user to see. If there is no such task found, a message telling the user that there
     * is no matching tasks will be displayed.
     *
     * @param desc a string of the Description of the task the user wants to find
     */
    public void find(String desc) {
        desc = desc.replace("find","");
        desc = desc.trim();
        boolean at_least_one = false;
        int count = 1;
        StringBuilder sb = new StringBuilder();

        for (Task task : list) {
            if (task.description.contains(desc)) {
                at_least_one = true;
                sb.append(task.get_Index() + ". " + task.toString());
                sb.append("\n");
            }
        }

        if (!at_least_one) {
            System.out.println(ui.noMatchingTasks());
        } else {
            System.out.println(ui.matchingTasks());
            System.out.println(sb.toString());
        }
    }


    /**
     * Produces an Event class object which is added into the list. The user will also be informed of the
     * Event object that is created. Should the 'event'
     * command be given more than once or the '/at' command not given, the user will be informed and
     * the object will not be created.
     *
     * @param input the description of the task that is inputted by the user
     */
    public void event(String input) {

        int repeat = checkRepeats(input,"/at");

        if (repeat > 1) {
            System.out.println(ui.oneCommand());
            return;
        }

        input = input.replace("event","");
        input = input.trim();

        String[] strings = input.split("/at");

        try {
            if (checkEmpty(strings[0])) {
                return;
            }

            Event event = new Event(strings[0],strings[1],++latest_index);
            list.add(event);


            System.out.println("Got it. I have added this task:\n" + event.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ui.inputAtCmd());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Checks Repeats within a string. This function is used for checking repeats of commands entered.
     *
     * @param input string input entered by the user
     * @param repeat string that is checked for repeats
     */
    public int checkRepeats(String input, String repeat) {

        int count = 0;
        String[] strings = input.split(" ");
        for (String s : strings) {
            if (s.equals(repeat)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Checks if the input by the user is empty
     *
     * @param cmd Command entered by the user
     * @return boolean value whether it is empty
     */
    public boolean checkEmpty(String cmd) {

        if (cmd.isEmpty()) {
            System.out.println(ui.emptyCmd());
            return true;
        }
        return false;
    }


    /**
     * Bye function which runs when the user inputs 'bye'. The list is then written into a text file and saved
     * until future running of the application.
     *
     * @throws Exception If any issue with any function
     */
    public void bye() throws Exception {
        // store
        System.out.println(ui.sayBye());
        storage.writeFile(list);
    }


    /**
     * Prints the list when the user inputs 'list' Iterates through all the tasks in the list and calls
     * the overloaded toString function.
     *
     */
    public void printList() {

        if (latest_index == 0) {
            System.out.println(ui.emptyList());
        }

        for (Task task : list) {
            System.out.println(task.get_Index() + ". " + task.toString());
        }
    }
}