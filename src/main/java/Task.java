package main.java;

public class Task {

    private String name;
    private boolean isDone;

    public Task(String thing){
        this.name = thing;
        this.isDone = false;
    }

    public String getName(){

        return this.name;
    }

    public boolean getStatus(){

        return this.isDone;
    }

    public void setStatus(boolean state){

        this.isDone = state;
    }
}
