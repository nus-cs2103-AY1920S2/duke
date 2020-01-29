import java.util.HashMap;
import java.util.HashSet;

public abstract class Task {
    boolean isDone = false;
    HashSet<String> wordsInDescription = new HashSet<>();

    String tick = "[" + (char) 10003 + "]";
    String cross = "[" + (char) 10060 + "]";


    abstract public String toString();

    public void setDone() {
        isDone = true;
    }

    abstract public String formatToStore();

    boolean contains(String keyword){
        if(wordsInDescription.contains(keyword)) {
            return true;
        } else{
            return false;
        }
    }

}
