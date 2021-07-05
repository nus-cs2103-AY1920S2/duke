package duke;


public class ToDo extends Task {

    /***
     * Constructor of newly created todo
     * @param name
     */
    public ToDo(String name) {
        super(name);
        isDone = false;
    }

    /***
     * Constructor of newly read todo
     * @param name
     * @param done
     */
    public ToDo(String name, boolean done) {
        super(name);
        this.isDone = isDone;
    }

    /***
     * Specify print format
     * @return print format
     */
    public String toString() {
        if (isDone) {
            return ("[T][v] " + name+"\n");
        }

        return ("[T][x] " + name+"\n");
    }


}
