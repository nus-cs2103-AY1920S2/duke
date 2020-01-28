package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.io.FileNotFoundException;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage{

    String FILEPATH; 

    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * Count file rows.
     * @param file file
     * @return file row count
     * @throws IOException
     */
    public long getLineCount(File file) throws IOException {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines.count();
        }
    }

    /**
     * retrieve task from storage
     * @param lineContent
     * @param filepath
     * @return String task
     * @throws IOException
     */
    public String getTask(String lineContent, String filepath) throws IOException {
        File file = new File(filepath);
        return Files.lines(file.toPath())
                    .filter(line -> line.contains(lineContent))
                    .reduce("", (x,y) -> x+y+'\n');
    }

    /**
     * remove task from storage
     * @param lineContent
     * @param filepath
     * @param temp
     * @throws IOException
     */
    public void removeLine(String lineContent, String filepath, String temp) throws IOException {
        File file = new File(filepath);
        File tempo = new File(temp);
        PrintWriter out = new PrintWriter(new FileWriter(temp));
        Files.lines(file.toPath())
            .filter(line -> !line.contains(lineContent))
            .forEach(out::println);
        out.flush();
        out.close();
        tempo.renameTo(file);
    }

    /**
     * add task to storage
     * @param filepath
     * @param textToAdd
     * @throws IOException
     */
    public void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(textToAdd);
        fw.close(); 
    }

    /**
     * list tasks from file
     * @param filepath
     * @throws FileNotFoundException
     */
    public void printFileContents(String filepath) throws FileNotFoundException {
        File f = new File(filepath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine()); 
        }
        s.close();
    }

}