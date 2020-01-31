package dukeClasses;

import dukeClasses.*;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Handles all the issues related to Tasks!
 */
public class TaskManager {

    protected ArrayList<Task> listOfTasks;
    protected Storage storage;

    public TaskManager(){
        listOfTasks = new ArrayList<>();
        storage = new Storage();
    }

    /**
     * loads existing data from data.txt
     */
    public void loadExistingData(){
        listOfTasks = storage.loadExistingData();
    }

    /**
     * saves current data into data.txt
     */
    public void saveExistingData(){
        storage.saveExistingData(listOfTasks);

    }

    /**
     * Used for Level-9. find keyword that appears in each dukeClasses.Task in listOfTasks
     * @param keyword keyword to search
     */
    public void findTask(String keyword){
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0 ; i < listOfTasks.size() ; i ++){
            if(listOfTasks.get(i).description.contains(keyword)){
                System.out.println("  " + (i + 1) +  ". " + this.listOfTasks.get(i).toString());
            }
        }
    }

    /**
     * lists down all tasks
     */
    public void listAllTasks(){
        System.out.println("Here are your tasks in your list: ");
        for(int j = 0 ; j < this.listOfTasks.size() ; j++){
            System.out.println("  " + (j + 1) +  ". " + this.listOfTasks.get(j).toString());
        }
    }

    /**
     * change the boolean isDone? of a dukeClasses.Task
     * @param index index of dukeClasses.Task to be set as Done (isDone = True)
     */
    public void setTaskAsDone(int index){
        listOfTasks.get(index-1).markAsDone();
        storage.saveExistingData(listOfTasks);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + listOfTasks.get(index-1).toString());
    }

    /**
     * Adds a new task and saves it in data
     * @param textEntered includes the type of task, description, and /by or /at
     * @throws DukeException Must have deadline and date for dukeClasses.Deadline and Events
     */
    public void addTask(String textEntered) throws DukeException{

        Task newTask;

        if(textEntered.contains("todo")){ //Handles dukeClasses.Task that are dukeClasses.ToDo

            if(textEntered.split(" ").length == 1){
                throw new DukeException("The description of a todo cannot be empty");
            }else {
                newTask = new ToDo(textEntered.substring(5));
                this.listOfTasks.add(newTask);
            }

        }else if(textEntered.contains("deadline")){ //Handles dukeClasses.Task that are dukeClasses.Deadline

            if(textEntered.split(" ").length == 1){ //No input date
                throw new DukeException("The description of a deadline cannot be empty");
            }else{
                textEntered = textEntered.substring(9);
                String[] temp = (textEntered.split("/by ")); //leaving only the date and time portion
                String tempDate = temp[1];

                LocalDate date = LocalDate.parse(tempDate);
                /* This is to get time if needed
                LocalDateTime timing = date.atTime(18,00);
                System.out.println(timing.format(DateTimeFormatter.ofPattern("HH:mm")));
                */
                newTask = new Deadline(temp[0], date);
                this.listOfTasks.add(newTask);
            }

        }else{ //Handles task that is dukeClasses.Event

            if(textEntered.split(" ").length == 1){
                throw new DukeException("The description of an event cannot be empty");
            }else {
                textEntered = textEntered.substring(6);
                String[] temp = (textEntered.split("/"));
                newTask = new Event(temp[0], temp[1].substring(3));
                this.listOfTasks.add(newTask);
            }
        }
        storage.saveExistingData(listOfTasks);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Handles it when user types in nonsense inputs.
     * @throws DukeException Telling user that there is no such exception
     */
    public void nonsenseInput() throws DukeException{
        throw new DukeException("I'm sorry, but I don't know what that means :-(");

    }

    /**
     * Deletes task from listOfTasks and saves the data
     * @param indexOfTaskToDelete index of task to delete
     * @throws DukeException Cannot delete nothing
     * @throws IndexOutOfBoundsException Incase if user inputs something that is out of array bound
     */
    public void deleteTask(int indexOfTaskToDelete) throws DukeException, IndexOutOfBoundsException{

        if(listOfTasks.size() == 0){
            throw new DukeException("Nothing to delete because list is empty.");
        }
        indexOfTaskToDelete = indexOfTaskToDelete - 1;
        if(listOfTasks.get(indexOfTaskToDelete) == null){
            throw new DukeException("Cannot delete because the task do not exist.");
        }else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + listOfTasks.get(indexOfTaskToDelete).toString());
            System.out.println("Now you have " + (listOfTasks.size() - 1) + " tasks left in the list.");
            listOfTasks.remove(indexOfTaskToDelete);
            storage.saveExistingData(listOfTasks);
        }
    }

    /**
     * used for Level 1 and 2. Redundant now
     * @param textEntered the user's input
     */
    public void echo(String textEntered){
        System.out.println(textEntered);
    }
}
