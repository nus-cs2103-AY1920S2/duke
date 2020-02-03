import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class Duke {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<String> tasksSaved = new ArrayList<>();

        //File file = new File("/Users/kerwei/Desktop/Jessica/src/main/tasks/task.rtf");
        String fileName = "/Users/kerwei/Desktop/Jessica/src/main/tasks/task.txt";
        //greetings
        System.out.println("Harlo Sir, how may i help you?");

        //read in saved tasks
        try {
            listOfTasks = loadTask(fileName);
            tasksSaved = copySavedTasks(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("no such file la idiot");
        }
        //echo
        String input = sc.nextLine();
        String[] inputDetails = handleInput(input);
        String typeOfTask = inputDetails[0];
        String detailOfTask = "";
        String dateTime = "";
        while (!typeOfTask.equals("bye")) {
            try {
                switch (typeOfTask) {
                    case ("list"):
                        int i = 1;
                        for (Task t : listOfTasks) {
                            System.out.println(i + ". " + t);
                            i++;
                        }
                        break;
                    case ("todo"):
                        try{
                            if (inputDetails[1] == null) {
                                throw new NullPointerException("error");
                            } else {
                                detailOfTask = inputDetails[1];
                                Todo newTask = new Todo(typeOfTask, detailOfTask, "");
                                listOfTasks.add(newTask);
                                tasksSaved.add(typeOfTask + " | " + newTask.isDone + " | " + detailOfTask + "  |  ");
                                System.out.println("added: " + newTask);
                            }
                        } catch (NullPointerException error){
                            throw new DukeException("need description for todo to be added la idiot");
                        }
                        break;
                    case ("deadline"):
                        try {
                            if (inputDetails[1] == null || inputDetails[2] == null) {
                                throw new NullPointerException("error");
                            } else {
                                detailOfTask = inputDetails[1];
                                dateTime = inputDetails[2];
                                Deadline newTask = new Deadline(typeOfTask, detailOfTask, dateTime);
                                listOfTasks.add(newTask);
                                tasksSaved.add(typeOfTask + " | " + newTask.isDone + " | " + detailOfTask + " | " + dateTime);
                                System.out.println("added: " + newTask);
                            }
                        } catch (NullPointerException error){
                            throw new DukeException("need description and date/ time for deadline to be added la idiot");
                        }
                        break;
                    case ("event"):
                        try {
                            if (inputDetails[1] == null || inputDetails[2] == null) {
                                throw new NullPointerException("error");
                            } else {
                                detailOfTask = inputDetails[1];
                                dateTime = inputDetails[2];
                                Event newTask = new Event(typeOfTask, detailOfTask, dateTime);
                                listOfTasks.add(newTask);
                                tasksSaved.add(typeOfTask + " | " + newTask.isDone + " | " + detailOfTask + " | " + dateTime);
                                System.out.println("added: " + newTask);
                            }
                        } catch (NullPointerException error){
                            throw new DukeException("need description and date/ time for event to be added la idiot");
                        }
                        break;
                    case ("done"):
                        try {
                            if (inputDetails[1] == null) {
                                throw new NullPointerException("error");
                            } else {
                                detailOfTask = inputDetails[1];
                                int index = Integer.parseInt(detailOfTask) - 1;
                                Task currentTask = listOfTasks.get(index);
                                currentTask.isDone = true;
                                tasksSaved.set(index, currentTask.type + " | " + currentTask.isDone + " | " +
                                        currentTask.description + " | " + currentTask.dateTime);
                                System.out.println("Good job on getting this done!");
                                System.out.println(currentTask);
                            }
                        } catch (NullPointerException error){
                            throw new DukeException("which task are you done with idiot");
                        }
                        break;
                    case ("delete"):
                        try {
                            if (inputDetails[1] == null) {
                                throw new NullPointerException("error");
                            } else {
                                detailOfTask = inputDetails[1];
                                Task currentTask = listOfTasks.get(Integer.parseInt(detailOfTask) - 1);
                                System.out.println("alright bye bye task");
                                System.out.println(currentTask);
                                listOfTasks.remove(Integer.parseInt(detailOfTask) - 1);
                                tasksSaved.remove(Integer.parseInt(detailOfTask) - 1);
                            }
                        } catch (NullPointerException error){
                            throw new DukeException("which task do you want to delete idiot");
                        }
                        break;
                    default:
                        throw new DukeException("dont anyhow type la idiot");
            }
            } catch (DukeException error) {
                    System.out.println(error);
            }
            input = sc.nextLine();
            inputDetails = handleInput(input);
            typeOfTask = inputDetails[0];
        }
        //bye
        try {
            saveTask(fileName, tasksSaved);
        } catch (FileNotFoundException error){
            System.out.println("no file to write to leh");
        }
        System.out.println("See you again NEVER!!");
    }

    public static String[] handleInput(String input){
        String[] task = input.split(" ", 2);
        String[] inputDetails = new String[3];
        inputDetails[0] = task[0];
        if (input.contains(" ")) {
            String[] taskDetails = task[1].split("/");
            inputDetails[1] = taskDetails[0];
            if (task[1].contains("/")){
                inputDetails[2] = taskDetails[1];
            } else {
                inputDetails[2] = "date/ time not specific";
            }
        }
        return inputDetails;
    }

    public static ArrayList<String> copySavedTasks(String file) throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanIn = new Scanner(fileInputStream);
        ArrayList<String> tasksSaved = new ArrayList<>();
        while (scanIn.hasNext()) {
            tasksSaved.add(scanIn.next());
        }
        return tasksSaved;
    }

    public static ArrayList<Task> loadTask(String file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanIn = new Scanner(fileInputStream);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (scanIn.hasNext()){
            String savedTask = scanIn.next();
            String[] splitTask = savedTask.split(" | ", 0);
            String task = splitTask[0];
            switch (task) {
                case ("todo"):
                    Todo todo = new Todo(splitTask[0], splitTask[2], "");
                    todo.isDone = Boolean.parseBoolean(splitTask[1]);
                    listOfTasks.add(todo);
                    break;
                case ("deadline"):
                    Deadline deadline = new Deadline(splitTask[0], splitTask[2], splitTask[3]);
                    deadline.isDone = Boolean.parseBoolean(splitTask[1]);
                    listOfTasks.add(deadline);
                    break;
                case ("event"):
                    Event event = new Event(splitTask[0], splitTask[2], splitTask[3]);
                    event.isDone = Boolean.parseBoolean(splitTask[1]);
                    listOfTasks.add(event);
                    break;
            }

        }
        for (Task t : listOfTasks) {

        }
        return listOfTasks;

    }

    public static void saveTask(String file, ArrayList<String> tasksSaved) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(fileOutputStream);
        for (String s : tasksSaved) {
            writer.println(s);
        }
        writer.close();
    }
}
