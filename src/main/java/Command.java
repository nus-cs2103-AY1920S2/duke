public abstract class Command {

    public void execute(Common common,Ui ui) throws DukeException {}

    public boolean isExit(){
        return false;
    }
}
//