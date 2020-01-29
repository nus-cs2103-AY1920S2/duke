/**
 * handles strings from Ui and interpret accordingly
 * Returns interpreted commands back to Ui
 * Passes intended instructions to TaskList to perform
 *
 */




public class Parser{
    public static Command of(String[] arr){
        Command cmd = Command.convert(arr[0]);
        return Command.BYE;
        //make sense of command
    }
}