import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts array list of tasks to String and saves to hard disk
     * @param xs
     * @throws FileNotFoundException
     */
    public String saveToFile(ArrayList<Task> xs) throws FileNotFoundException {
        String output = "";
        StringBuilder sb = new StringBuilder();
        for(Task t : xs) {
            sb.append(t.saveToText() + "\n");
        }
        try (PrintStream out = new PrintStream(new FileOutputStream(this.filePath))) {
            out.print(sb.toString());
            output = "Your tasks have been saved to the hard disk";
        } catch (IOException e){
            System.out.println(e);
        }
        return output;
    }

    /**
     * Converts saved text file from specified directory to a list of input strings
     * @return a List of Strings that represent saved data from previous uses
     * @throws IOException
     */
    public List<String> loadSavedData() throws IOException {
        File tmpDir = new File(this.filePath);
        String absolutePath;
        List<String> data = new ArrayList<>();
        if(tmpDir.exists()) {
            absolutePath = tmpDir.getAbsolutePath();
            Scanner sc = new Scanner(tmpDir);
            while (sc.hasNext()) {
                data.add(sc.nextLine());
            }
        }
        return data;
    }
}
