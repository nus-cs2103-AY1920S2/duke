package database;

import duke.UI;
import exceptions.DukeException;
import resources.Task;
import resources.TaskTracker;

import java.util.ArrayList;

/**
 * Database.MyList handles everything to do with the list
 */
public class MyList {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static Task justAdded;

    /**
     * Add tasks.
     *
     * @param t the t
     */
    public static void addItem(Task t) {
        System.out.println("added: " + t);
        listOfTasks.add(t);
        justAdded = t;
    }
    /**
     * Gets the tasks that was recently just added in
     *
     * @return Task that was recently added
     */
    protected static Task getRecentAddedTask() {
        return justAdded;
    }

    /**
     * Delete tasks.
     *
     * @param ID the id
     * @throws DukeException the duke exception
     */
    public static void deleteItem(int ID)throws DukeException {
        ID = ID - 1;
        if (ID >= listOfTasks.size() || ID < 0) {
            throw new DukeException(UI.getReply("deleteEmpty"));
        }
        System.out.println("Deleted: " + listOfTasks.get(ID));
        TaskTracker.markDeleted();
        listOfTasks.remove(ID);
    }


    /**
     * Prints list.
     * @return A string with list prints
     */
    public static String printList() {
        String printed = "";
        if (listOfTasks.size() == 0) {
            return UI.getReply("emptyList");
        }
        int count = 1;
        for (Task t : listOfTasks) {
            printed = printed + count + ". " + t + "\n";
            count++;
        }
        return printed;
    }

    /**
     * Prints number of items in tasks.
     * @return An int of the number of items in list
     */
    public static int printCount() {
        System.out.println("Now you have " + listOfTasks.size() + " things in your bloody list..");
        return listOfTasks.size();

    }

    /**
     * Mark list item as done
     *
     * @param ID the id
     * @throws DukeException the duke exception
     */
    public static void markDone(int ID) throws DukeException {
        ID = ID - 1;
        if (ID >= listOfTasks.size() || ID < 0) {
            throw new DukeException(UI.getReply("imaginary"));
        }
        listOfTasks.get(ID).markDone();
        //System.out.println(DaList.get(ID));
    }

    /**
     * Gets list array list.
     *
     * @return the list of objects
     */
    public static ArrayList getList() {
        return listOfTasks;
    }

    /**
     * Find existing objects and prints
     *
     * @param keyword takes in keyword
     * @return String stating number of items find
     */
    public static String find(String keyword) {
        ArrayList<Task> localList = populateFindList(keyword);
        int count = 1;
        String founded = "";

        if (localList.size() != 0) {
            founded = "Found these tasks:\n";
        }
        for (Task t: localList) {
            System.out.println(count + ". " + t);
            founded = founded + count + ". " + t + "\n";
            count++;
        }
        return founded + "Found in total of " + localList.size() + " items";
    }

    /**
     * Look through the list of objects and populate local list
     *
     * @param str takes in keyword
     * @return an ArrayList containing find results
     */
    private static ArrayList populateFindList(String str) {
        ArrayList<Task> localList = new ArrayList<>();
        for (Task t: listOfTasks) {
            if (t.toString().contains(str)) {
                localList.add(t);
            }
        }
        return localList;
    }
}
