package core;

import java.io.Serializable;
import java.util.Stack;

/**
 * Holds history of data in the system.
 */
public class StateHolder implements Serializable {

    private Stack<State> states;
    private Stack<State> undoHolder;

    /**
     * Constructor to initialize states array and array to hold the undo states.
     */
    public StateHolder(){
        states=new Stack<>();
        undoHolder=new Stack<>();
    }

    /**
     * Clear all undo states before adding new state.
     * @param state new state to be added.
     */
    public void addNewState(State state){
        undoHolder.clear();
        states.add(state);
    }

    /**
     * Obtain current state.
     * @return current state.
     */
    public State getCurrentState(){
        assert !states.isEmpty();
        return states.peek();
    }

    /**
     * Obtain previous state.
     * @return previous state.
     */
    //undo
    public State getPreviousState(){
        assert !states.isEmpty();
        undoHolder.push(states.pop());
        return states.peek();
    }

    /**
     * Revert undo and obtain state before undo.
     * @return state before undo.
     */
    //redo
    public State getNextState(){
        assert !undoHolder.isEmpty();
        states.push(undoHolder.pop());
        return states.peek();
    }

}
