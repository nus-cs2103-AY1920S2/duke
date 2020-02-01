import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Storage {

    private static String fileSeparator = System.getProperty("file.separator");

    public static void createStorage() throws IOException {
        //relative path
        String relativePath = ".." + fileSeparator + "duke" + fileSeparator + "data" + fileSeparator + "duke.txt";
        File file = new File(relativePath);
        if (file.createNewFile()) {
            System.out.println(relativePath + " Save File Created");
        } else System.out.println("File " + relativePath + " already exists");
    }

    public static boolean checkFileExistence(String fileName) {
        //relative path
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    public static String loadStorage(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        int content;
        String result = "";
        while ((content = fis.read()) != -1) {
            result = result + ((char) content);
        }
        return result;
    }

    public static void storeData(String data, String file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data.getBytes());
        fos.flush();
        fos.close();
    }
}
