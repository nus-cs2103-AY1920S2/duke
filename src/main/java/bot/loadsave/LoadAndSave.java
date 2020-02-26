package bot.loadsave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Class LoadAndSave handles loading and
 * saving of data from the disk
 *
 * @param <T> Type of data stored at the
 *           file name and directory provided
 */
public abstract class LoadAndSave<T> {
    private final String fileDirectory;
    private final String fileName;
    private final Optional<FileReader> toLoadFrom;

    /**
     * Constructs a new LoadAndSave with the desired
     * file directory and file name
     *
     * @param fd Location of the file on the system
     * @param fn Name of the file in the directory
     * @param def String representing the default data
     *                to be loaded if file is not found
     *
     * @throws FileNotFoundException When file directory
     *     or file name is invalid
     */
    public LoadAndSave(String fd, String fn, String def)
            throws FileNotFoundException {

        this.fileDirectory = fd;
        this.fileName = fn;
        String fullAddress = LoadAndSave.getFullFileAddress(fd, fn);
        FileReader fileLocation;
        try {
            fileLocation = new FileReader(fullAddress);
        } catch (FileNotFoundException e) {
            // try to create required directories and file name
            File desiredDirectory = new File(fd);
            File desiredLocation = new File(fullAddress);
            if (!desiredLocation.exists()) {
                System.out.println(bot.Ui.LOAD_FROM_DISK_FAIL_MESSAGE);
                try {
                    desiredDirectory.mkdirs();
                    desiredLocation.createNewFile();
                } catch (IOException ex) {
                    // error in creating new file
                    throw new FileNotFoundException(
                            "File not found, could not create"
                                    + " file at " + fullAddress
                    );
                }

                FileSaver.saveStringAsFile(fullAddress, new StringBuilder(def));
                fileLocation = new FileReader(fullAddress);
            } else {
                throw e;
            }
        }
        this.toLoadFrom = Optional.of(fileLocation);
    }

    /**
     * Constructor for a dummy LoadAndSave, to be
     * used only by DummyLoader
     */
    protected LoadAndSave() {
        this.fileDirectory = "";
        this.fileName = "";
        this.toLoadFrom = Optional.empty();
    }

    /**
     * Loads the stored items from the given
     * file directory and name
     *
     * @return ArrayList containing the stored items
     */
    public abstract ArrayList<T> loadFromDisk();

    /**
     * Saves a given Collection of items to the
     * given file directory and name (overwriting
     * the previous content!)
     *
     * @param items The items to be saved
     */
    public abstract void saveToDisk(Collection<T> items);

    /**
     * Gets the file directory provided originally
     *
     * @return String representing file directory
     */
    public String getFileDirectory() {
        return this.fileDirectory;
    }

    /**
     * Gets the file name provided originally
     *
     * @return String representing file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the full relative address of the file, from
     * the given file directory and file name
     *
     * @return String representing full relative
     *     file address
     */
    public String getRelativeAddress() {
        return LoadAndSave.getFullFileAddress(this.fileDirectory, this.fileName);
    }

    /**
     * Gets the Optional{@literal <FileReader>} that
     * may contain the FileReader which reads from
     * the given file directory and name
     *
     * @return A FileReader that reads the file
     */
    public Optional<FileReader> getToLoadFrom() {
        return this.toLoadFrom;
    }

    /**
     * Gets the full file address relative to the
     * current location
     *
     * @param directory String representing file directory
     * @param name String representing file name
     * @return String representing full file address
     */
    private static String getFullFileAddress(String directory, String name) {
        return directory + "/" + name;
    }
}
