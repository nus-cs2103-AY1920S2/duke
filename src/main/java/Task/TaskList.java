package Task;

import Utils.Parser;
import Utils.Storage;

import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TaskList {

    public List<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public TaskList(String data){
        this.list = new ArrayList<>();
        restoreFromExisting(data);
    }


    private void restoreFromExisting(String data){

        String[] dataArray = data.split("\n");

        int i = 1;
        for( String line : dataArray ){
            Task task = Parser.parseTask(line, i);
            list.add(task);
            i++;
        }

    }

    private void saveList() throws DukeException {

        String data = "";

        for ( Task task : this.list ){
            data += task.toString() + "\n";
        }

        Storage.writeToFile(data);

    }

    public void printTasks(){
        if( this.list.size() == 0 ){
            System.out.println("You have no tasks in your list.");
        } else {

            for( Task task: list ){
                System.out.println(task);
            }

        }
    }

    public void printTasksOn(String req) throws DukeException {

        try {
            Parser parser = new Parser(req);
            DukeDate date = new DukeDate(parser.getDateString());

            System.out.println("Here are your tasks for " + date.getDateString());

            for( Task task: list ){
                if(task.date != null && task.date.getDateString().equals(date.getDateString())){
                    System.out.println(task);
                }
            }

        } catch (DateTimeParseException e){
            throw new DukeException("Date must be in the form YYYY-MM-DD!");
        }

    }

    /**
     * deletes task
     * @param taskIndex index of task to be deleted
     * @throws DukeException if no task found for index
     */
    public void deleteTask(int taskIndex) throws DukeException {
        try {

            Task task = list.get(taskIndex - 1);

            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            this.list.remove(task);

            System.out.println("\nNow you have " + list.size() + "task(s) in the list");

        } catch ( IndexOutOfBoundsException e){
            throw new DukeException("No task found for index " + taskIndex + "!");
        }
    }

    /**
     * adds task to list
     * @param req user input
     * @throws DukeException if no date
     */
    public void addTask(String req) throws DukeException {

        Parser parser = new Parser(req);

        int index = this.list.size() + 1;

        Task task;

        String command = parser.getCommand();
        String content = parser.getContent();

        switch( command ){
            case "event":
                DukeDate date = new DukeDate(parser.getDateString());
                task = new Event(content, index, date);
                break;
            case "deadline":
                date = new DukeDate(parser.getDateString());
                task = new Deadline(content, index, date);
                break;
            default:
                task = new Todo(content, index);
        }

        this.list.add(task);
        this.saveList();
        System.out.println(task.content);
    }

    public void findTask(String req){

        Parser parser = new Parser(req);

        String searchTerm = parser.getContent();

        Pattern p = Pattern.compile( searchTerm );

        System.out.println("Here's what I found my lord:");

        for ( Task task : list ){

            String[] wordArray = task.content.split(" ");

            for (String word : wordArray ){

                Matcher m = p.matcher( word.trim() );

                if( m.matches() ){
                    System.out.println(task.toString());
                    break;
                }

            }

        }

    }

    /**
     * marks task as done
     * @param taskIndex index of task to be marked as done
     * @throws DukeException if index out of bounds
     */
    public void markTask(int taskIndex) throws DukeException {

        try {
            Task task = this.list.get(taskIndex - 1);

            task.markAsDone();

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);

            saveList();

        } catch( IndexOutOfBoundsException e){
            throw new DukeException("Task.Task Index is invalid");
        }


    }


}
