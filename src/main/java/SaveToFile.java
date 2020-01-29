import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {
    public static void usingFileWriter(String list) throws IOException{
        FileWriter fileWriter = new FileWriter("./out.txt");
        fileWriter.write(list);
        fileWriter.close();
    }
        
}
