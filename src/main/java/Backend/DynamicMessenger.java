package Backend;

import java.util.List;

import Backend.Constants.Messages;
import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Task;
import Backend.Parsers.DateParser;
import Backend.Parsers.TimeOfDay;

/**
 * A utility class for dynamic messages.
 */
public class DynamicMessenger {

        public static String sayHello(){
            TimeOfDay timeOfDay =  DateParser.getTimeOfDay();

            switch ( timeOfDay ){
                case MORNING:
                    return Messages.GOOD_MORNING_MSG;
                case AFTERNOON:
                    return Messages.GOOD_AFTERNOON_MSG;
                case EVENING:
                    return Messages.GOOD_EVENING_MSG;
            }

            return "";
        }

        public static String sayHelp() throws DukeException{
            return Storage.loadHelpFromFile();
        }

        public static String sayMarkedTaskAsDone( Task task ){
            return Messages.MARK_AS_DONE_MSG + task.toString();
        }

        public static String sayTaskAlreadyDone( Task task ){
            return Messages.TASK_ALREADY_DONE_MSG + task.toString();
        }

        public static String sayAddedTask( Task task ){
            return Messages.ADDED_TASK_MSG + task.toString();
        }

        public static String sayRemovedTask( Task task ){
            return Messages.REMOVED_TASK_MSG + task.toString();
        }

        public static String sayTaskList( List<Task> list ){

            if( list.size() == 0 ){
                return Messages.NO_TASKS_MSG;
            } else {

                StringBuilder str = new StringBuilder();
                str.append( Messages.LIST_TASKS_MSG );

                for( Task task: list ){
                    str.append( task.toString() ).append( "\n" );
                }

                return str.toString();
            }
        }
}
