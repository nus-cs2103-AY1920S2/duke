public class OutOfBoundMarkingRequestException extends Exception {
    public OutOfBoundMarkingRequestException(int num, int size) {
        super(String.format("Position %d is out of bound! Your list, if not empty, must include this index.", num, size));
    }
}
