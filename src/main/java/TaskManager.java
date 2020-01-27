import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TaskManager implements java.io.Serializable{

    ArrayList<Task> listOfTasks;

    public TaskManager(){
        listOfTasks = new ArrayList<>();
    }

    public void loadExistingData(){
        try {
            FileInputStream fileIn = new FileInputStream("data/data.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listOfTasks = (ArrayList<Task>) in.readObject();



            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();

        }catch(ClassNotFoundException c) {
            System.out.println("List class not found");
            c.printStackTrace();

        }
    }

    public void saveExistingData(){
        try {
            FileOutputStream fileOut = new FileOutputStream("data/data.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfTasks);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in data/data.txt");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }




    public void listAllTasks(){
        System.out.println("Here are your tasks in your list: ");
        for(int j = 0 ; j < this.listOfTasks.size() ; j++){
            System.out.println(j+1 +  ". " + this.listOfTasks.get(j).toString());
        }
    }

    public void setTaskAsDone(int index){
        listOfTasks.get(index-1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + listOfTasks.get(index-1).toString());

    }

    public void addTask(String textEntered) throws DukeException{

        Task newTask;

        if(textEntered.contains("todo")){

            if(textEntered.split(" ").length == 1){
                throw new DukeException("The description of a todo cannot be empty");
            }else {
                newTask = new ToDo(textEntered.substring(5));
                this.listOfTasks.add(newTask);
            }
        }else if(textEntered.contains("deadline")){//Deadline
            if(textEntered.split(" ").length == 1){ //No input date
                throw new DukeException("The description of a deadline cannot be empty");
            }else {
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
        }else{//Event

            if(textEntered.split(" ").length == 1){
                throw new DukeException("The description of an event cannot be empty");
            }else {
                textEntered = textEntered.substring(6);
                String[] temp = (textEntered.split("/"));
                newTask = new Event(temp[0], temp[1].substring(3));
                this.listOfTasks.add(newTask);
            }
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void nonsenseInput() throws DukeException{
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

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
        }
    }

    public void echo(String textEntered){
        System.out.println(textEntered);
    }
}
