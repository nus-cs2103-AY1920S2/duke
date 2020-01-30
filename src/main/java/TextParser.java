import java.util.ArrayList;

public class TextParser {

    public static String[] myFirstParser(String line) {
        String[] arrSplit = line.split(" " , 2);
        return arrSplit;
    }

    public static String[] mySecondParser(String line) {
        String[] arrSplit = line.split("/", 2);
        return arrSplit;
    }
}
