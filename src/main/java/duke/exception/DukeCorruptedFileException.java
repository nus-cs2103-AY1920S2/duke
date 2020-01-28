package duke.exception;

@SuppressWarnings("serial")
public class DukeCorruptedFileException extends DukeException {
    public DukeCorruptedFileException(String filePath) {
        super(String.format("File at %s is corrupted", filePath));
    }
}