/**
 * Todo class is a child class of Task
 */
public class ToDo extends Task {

    protected ToDo(String desciption){
        super(desciption);
    }

    /**
     * returns a String contaning description of object
     * @return returns a String containing the description of the object, used to print out
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * returns the dynamic state of the ToDo
     * @return the state of ToDo, to be saved in data.txt
     */
    public String saveData(){
        String isItDone = this.isDone? "1" : "0";
        //1 is done, 0 is not done
        return "ToDo" + "|" + isItDone + "|" + this.description;
    }
}
