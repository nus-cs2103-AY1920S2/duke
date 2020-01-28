import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> store = new ArrayList<>();
        //Read from File
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String currLine = br.readLine();
            while(currLine != null) {
                String[] parts = currLine.split("\\|");
                String[] newParts = new String[parts.length];
                for(int i = 0; i < parts.length; i++) {
                    newParts[i] = parts[i].trim();
                }
                if(newParts[0].equals("T")) {
                    ToDo temp = new ToDo(newParts[2]);
                    if(newParts[1].equals("\u2713")) {
                        temp.isDone = true;
                    }
                    store.add(temp);
                } else if(newParts[0].equals("D")) {
                    LocalDateTime d1 = LocalDateTime.parse(newParts[3]);
                    Deadline temp = new Deadline(newParts[2],d1);
                    if(newParts[1].equals("\u2713")) {
                        temp.isDone = true;
                    }
                    store.add(temp);
                } else if(newParts[0].equals("E")) {
                    Event temp = new Event(newParts[2], newParts[3]);
                    if(newParts[1].equals("\u2713")) {
                        temp.isDone = true;
                    }
                    store.add(temp);
                } else {}
                currLine = br.readLine();
            }
        } catch(IOException e) {
            throw new DukeException("IO Exception");
        }
        return store;
    }

}
