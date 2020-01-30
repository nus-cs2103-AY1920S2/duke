import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;

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

    public static void writeFile(String command,String task,File f) throws IOException {
        String filePath = "duke.txt";
        FileWriter fw = new FileWriter(filePath,true);
        BufferedWriter br = new BufferedWriter(fw);
        br.write(command+" || "+task);
        br.newLine();
        br.close();
//        fw.write(command+" || "+task);
        fw.close();
        try {

            Scanner s = new Scanner(f);
            System.out.println("Load data from duke text file ");
            while(s.hasNext()){
                System.out.println("     "+s.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static File createNewFile(){
//        String filePath = "duke.txt";
//        File f = new File(filePath);
//        return f;
//    }
}









