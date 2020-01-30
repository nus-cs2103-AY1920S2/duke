package dukeClasses;

import dukeClasses.Task;

/**
 * dukeClasses.Event class is a child class of dukeClasses.Task that has a at variable which tells the user
 * where is the event at
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    /**
     * returns a String contaning description of object
     * @return returns a String containing the description of the object, used to print out
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    /**
     * returns the dynamic state of the dukeClasses.Event
     * @return the state of dukeClasses.Event, to be saved in data.txt
     */
    public String saveData(){
        String isItDone = this.isDone? "1" : "0"; //1 is done, 0 is not done
        return "dukeClasses.Event" + "|" + isItDone + "|" + this.description + "|" + this.at;
    }
}
