package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class StateHolder implements Serializable {

    private Stack<State> states;
    private Stack<State> undoHolder;

    public StateHolder(){
        states=new Stack<>();
        undoHolder=new Stack<>();
    }

    public void addNewState(State state){
        undoHolder.clear();
        states.add(state);
    }

    public State getCurrentState(){
        assert !states.isEmpty();
        return states.peek();
    }

    //undo
    public State getPreviousState(){
        assert !states.isEmpty();
        undoHolder.push(states.pop());
        return states.peek();
    }

    //redo
    public State getNextState(){
        assert !undoHolder.isEmpty();
        states.push(undoHolder.pop());
        return states.peek();
    }

}
