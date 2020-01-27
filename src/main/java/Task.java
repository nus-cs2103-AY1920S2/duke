import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
public class Task implements java.io.Serializable{






    protected String description;
    protected boolean isDone;


    public Task(String s){
        this.description = s;
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon(){
        return isDone? "\u2713" : "\u2718";
    }

    public void markAsDone(){
        this.isDone = true;
    }
}
