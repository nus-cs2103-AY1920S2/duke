import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> listOfTasks;

    public TaskManager(){
        listOfTasks = new ArrayList<>();
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

    public void nonsenceInput() throws DukeException{
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}
