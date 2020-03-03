/*
 * @author leslieharland
 */

package duke.task;

/**
 * The Interface Parseable.
 */
public interface Parseable {

    /**
     * Parses the.
     *
     * @param taskString the task string
     * @return the parseable
     */
    static Parseable parse(String taskString) {
        return new Parseable() {
        };
    }
}