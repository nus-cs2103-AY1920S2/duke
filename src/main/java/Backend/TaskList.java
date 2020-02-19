package Backend;

import Backend.Constants.Messages;
import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Task;
import Backend.Parsers.DateParser;
import Backend.Parsers.Parser;

import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * Parses a string of data into separate tasks and loads up the task list.
     * @param data data string to be parsed into tasks and loaded into the task list
     * @throws DukeException exception rethrown from parseTask
     */
    public void loadTasks ( String data ) throws DukeException{

        String[] dataArray = data.split("\n");

        int i = 1;

        for( String line : dataArray ) {
            Task task = Parser.parseSavedTask( line, i );
            list.add( task );
            i++;
        }


    }

    /**
     * Saves a list of tasks as lines in a file.
     * Each task is written to a file that can be loaded from upon restarting the app.
     */
    private void saveList() throws DukeException {

        StringBuilder data = new StringBuilder();

        for ( Task task : this.list ){

            data.append( task.toString() ).append( "\n" );
        }

        Storage.writeDataToFile( data.toString() );

    }

    public List<Task> getList(){
        return list;
    }

    /**
     * Gets the task in the list by its index.
     * @param taskIndex the number the task is indexed as to the user, not the actual index of the task in the list.
     * @return the task at index - 1 in the list.
     */
    public Task getTask( int taskIndex ){
        return list.get(taskIndex - 1);
    }

    public String printTasks(){
        return DynamicMessenger.sayTaskList( list );
    }


    /**
     * Prints the task list by streaming the list through a date filter.
     * @param date the date to filter the tasks by
     * @return a pretty-printed string of the tasks, filtered by date, in the task list
     */
    public String printTasksByDate(DateParser date) throws DukeException {

        try {

            List<Task> filteredTaskList = list.stream()
                                            .filter( task -> task.date != null && task.date.getDateString().equals(date.getDateString()))
                                            .collect(Collectors.toList());

            return DynamicMessenger.sayTaskList( filteredTaskList );

        } catch ( DateTimeParseException e ) {
            throw new DukeException(e);
        }

    }

    /**
     * Prints the task list by streaming the list through a search term filter.
     * A task is determined to match if the search term matches one or more words in the task's content.
     * @param searchTerm the search term to filter the tasks by.
     * @return a pretty-printed string of the tasks, filtered by search term, in the task list
     */
    public String printTasksBySearchTerm( String searchTerm ){

        Pattern p = Pattern.compile( searchTerm );

        List<Task> filteredTaskList = list.stream()
                                        .filter( task -> {
                                            String[] wordArray = task.content.split(" ");

                                            boolean isMatch = false;

                                            for (String word : wordArray ){

                                                Matcher m = p.matcher( word.trim() );

                                                isMatch = m.matches();

                                            }

                                            return isMatch;
                                        })
                                        .collect( Collectors.toList());

        if( filteredTaskList.size() == 0 ){
            return Messages.NOT_FOUND_MSG;
        } else {
            return DynamicMessenger.sayTaskList( filteredTaskList );
        }

    }

    /**
     * Deletes a task from the task list by its index.
     * @param taskIndex the number the task is indexed as to the user, not the actual index of the task in the list.
     * @return the task that was deleted.
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

    /**
     * Re-indexes a list to ensure that each task is indexed consecutively.
     */
    private void reindexList(){

        int index = 1;

        for( Task task: list ){
            task.indexTask( index );
            index++;
        }

    }

    /**
     * Adds a task to the list.
     * @param task the task to be added
     */
    public void addTask(Task task) throws DukeException {
        task.indexTask( this.list.size() + 1 );
        this.list.add( task );
        this.saveList();
    }

    /**
     * Marks a task as done by its index.
     * @param taskIndex the number the task is indexed as to the user, not the actual index of the task in the list.
     * @return the task that was marked as done
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
