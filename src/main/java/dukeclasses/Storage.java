package dukeclasses;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * This class handles the saving and loading of data from data.txt
 */
public class Storage {

    protected ArrayList<Task> listOfTasks = new ArrayList<>();
    protected String dataDirPath = "data/";


    protected String fileSeparator = File.separator;
    protected String projectRootPath = Paths.get("").toAbsolutePath().toString();
    protected String dataDirectoryPath = projectRootPath + fileSeparator + "data";
    protected String saveFilePath = dataDirectoryPath + fileSeparator + "data.txt";


    public Storage() {
    }

    /**
     * Check and create a folder to store user's data, if its not there.
     *
     * @throws IOException If the folder cannot be created, inform the user.
     */
    public void checkFileDir() throws IOException { //check if data folder exists, if not create it

        File dataDirectory = new File(dataDirectoryPath);

        //If data folder does not exists then create a folder for it
        if (!dataDirectory.exists()) {
            try {
                Files.createDirectories(Paths.get(dataDirectoryPath));
            } catch (IOException e) {
                System.out.println("Cannot create file");
            }

        } else {
            System.out.println("Connecting to Stark's secure database...");
        }
    }

    /**
     * Create a file called data.txt to store user's data.
     *
     * @throws IOException throws exception if file is not found
     */
    public void checkFile() throws IOException {

        File dataFile = new File(saveFilePath);
        if (!dataFile.exists()) {
            try {
                Files.createFile(Paths.get(saveFilePath));
            } catch (Exception e) {
                throw new IOException();
            }
        } else {
            System.out.println("Data file found! Proceeding to load existing data!");
        }
    }

    /**
     * helps with loading data when starting up program again. Data is stored in data.txt.
     *
     * @return returns an array List with the data filled in from data.txt
     */
    public ArrayList<Task> loadExistingData() {
        String fileName = saveFilePath;
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] temporary = line.split("\\|");
                if (temporary[0].contains("dukeClasses.ToDo")) {
                    Task t = new ToDo(temporary[2]);
                    if (temporary[1].contains("1")) {
                        t.markAsDone();
                    }
                    if (temporary[3].contains("1")) {
                        t.markAsHighPriority();
                    }
                    listOfTasks.add(t);
                } else if (temporary[0].contains("dukeClasses.Deadline")) {
                    Task t = new Deadline(temporary[2], LocalDate.parse(temporary[3]));
                    if (temporary[1].contains("1")) {
                        t.markAsDone();
                    }
                    if (temporary[4].contains("1")) {
                        t.markAsHighPriority();
                    }
                    listOfTasks.add(t);
                } else if (temporary[0].contains("dukeClasses.Event")) {
                    Task t = new Event(temporary[2], temporary[3]);
                    if (temporary[1].contains("1")) {
                        t.markAsDone();
                    }
                    if (temporary[4].contains("1")) {
                        t.markAsHighPriority();
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
                    "Unable to open file '"
                           + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return listOfTasks;
    }


    /**
     * saves data in data.txt before program exits.
     *
     * @param list list contains the ArrayList filled with all the data up till now.
     *             It will be saved in data.txt
     */

    public void saveExistingData(ArrayList<Task> list) {
        String fileName = saveFilePath;
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                if (t instanceof ToDo) {
                    bufferedWriter.write(((ToDo) t).saveData());
                    bufferedWriter.newLine();
                } else if (t instanceof Deadline) {
                    bufferedWriter.write(((Deadline) t).saveData());
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(((Event) t).saveData());
                    bufferedWriter.newLine();
                }
            }

            // Always close files.
            bufferedWriter.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
