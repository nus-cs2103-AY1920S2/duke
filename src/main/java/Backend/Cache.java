package Backend;

import Backend.Constants.Commands;

import java.util.*;

public class Cache {

    private final int MAX = 20;
    public Deque<String> userInputHistory;

    /**
     * Caches minimal data needed during the runtime of the app.
     * For the user input history, uses a double-ended queue data structure.
     */
    public Cache(){
        userInputHistory = new ArrayDeque<>();
    }

    /**
     * Adds a user input string into a list of user input strings.
     * If queue is at maximum capacity, remove the last element of the queue and enqueue the current user input.
     * @param userInput most recent user input
     */
    public void addUserInput( String userInput ){
        if (userInputHistory.size() >= MAX) {
            userInputHistory.pollLast();
        }

        userInputHistory.offerFirst(userInput);
    }

    /**
     * Converts the user input history to an array and returns the element at specified index.
     * @param index index of the element to be retrieved
     * @return element of the queue at specified index
     */
    public String getUserInputHistory( int index ){
        Object[] arr = userInputHistory.toArray();
        return (String) arr[index];
    }

    public int getUserInputHistorySize(){
        return userInputHistory.size();
    }

    /**
     * Pretty-prints the user input history.
     * @return pretty-printed string of the user input history
     */
    public String printUserInputHistory(){
        Object[] arr = userInputHistory.toArray();

        StringBuilder userInputHistoryString = new StringBuilder();

        int j = 0;
        for( int i = arr.length - 1; i > 0; i-- ){
            userInputHistoryString.append( i ).append(". ").append( arr[i] ).append( "\n" );
        }

        return userInputHistoryString.toString();
    }


}
