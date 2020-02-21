package Backend;

import Backend.Constants.AdvCommands;
import Backend.Constants.Commands;
import Backend.Constants.Messages;
import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Deadline;
import Backend.Objects.Task.Event;
import Backend.Objects.Task.Todo;
import Backend.Parsers.DateParser;
import Backend.Parsers.Parser;
import Backend.Objects.Task.Task;

import java.util.Optional;

public class Switcher {

    private TaskList taskList;
    private Cache cache;

    /**
     * Contains the logic required to interpret commands
     * @param taskList the task list instantiated in the main function
     * @param cache the cache instantiated in the main function
     */
    public Switcher( TaskList taskList, Cache cache ){
        this.cache = cache;
        this.taskList = taskList;
    }

    /**
     * Decides what action is to be executed based on the user input.
     * Contains two switches. First switch deals with commands that do not manipulate the data while the second deals with commands
     * that manipulate the data.
     * @param req request of the user from a frontend component
     * @return a string representing the reply to the user containing the relevant requested information
     */
    public String res(String req) throws DukeException {

        if( req.equals( Commands.EXIT )){
            return Messages.EXIT_MSG;
        }

        if( req.equals(Commands.BYE) ){
            return Messages.BYE_MSG;
        }

        /**
         * Prints what is requested and does no data manipulation
         */
        try {
            Parser parser = new Parser(req);
            String command = parser.parseCommand();

            switch( command ){
                case Commands.HISTORY:
                    return cache.printUserInputHistory();
                case Commands.HELP:
                    return DynamicMessenger.sayHelp();
                case Commands.LIST:
                case AdvCommands.LIST:
                    return taskList.printTasks();
                case Commands.DATE:
                    DateParser date = new DateParser(parser.parseDateString());
                    return taskList.printTasksByDate(date);
                case Commands.FIND:
                case AdvCommands.FIND:
                    String searchTerm = parser.parseContent();
                    return taskList.printTasksBySearchTerm(searchTerm);
            }

            Optional<Integer> indexOptional = parser.parseContentAsInt();

            int index = 0;

            if( indexOptional.isPresent() ){
                index = indexOptional.get();
            }

            /**
             * Manipulates the data
             */
            switch( command ) {
                case Commands.DONE:
                    Task taskToBeMarked = taskList.getTask( index );

                    if( taskToBeMarked.done ){
                        return DynamicMessenger.sayTaskAlreadyDone( taskToBeMarked );
                    } else {
                        taskToBeMarked = taskList.markTask( index );
                        return DynamicMessenger.sayMarkedTaskAsDone( taskToBeMarked );
                    }

                case Commands.DELETE:
                case AdvCommands.DELETE:
                    Task removedTask = taskList.deleteTask(index);
                    return DynamicMessenger.sayRemovedTask(removedTask);
                default:
                    String content = parser.parseContent();
                    Task task;

                    switch( command ) {
                        case Commands.EVENT:
                            task = new Event( content, new DateParser(parser.parseDateString() ));
                            break;
                        case Commands.DEADLINE:
                            task = new Deadline(content, new DateParser(parser.parseDateString()));
                            break;
                        case Commands.TODO:
                            task = new Todo( content );
                            break;
                        default:
                            throw new DukeException( new StringIndexOutOfBoundsException()  );
                    }

                    taskList.addTask(task);
                    return DynamicMessenger.sayAddedTask( task );
            }

        } catch (StringIndexOutOfBoundsException e){

                throw new DukeException(e);

        }



    }

}









