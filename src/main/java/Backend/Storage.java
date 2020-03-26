package Backend;

import Backend.Constants.FilePaths;
import Backend.Exceptions.DukeException;

import java.io.*;

public class Storage {

    public Storage(){}

    public static String loadDataFromFile() throws DukeException{
        return loadFromFile( FilePaths.DATA_PATHNAME );
    }

    public static String loadHelpFromFile() throws DukeException {
        return loadFromFile( FilePaths.HELP_PATHNAME );
    }

    public static void writeDataToFile( String data ) throws DukeException{
        writeToFile( data );
    }

    /**
     * loads data from a file and converts it to a string of data.
     * @return a string of data to be parsed
     */
    private static String loadFromFile(String pathname) throws DukeException {
        try {

            FileReader fileReader = new FileReader(pathname);
            System.out.println("Existing file found, loading...");

            int i = 0;
            StringBuilder data = new StringBuilder();

            try {
                while (( i = fileReader.read()) != -1){
                    data.append( (char) i );
                }

                return data.toString();

            } catch ( IOException e ){
                throw new DukeException(e);
            }

        } catch ( FileNotFoundException e ) {
            throw new DukeException(e);
        }

    }

    /**
     * Writes data to a file stored in a directory.
     * If directory specified by the directory name does not exist, makes a directory.
     * If file does not exist, creates a file.
     * @param data string of data to be written.
     */
    private static void writeToFile( String data ) throws DukeException {

        //make directory
        File dir = new File( FilePaths.DATA_DIRNAME );
        dir.mkdir();

        //write to file
        File saveFile = new File( FilePaths.DATA_PATHNAME );
        FileWriter fileWriter = null;

        try {

            fileWriter = new FileWriter(saveFile);
            fileWriter.write(data);

        } catch (IOException e) {
            throw new DukeException(e);
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch ( IOException e ) {
                throw new DukeException(e);
            }
        }

    }

}
