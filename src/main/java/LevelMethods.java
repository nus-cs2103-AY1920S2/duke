import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelMethods {
    List<Task> storingList = new ArrayList<>();
    String filePath;

    public LevelMethods() {
        //no stuff
    }

    /**
     * Display format
     *
     * @param contentStr
     */
    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        String lines[] = contentStr.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.println("      " + lines[i]);
        }

        //System.out.println(contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    /**
     * Greet the user
     */
    public void greetings() {
        String intro = "Hello! I'm Grapie! \n"
                + "   _____                 _      \n"
                + "  / ____|               (_)     \n"
                + " | |  __ _ __ __ _ _ __  _  ___ \n"
                + " | | |_ | '__/ _` | '_ \\| |/ _ \\ \n"
                + " | |__| | | | (_| | |_) | |  __/ \n"
                + "  \\_____|_|  \\__,_| .__/|_|\\___| \n"
                + "                  | |           \n"
                + "                  |_|           \n"


                + "What do ya need from me?\n";

        formattingDivider(intro);
    }

    public void printAddingTask(Task task) {
        String printStr = "Alrighty. I've added this task: \n"
                + task + "\n"
                + "Now you have " + storingList.size() + " tasks in the list.";

        formattingDivider(printStr);
    }

    public void createStorage() throws IOException {


        //trying to get path???
        //filePath = new File("").getAbsolutePath();
        //System.out.println("1. " + filePath);
        //filePath = filePath + "\\data\\dukeStorage";
        //System.out.println("2. " + filePath);

        filePath = "data/dukeStorage.txt";

        //java.nio.file.Path path = java.nio.file.Paths.get(home, "my", "app", "dir")

        String home = System.getProperty("user.home");


        File file = new File(filePath);
        //boolean exists = tmpDir.exists();

        file.createNewFile();
    }

    public void updateListFromFile() throws IOException, GrapieExceptions {
//        String str = "Hello";
//        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
//        writer.write(str);
//
//        writer.close();


        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            //System.out.println("data is: " + data);
            //System.out.println(data);

            /*
            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            T | 1 | join sports club
            */

            String[] dataSplited = data.split("\\|");

            //trim off empty spaces at front and back of string
            for (int i = 0; i < dataSplited.length; i++) {
                dataSplited[i] = dataSplited[i].trim();
            }
            //System.out.println(dataSplited.length);

            if (dataSplited.length == 3) {
                //is a todo
                Task task = new Todo(dataSplited[2]);
                if (dataSplited[1].equals("O")) {
                    task.isDone = true;
                }
                storingList.add(task);
                //System.out.println(task);

            } else if (dataSplited.length == 4) {
                //is a event or deadline
                if (dataSplited[0].equals("E")) {
                    //event
                    Event task = new Event(dataSplited[2], dataSplited[3]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
                    }
                    storingList.add(task);
                    //System.out.println(task);

                } else {
                    //deadline
                    Deadline task = new Deadline(dataSplited[2], dataSplited[3]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
                    }
                    storingList.add(task);
                    //System.out.println(task);
                }
            }
        }

        //System.out.println("size of storing list is now: " + storingList.size());
    }

    public void convertToHardDiskFormatAndStore(Task task, String type, String time) throws IOException {
            /*
            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            T | 1 | join sports club
            */

            /*
            [T][O] read book
            [D][X] return book (by: June 6th)
            [E][X] project meeting (at: Aug 6th 2-4pm)
            [T][O] join sports club
             */

        String doneOrNotDone = "";
        if (task.isDone) {
            doneOrNotDone += "O";
        } else {
            doneOrNotDone += "X";
        }

        String newDescription = "";
        if (type.equals("T")) {

            newDescription += type + " | " + doneOrNotDone + " | " + task.description;
        } else {
            //event & deadline
//                String doneOrNotDone = description.substring(4,5);
//                String todoDescription = description.substring(7, description.length());

            newDescription += type + " | " + doneOrNotDone + " | " + task.description + " | " + time;
        }

        File file = new File(filePath);
        FileWriter fr = new FileWriter(file, true);

        //System.out.println("size of list is: " + storingList.size());
        if (storingList.size() == 1) {
            //System.out.println("nou");
            fr.write(newDescription);
        } else {
            //System.out.println("fk");
            fr.write("\n" + newDescription);

        }

        //System.out.println("size of list is: " + storingList.size());

        Scanner myReader = new Scanner(file);

        fr.close();
    }


    /**
     * Grapie's replies
     *
     * @param inputStr
     */
    public void echo(String inputStr) throws GrapieExceptions, IOException {
        if (inputStr.contains("todo")) {
            if (inputStr.substring(0, 4).equals("todo") && inputStr.length() > 5) {
                String detailsStr = inputStr.substring(5, inputStr.length());

                String checkIfTodoIsEmpty = detailsStr.replaceAll("\\s", "");

                if (checkIfTodoIsEmpty.length() == 0) {
                    //That means it is empty behing todo
                    throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
                } else {

                    Task todo = new Todo(detailsStr);
                    storingList.add(todo);
                    printAddingTask(todo);

                    //store into hard disk
                    convertToHardDiskFormatAndStore(todo, "T", "");

                }
            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }

        } else if (inputStr.contains("event")) {
            if (inputStr.substring(0, 5).equals("event") && inputStr.length() > 6) {
                String[] eventAndTime = inputStr.substring(6, inputStr.length()).split(" /at ");

                if (eventAndTime.length <= 1) {
                    //not able to split string properly
                    throw new GrapieExceptions(ErrorMsg.wrongFormatError("event your_event /at YYYY-MM-DD"));
                } else {

                    Event event = new Event(eventAndTime[0], eventAndTime[1]);
                    storingList.add(event);

                    //printing
                    printAddingTask(event);


                    //store into hard disk
                    convertToHardDiskFormatAndStore(event, "T", event.time);

                }

            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }

        } else if (inputStr.contains("deadline")) {
            if (inputStr.substring(0, 8).equals("deadline") && inputStr.length() > 9) {

                String[] eventAndTime = inputStr.substring(9, inputStr.length()).split(" /by ");

                if (eventAndTime.length > 1) {

                    Deadline deadline = new Deadline(eventAndTime[0], eventAndTime[1]);
                    storingList.add(deadline);

                    //print
                    printAddingTask(deadline);

                    //store into hard disk
                    convertToHardDiskFormatAndStore(deadline, "T", deadline.time);
                } else {
                    //"OOPS!!! Deadline in wrong format. Please use: deadline your_deadline /by YYYY-MM-DD TTTT"
                    throw new GrapieExceptions(ErrorMsg.wrongFormatError("deadline your_deadline /by YYYY-MM-DD"));
                }

            } else {
                throw new GrapieExceptions(ErrorMsg.emptyDescriptionError);
            }
        } else {
            throw new GrapieExceptions(ErrorMsg.wakarimasenError);
        }

//        Task task = new Task(inputStr);
//        storingList.add(task);

    }

    public boolean isNumber(String numStr) {
        try {
            Integer.parseInt(numStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void replaceLineFromHardDisk(int lineNumber) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        int counter = 1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            //System.out.println(data);
            if (counter == lineNumber) {
                data = data.substring(0, 4) + "O" + data.substring(5, data.length());
            }

            if (counter == 1) {
                newData += data;
            } else {
                newData += "\n" + data;
            }

            counter++;
        }

//        System.out.println(newData);

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }

    public void completeTask(String doneTaskStr) throws GrapieExceptions, IOException {
        if (doneTaskStr.length() <= 5) {
            //no number behind
            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
        } else {
            //remember to add check for already completed task

            String strNumberDone = doneTaskStr.substring(5, doneTaskStr.length());
            strNumberDone.replaceAll("\\s+", ""); //remove all white spaces

            boolean isANumber = isNumber(strNumberDone);

            if (isANumber) {
                int numDone = Integer.parseInt(strNumberDone); //convert to number

                if (storingList.size() >= numDone && numDone != 0) {

                    if (storingList.get(numDone - 1).isDone) {
                        //if already true
                        throw new GrapieExceptions(ErrorMsg.taskNumIsAlreadyDone(numDone));
                    } else {

                        storingList.get(numDone - 1).isDone = true;
                        //storingList.set(taskNum - 1, updatedTask);

                        String printStr = "Nice! I've marked this task as done: \n" + storingList.get(numDone - 1);
                        formattingDivider(printStr);

                        //update hard disk :(
                        replaceLineFromHardDisk(numDone);
                    }

                } else {
                    throw new GrapieExceptions(ErrorMsg.numberDoNotExistError(numDone));
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.invalidNumberError);
            }
        }

    }

    public void deleteLineFromHardDisk(int lineNumber) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        boolean firstLineDone = false;
        int counter = 1;

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (counter == lineNumber) {
                counter++;
            } else {
                if (!firstLineDone) {
                    newData += data;
                    firstLineDone = true;
                } else {
                    newData += "\n" + data;
                }

                counter++;
            }
        }

//        System.out.println(newData);

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }

    public void deleteTask(String inputStr) throws GrapieExceptions, IOException {
        if (inputStr.length() <= 7) {
            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
        } else {
            String strNumberDeleted = inputStr.substring(7, inputStr.length());
            strNumberDeleted.replaceAll("\\s+", ""); //remove all white spaces

            boolean isANumber = isNumber(strNumberDeleted);

            if (isANumber) {
                int numToDelete = Integer.parseInt(strNumberDeleted);

                if (storingList.size() >= numToDelete) {

                    int newSize = storingList.size() - 1;
                    String toPrint = " Alrighty. I've removed this task: \n"
                            + storingList.get(numToDelete - 1)
                            + "\n Now you have " + newSize + " tasks in the list.";

                    storingList.remove(numToDelete - 1);

                    formattingDivider(toPrint);

                    //delete from hard disk
                    deleteLineFromHardDisk(numToDelete);

                } else {
                    throw new GrapieExceptions(ErrorMsg.numberDoNotExistError(numToDelete));
                }
            } else {
                throw new GrapieExceptions(ErrorMsg.invalidNumberError);
            }
        }
    }

    public void listTheList() {
        int sizeOfList = storingList.size();
        String stringList = "Here are the tasks in your list: \n";

        for (int i = 1; i <= sizeOfList; i++) {
            stringList = stringList + "" + i + ". " + storingList.get(i - 1) + "\n"; //add tasks
        }

        formattingDivider(stringList);
    }


    public void sayonara() {
        formattingDivider("Okie!! Goodbye!");
    }

}
