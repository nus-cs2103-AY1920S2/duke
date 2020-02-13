package Backend;

import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Task;
import Backend.Parsers.DateParser;
import Backend.Parsers.Parser;

import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * Parses a string of data into separate tasks and loads up the task list
     * @param data data string to be parsed into tasks and loaded into the task list
     * @throws DukeException exception rethrown from parseTask
     */
    public void loadTasks ( String data ) throws DukeException{

        String[] dataArray = data.split("\n");

        int i = 1;

        for( String line : dataArray ){
            Task task = Parser.formatTaskString( line, i );
            list.add( task );
            i++;
        }

    }

    /**
     * Saves a list of tasks as a string in a file
     * @throws DukeException exception rethrown from writeToFile
     */
    private void saveList() throws DukeException {

        StringBuilder data = new StringBuilder();

        for ( Task task : this.list ){
            data.append( task.toString() ).append( "\n" );
        }

        Storage.writeDataToFile( data.toString() );

    }

    public int getLength(){
        return list.size();
    }

    public List<Task> getList(){
        return list;
    }

    public String printTasks(){
        return ChatterBox.sayTaskList( list );
    }


    public String printTasks(String req) throws DukeException {

        try {

            Parser parser = new Parser(req);
            DateParser date = new DateParser(parser.parseDateString());

            List<Task> filteredTaskList = list.stream()
                                            .filter( task -> task.date != null && task.date.getDateString().equals(date.getDateString()))
                                            .collect(Collectors.toList());

            return ChatterBox.sayTaskList( filteredTaskList );

        } catch ( DateTimeParseException e ) {
            throw new DukeException(e);
        }

    }

    /**
     * deletes task
     * @param taskIndex index of task to be deleted
     * @throws DukeException if no task found for index
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        try {
            Task task = list.get(taskIndex - 1);

            this.list.remove(task);

            this.reindexList();

            this.saveList();

            return task;

        } catch ( IndexOutOfBoundsException e){
            throw new DukeException(e);
        }
    }

    private void reindexList(){

        int index = 1;

        for( Task task: list ){
            task.indexTask( index );
            index++;
        }

    }

    /**
     * adds task to list
     * @param task task to be added
     * @throws DukeException if no date
     */
    public Task addTask(Task task) throws DukeException {

        task.indexTask( this.list.size() + 1 );
        this.list.add( task );
        this.saveList();

        return task;
    }

    public String findTask(String req){

        Parser parser = new Parser(req);
        String searchTerm = parser.parseContent();
        Pattern p = Pattern.compile( searchTerm );
        List<Task> foundTasks = new ArrayList<>(  );

        for ( Task task : list ){

            String[] wordArray = task.content.split(" ");

            for (String word : wordArray ){

                Matcher m = p.matcher( word.trim() );

                if( m.matches() ){
                    foundTasks.add(task);
                }

            }

        }

        if( foundTasks.size() == 0 ){
            return ChatterBox.sayNotFound();
        } else {
            return ChatterBox.sayFound( foundTasks );
        }

    }

    /**
     * marks task as done
     * @param taskIndex index of task to be marked as done
     * @throws DukeException if index out of bounds
     */
    public Task markTask(int taskIndex) throws DukeException {

        try {
            Task task = this.list.get(taskIndex - 1);

            task.markAsDone();

            saveList();

            return task;

        } catch( IndexOutOfBoundsException e){
            throw new DukeException(e);
        }


    }


}
