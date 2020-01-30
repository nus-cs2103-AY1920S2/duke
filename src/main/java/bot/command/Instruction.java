package bot.command;

/**
 * An enum containing constants, to be returned
 * to the bot from CommandParser
 */
public enum Instruction {
    AWAIT,
    DELETE,
    MARK_DONE,
    READ_STORAGE,
    SEARCH_STORAGE,
    STORE_DDL,
    STORE_EVENT,
    STORE_TODO,
    TERMINATE
}
