package duke.other;

import duke.task.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Path path = Paths.get(filePath);
        File noteFile = new File(path.toString());
        if (Files.notExists(path)) {
            noteFile = new File("data/duke.txt");
        }
        return fileToArray(noteFile.toString());
    }

    private static ArrayList<Task> fileToArray(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        while(sc.hasNext()) {
            String note = sc.nextLine();
            String[] noteArr = note.split(">");
            String noteType = noteArr[0];
            boolean taskIsDone = (noteArr[1]).equals("true")  ? true : false;
            String taskDesc = noteArr[2];

            if(noteType.equals("T")) {
                taskList.add(new Todo(taskDesc, taskIsDone));
            } else if(noteType.equals("E")) {
                if(noteArr.length == 5) {
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), LocalTime.parse(noteArr[4]),
                            taskIsDone));
                } else if(noteArr.length == 4) {
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), taskIsDone));
                }
            } else {
                if(noteArr.length == 5) {
                    taskList.add(new Deadline(taskDesc, LocalDate.parse(noteArr[3]), LocalTime.parse(noteArr[4]),
                            taskIsDone));
                } else if(noteArr.length == 4){
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), taskIsDone));
                }
            }
        }
        return taskList;
    }

    private static String arrayToFile(TaskList tasks) {
        ArrayList<Task> taskList = tasks.taskList;
        String outputToFile = "";
        for(Task task : taskList) {
            String taskType = task.getClass().getSimpleName();
            String taskDesc = task.getDescription();
            boolean taskIsDone = task.isDone;

            if (taskType.equals("Todo")) {
                outputToFile += "T>" + taskIsDone + ">" + taskDesc + "\n";
            } else if (taskType.equals("Event")) {
                if (((Event) task).isTime) {
                    outputToFile += "E>" + taskIsDone + ">" + taskDesc + ">" + ((Event) task).getDate() + ">"
                            + ((Event) task).getTime() + "\n";
                } else {
                    outputToFile += "E>" + taskIsDone + ">" + taskDesc + ">" + ((Event) task).getDate() + "\n";
                }
            } else {
                if (((Deadline) task).isTime) {
                    outputToFile += "D>" + taskIsDone + ">" + taskDesc + ">" + ((Deadline) task).getDate() + ">"
                            + ((Deadline) task).getTime() + "\n";
                } else {
                    outputToFile += "D>" + taskIsDone + ">" + taskDesc + ">" + ((Deadline) task).getDate() + "\n";
                }
            }
        }
        return outputToFile;
    }


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToAdd);
        bw.close();
    }


    public void saveFile(TaskList taskList){
        try {
            writeToFile(filePath, arrayToFile(taskList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




}
