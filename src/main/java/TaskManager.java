import java.io.*;
import java.util.ArrayList;

public class TaskManager implements java.io.Serializable{

    ArrayList<Task> listOfTasks;

    public TaskManager(){
        listOfTasks = new ArrayList<>();
    }

    public void loadExistingData(){
        String fileName = "data/data.txt";
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                String[] temporary = line.split("\\|");

                if(temporary[0].contains("ToDo")){
                    Task t = new ToDo(temporary[2]);
                    if(temporary[1].contains("1")){
                        t.markAsDone();
                    }
                    listOfTasks.add(t);
                }else if(temporary[0].contains("Deadline")){
                    Task t = new Deadline(temporary[2],temporary[3]);
                    if (temporary[1].contains("1")){
                        t.markAsDone();
                    }
                    listOfTasks.add(t);
                }else if(temporary[0].contains("Event")){
                    Task t = new Event(temporary[2],temporary[3]);
                    if (temporary[1].contains("1")){
                        t.markAsDone();
                    }
                    listOfTasks.add(t);
                }else{
                    System.out.println("File is empty, no data to load.");
                }
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public void saveExistingData(){
        String fileName = "data/data.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.

            for(int i = 0 ; i < listOfTasks.size() ; i++){
                Task t = listOfTasks.get(i);
                if(t instanceof ToDo){
                    bufferedWriter.write(((ToDo) t).saveData());
                    bufferedWriter.newLine();
                }else if(t instanceof Deadline){
                    bufferedWriter.write(((Deadline) t).saveData());
                    bufferedWriter.newLine();
                }else{
                    bufferedWriter.write(((Event) t).saveData());
                    bufferedWriter.newLine();
                }
            }

            // Always close files.
            bufferedWriter.close();
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
            if(textEntered.split(" ").length == 1){
                throw new DukeException("The description of a deadline cannot be empty");
            }else {
                textEntered = textEntered.substring(9);
                String[] temp = (textEntered.split("/"));
                newTask = new Deadline(temp[0], temp[1].substring(3));
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
