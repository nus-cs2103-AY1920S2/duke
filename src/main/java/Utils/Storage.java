package Utils;

import Task.DukeException;

import java.io.*;

public class Storage {

    static final String dirname = "./data";
    static final String pathname = "./data/duke.txt";

    public Storage(){}

    public static String loadFromFile() throws DukeException {
        try {

            FileReader fileReader = new FileReader(pathname);
            System.out.println("Existing file found, loading...");

            int i = 0;
            String data = "";

            try {
                while (( i = fileReader.read()) != -1){
                    data += (char)i;
                }

                System.out.println(data);

                return data;

            } catch ( IOException e ){
                throw new DukeException("Error reading file!");
            }

        } catch ( FileNotFoundException e ) {
            throw new DukeException("No existing file!");
        }

    }

    public static void writeToFile( String data ) throws DukeException {

        //make directory
        File dir = new File(dirname);
        dir.mkdir();

        //write to file
        File saveFile = new File(pathname);
        FileWriter fileWriter = null;

        try {

            fileWriter = new FileWriter(saveFile);
            fileWriter.write(data);

        } catch (IOException e) {
            throw new DukeException("Error writing to file. Your changes were not saved.");
        } finally {
            try {
                fileWriter.close();
            } catch ( IOException e ) {
                throw new DukeException("Error closing file");
            }
        }

    }

}
