import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String command ;
    private String task;

    public Storage(){
    }

    public Storage (String command, String task){
        this.command = command;
        this.task = task;
    }

    public void setCommand(String command){
        this.command = command;
    }

    public void setTask(String tasks){
        this.task = task;
    }

    public static void createFile(String command,String task) throws IOException {
        String filePath = "duke.txt";
        // create file
        File f = new File(filePath);
        // write task into file
        FileWriter fw = new FileWriter(filePath);
        fw.write(command+" || "+task);
        fw.close();
        try {
            boolean result = f.createNewFile();
            Scanner s = new Scanner(f);
            System.out.println("Load data from duke text file ");
            while(s.hasNext()){
                System.out.println("     "+s.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









