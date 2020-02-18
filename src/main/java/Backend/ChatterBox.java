package Backend;

import java.util.List;

import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Task;
import Backend.Parsers.DateParser;
import Backend.Parsers.TimeOfDay;

/**
 * A utility class for storing and returning responses
 */
public class ChatterBox {

        private final static String goodMorningMsg = "Good morning sir. Hope you slept well.";
        private final static String goodAfternoonMsg = "Good afternoon sir. Had your lunch?";
        private final static String goodEveningMsg = "Good evening sir. Hope you had a great day.";
        private final static String exitMsg = "I'll be here eagerly waiting your arrival. Goodbye.";
        private final static String byeMsg = "If you really have to leave sir, please type 'exit'.";
        private final static String notFoundMsg = "I looked long and hard but couldn't find what you were looking for. A different search term perhaps?";
        private final static String foundMsg = "Here's what you were looking for sir:\n";
        private final static String markAsDoneMsg = "Great job sir!\n";
        private final static String taskAlreadyDoneMsg = "This task was already completed sir.\n";
        private final static String addedTaskMsg = "Nicely done sir. I've added your task.\n";
        private final static String removedTaskMsg = "I assume you've completed this sir... I've removed it from the list.\n";
        private final static String noTasksMsg = "You currently have no tasks sir.";
        private final static String listTasksMsg = "Here are your tasks sir:\n";


        public static String sayHello(){
            TimeOfDay timeOfDay =  DateParser.getTimeOfDay();

            switch ( timeOfDay ){
                case MORNING:
                    return goodMorningMsg;
                case AFTERNOON:
                    return goodAfternoonMsg;
                case EVENING:
                    return goodEveningMsg;
            }

            return "";
        }

        public static String sayBye(){
            return byeMsg;
        }

        public static String sayExit(){
            return exitMsg;
        }

        public static String sayNotFound(){
            return notFoundMsg;
        }

        public static String sayHelp() throws DukeException{
            return Storage.loadHelpFromFile();
        }

        public static String sayFound( List<Task> Tasks ){

            StringBuilder str = new StringBuilder();

            str.append(foundMsg);

            for ( Task task: Tasks ){
                str.append( task.toString() );
                str.append( "\n" );
            }

            return str.toString();

        }

        public static String sayMarkedTaskAsDone( Task task ){
            return markAsDoneMsg + task.toString();
        }

        public static String sayTaskAlreadyDone( Task task ){
            return taskAlreadyDoneMsg + task.toString();
        }

        public static String sayAddedTask( Task task ){
            return addedTaskMsg + task.toString();
        }

        public static String sayRemovedTask( Task task ){
            return removedTaskMsg + task.toString();
        }

        public static String sayTaskList( List<Task> list ){

            if( list.size() == 0 ){
                return noTasksMsg;
            } else {

                StringBuilder str = new StringBuilder();
                str.append( listTasksMsg );

                for( Task task: list ){
                    str.append( task.toString() ).append( "\n" );
                }

                return str.toString();
            }
        }
}
