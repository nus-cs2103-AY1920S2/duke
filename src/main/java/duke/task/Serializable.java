package duke.task;

/**
 * Represents an object that can be serialized for storage.
 */
public interface Serializable {
    /**
     * Returns a text representation of this object for storage purposes.
     *
     * @return a text representation of this object for storage purposes.
     */
    String serialize();
}
