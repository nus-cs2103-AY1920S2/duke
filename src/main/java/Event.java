import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.*;
public class Event extends Task implements java.io.Serializable{

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
