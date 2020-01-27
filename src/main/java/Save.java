import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    String path;
    void load() throws IOException, FileNotFoundException {
        String filePath = new File("").getAbsolutePath();
        int ind = filePath.lastIndexOf("/");
        path = filePath.substring(0, ind + 1);
        path = path.concat("duke.txt");

        try (FileReader fr = new FileReader(path)) {
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                parser(line);
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    void parser(String line)throws IOException{
        int i=line.indexOf("|");
        String type=line.substring(0,i);
        switch(type){
            case "T":
                type="todo";
                break;
            case "E":
                type="event";
                break;
            case "D":
                type="deadline";
                break;
            default:
        }
        String sentence;
        String task;

        int j=line.indexOf("|",i+1);
        String done=line.substring(i+1,j);
        int d=Integer.valueOf(done);
        i=j;
        if(!type.equals("todo")){
        j=line.indexOf("|",i+1);
        task=line.substring(i+1,j);
        String time="";


             time=line.substring(j+1);
             if(type.equals(("deadline"))) {
                 sentence = type + " " + task + " /by " + time;
             }
             else {
                 sentence = type + " " + task + " /at " + time;
             }
        }
        else{
            task=line.substring(j+1);
            sentence=type+" "+task;
        }
        Duke.sort(1,sentence,d);

    }
    void save()throws IOException{

        try(FileWriter fw=new FileWriter(path,false)){
            String s="";
           for(int i=0;i<Duke.list.size();i++){

               Task ob=Duke.list.get(i);
               switch(ob.getType()){
                   case "deadline":
                       s=s+"D|"+ob.getDone()+"|"+ob.getTaskName()+"|"+((Deadline)ob).getBy()+"\n";
                       break;
                   case "todo":
                       s=s+"T|"+ob.getDone()+"|"+ob.getTaskName()+"\n";
                       break;
                   case "event":
                       s=s+"E|"+ob.getDone()+"|"+ob.getTaskName()+"|"+((Event)ob).getAt()+"\n";
                       break;
                   default:
               }

           }
           fw.write(s);
           fw.close();
        }
        catch(IOException e){

        }
    }
}
