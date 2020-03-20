package dukeclasses;

/**
 * Todo class is a child class of dukeClasses.Task.
 */
public class ToDo extends Task {

    public ToDo(String desciption) {
        super(desciption);
    }

    /**
     * returns a String contaning description of object.
     *
     * @return returns a String containing the description of the object, used to print out
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * returns the dynamic state of the dukeClasses.todo.
     *
     * @return the state of dukeClasses.todo, to be saved in data.txt
     */
    public String saveData() {
        String isItDone = this.isDone ? "1" : "0";    //1 is done, 0 is not done
        String isItHighPriority = this.isHighPriority ? "1" : "0"; //1 is means high priority, 0 is means not

        return "dukeClasses.ToDo" + "|" + isItDone + "|" + this.description
                + "|" + isItHighPriority;
    }
}
