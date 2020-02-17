package database;

import Duke.UI;
import exceptions.DukeException;
import resources.Task;

import java.util.ArrayList;

/**
 * Database.MyList handles everything to do with the list
 */
public class MyList {
    private static ArrayList<Task> DaList = new ArrayList<>();




    /**
     * Add tasks.
     *
     * @param t the t
     */
    public static void addItem(Task t){
        System.out.println("added: " + t);
        DaList.add(t);
    }

    /**
     * Delete tasks.
     *
     * @param ID the id
     * @throws DukeException the duke exception
     */
    public static void deleteItem(int ID)throws DukeException{
        ID = ID-1;
        if(ID>=DaList.size()|| ID<0){
            throw new DukeException(UI.getReply("deleteEmpty"));
        }
        System.out.println("Deleted: " + DaList.get(ID));
        DaList.remove(ID);
    }


    /**
     * Prints list.
     */
    public static void printList(){
        if(DaList.size()==0){
            System.out.println(UI.getReply("emptyList"));
        }
        int count = 1;
        for(Task t : DaList){
            System.out.println(count+ ". "+ t);
            count++;
        }
    }

    /**
     * Prints number of items in tasks.
     */
    public static void printCount(){
        System.out.println("Now you have "+ DaList.size() + " things in your bloody list..");
    }

    /**
     * Mark list item as done.
     *
     * @param ID the id
     * @throws DukeException the duke exception
     */
    public static void markDone(int ID) throws DukeException {
        ID = ID - 1;
        if(ID >= DaList.size()|| ID < 0){
            throw new DukeException(UI.getReply("imaginary"));
        }
        DaList.get(ID).markDone();
        System.out.println(DaList.get(ID));
    }

    /**
     * Get list array list.
     *
     * @return the list of objects
     */
    public static ArrayList getList(){
        return DaList;
    }

    /**
     * Find existing objects and prints
     *
     * @param keyword takes in keyword
     * @return String stating number of items find
     */
    public static String find(String keyword) throws DukeException{
        ArrayList<Task> localList = populateFindList(keyword);
        boolean isFirst = true;
        int count = 1;

        for(Task t: localList){
            if(isFirst){
                System.out.println("Found these tasks: ");
                isFirst = false;
            }
            System.out.println(count + ". "+ t);
            count++;
        }
        return "Found in total of " + localList.size() + " items";
    }

    /**
     * Look through the list of objects and populate local list
     *
     * @param str takes in keyword
     * @return an ArrayList containing find results
     */
    private static ArrayList populateFindList(String str){
        ArrayList<Task> localList = new ArrayList<>();
        for(Task t: DaList){
            if(t.toString().contains(str)){
                localList.add(t);
            }
        }
        return localList;
    }
}
