import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    ArrayList<Task> listOfTasks = new ArrayList<>();
    public Storage(){

    }

    public ArrayList<Task> loadExistingData() {
        String fileName = "data/data.txt";
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String[] temporary = line.split("\\|");

                if (temporary[0].contains("ToDo")) {
                    Task t = new ToDo(temporary[2]);
                    if (temporary[1].contains("1")) {
                        t.markAsDone();
                    }
                    listOfTasks.add(t);
                } else if (temporary[0].contains("Deadline")) {

                    Task t = new Deadline(temporary[2], LocalDate.parse(temporary[3]));
                    if (temporary[1].contains("1")) {
                        t.markAsDone();
                    }
                    listOfTasks.add(t);

                } else if (temporary[0].contains("Event")) {
                    Task t = new Event(temporary[2], temporary[3]);
                    if (temporary[1].contains("1")) {
                        t.markAsDone();
                    }
                    listOfTasks.add(t);

                } else {

                    if (listOfTasks.size() == 0) {
                        System.out.println("File is empty, no data to load.");
                    }
                }
            }

            // Always close files.
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return listOfTasks;
    }


    public void saveExistingData(ArrayList<Task> list){
        String fileName = "data/data.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0 ; i < list.size() ; i++){
                Task t = list.get(i);
                if(t instanceof ToDo){
                    bufferedWriter.write(((ToDo) t).saveData());
                    bufferedWriter.newLine();
                }else if(t instanceof Deadline){
                    bufferedWriter.write(((Deadline) t).saveData());
                    bufferedWriter.newLine();
                }else{
                    bufferedWriter.write(((Event) t).saveData());
                    bufferedWriter.newLine();
                }
            }

            // Always close files.
            bufferedWriter.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

}
