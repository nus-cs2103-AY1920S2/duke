import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


/*
 * Parser
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * The Parser class is the parser to load the database file.
 * @author Daniel Alfred Widjaja
 */
public class Parser {
    private String fileLoc;

    /**
     * Construct the parser instance.
     * @param fileLoc The location of the database file.
     */
    public Parser(String fileLoc) {
        this.fileLoc = fileLoc;
    }

    /**
     * Load and parse the database from file into an ArrayList.
     * @param fileLoc The location of the database file.
     * @return An ArrayList loaded from the file.
     */
    public ArrayList<Task> getDatabase(String fileLoc) {
        ArrayList<Task> listing = new ArrayList<>();
        Path path = Paths.get(fileLoc);
        try {
            Files.createFile(path);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        File file = new File(fileLoc);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            if (!file.createNewFile()) {
                String st;
                while ((st = br.readLine()) != null) {
                    listing.add(parseList(st));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listing;
    }

    /**
     * Load and parse the database from object's fileLoc into an ArrayList.
     * @return An ArrayList loaded from the object's fileLoc.
     */
    public ArrayList<Task> getDatabase() {
        return this.getDatabase(fileLoc);
    }

    /**
     * Returns Task object from string.
     * String example: D|true|read book|by|2019-02-10
     *
     * @param s string for object parameters.
     * @return Task of string.
     */
    private Task parseList(String s) {
        String[] params = s.split("\\|");

        if (params[0].equals("D")) {
            assert(params.length == 6);
            return new Deadline(params[2], params[3], params[4], !params[1].equals("false"), Integer.parseInt(params[5]));
        } else if (params[0].equals("E")) {
            assert(params.length == 6);
            return new Event(params[2], params[3], params[4], !params[1].equals("false"), Integer.parseInt(params[5]));
        } else {
            assert(params.length == 4);
            return new Task(params[2], !params[1].equals("false"), Integer.parseInt(params[3]));
        }
    }
}
