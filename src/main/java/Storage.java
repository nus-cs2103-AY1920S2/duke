import java.io.*;
import java.util.ArrayList;
import java.io.IOException;


public class Storage {
    private String path;

    public Storage(String StorageLocation) {
        this.path = StorageLocation;
    }

    public void saveToDisk(ArrayList<Task> taskStorage) {
        String name = this.path;
        try{
            FileWriter fw = new FileWriter(this.path);
            BufferedWriter bw = new BufferedWriter(fw);
            int counter = 0;
            for(Task task : taskStorage){
                Task t = taskStorage.get(counter++);
                if(t instanceof Events){
                    bw.write("Event " + t.checkDone() + " "
                            + t.getDescription() + " "
                            +(((Events) t).getDate()).toString());
                    bw.newLine();
                }
                else if(t instanceof Deadlines){
                    bw.write("Deadlines " + t.checkDone() + " "
                            +t.getDescription() + " "
                            +(((Deadlines) t).getDate()).toString());
                    bw.newLine();
                }
                else {
                    bw.write("Todo " + t.getDescription() + " "
                            + t.checkDone());
                    bw.newLine();

                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Task> load()  {
        ArrayList<Task> destination = new ArrayList<>();

        try{
            FileReader fr = new FileReader(this.path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] tempArray = line.split(" ");
                String action = tempArray[0];

                if(action.equals("Todo")){
                    destination.add(new Task(tempArray[1],tempArray[2]));
                }
                else if(action.equals("Deadlines")){
                    destination.add(new Deadlines(tempArray[2], tempArray[3],tempArray[1]));
                }
                else {
                    destination.add(new Events(tempArray[2], tempArray[3],tempArray[1]));
                }
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return destination;
    }
}










