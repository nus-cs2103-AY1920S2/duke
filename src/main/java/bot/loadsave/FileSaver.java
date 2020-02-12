package bot.loadsave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An interface containing one static method,
 * to assist in saving a String to the disk
 */
public interface FileSaver {
    /**
     * Saves a String as a file, to the given
     * relative address
     *
     * @param toBeSaved The StringBuilder containing
     *                  the String to be saved
     */
    static void saveStringAsFile(String relativeAddress, StringBuilder toBeSaved) {
        File saveLocation = new File(relativeAddress);
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(saveLocation)
            );
            writer.write(toBeSaved.toString());
            writer.close();
        } catch (IOException e) {
            // error in writing to file
            System.err.println("IOException2");
            System.err.println(e.getMessage());
        }
    }
}
