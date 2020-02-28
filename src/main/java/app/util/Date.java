package app.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import app.core.Messages;
import app.exceptions.InvalidDateTimeFormatException;

/**
 * This class represents the dates that are present in Duke.
 */
public final class Date {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    /**
     * Default format for input date parsing.
     */
    public static final String DEFAULT_INPUT_FORMAT = "yyyy-MM-dd HHmm";

    /**
     * Default format for date output.
     */
    public static final String DEFAULT_OUTPUT_FORMAT = "MMM d yyyy hh:mma";

    /**
     * Default format in storage.
     */
    public static final String DEFAULT_STORAGE_FORMAT = "yyyyMMddHHmmss";

    private Date(String dateTimeStr, String format) throws InvalidDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            this.date = LocalDateTime.parse(dateTimeStr, formatter);
            this.formatter = DateTimeFormatter.ofPattern(DEFAULT_OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException(String.format(Messages.INVALID_DATE_FORMAT_MESSAGE, format));
        }
    }

    private Date(LocalDateTime date, DateTimeFormatter formatter) {
        this.date = date;
        this.formatter = formatter;
    }

    /**
     * Parses and creates a new Date object using the DEFAULT_INPUT_FORMAT.
     * @param dateTimeStr Input string 
     * @return A new Date object
     * @throws InvalidDateTimeFormatException If input string's format does not 
     *     match DEFAULT_INPUT_FORMAT
     */
    public static Date from(String dateTimeStr) throws InvalidDateTimeFormatException {
        return new Date(dateTimeStr, DEFAULT_INPUT_FORMAT);
    }


    /**
     * Parses and creates a new Date object using a custom format.
     * @param dateTimeStr Input string
     * @param format Custom format based on DateTimeFormatter
     * @return A new Date object
     * @throws InvalidDateTimeFormatException If input string's format does not 
     *     match the param format.
     */
    public static Date fromFormat(String dateTimeStr, String format) throws InvalidDateTimeFormatException {
        return new Date(dateTimeStr, format);
    }

    /**
     * Parses and creates a new Date object using the DEFAULT_STORAGE_FORMAT.
     * @param dateTimeStr Input string 
     * @return A new Date object
     * @throws InvalidDateTimeFormatException If input string's format does not 
     *     match DEFAULT_STORAGE_FORMAT
     */
    public static Date fromStorage(String dateTimeStr) throws InvalidDateTimeFormatException {
        return new Date(dateTimeStr, DEFAULT_STORAGE_FORMAT);
    }

    /**
     * Changes the output format to a custom format.
     * @param outputFormat Custom format based on DateTimeFormatter
     * @return A new Date object with a new output formatter
     */
    public Date withFormat(String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return new Date(this.date, formatter);
    }

    /**
     * Returns a String reresentation of the Date for use in Storage.
     * @return a String representation of the Date using the DEFAULT_STORAGE_FORMAT
     */
    public String toStorage() {
        return this.date.format(DateTimeFormatter.ofPattern(DEFAULT_STORAGE_FORMAT));
    }

    /**
     * Returns a String representation of the Date object.
     */
    @Override
    public String toString() {
        return this.date.format(this.formatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        Date otherDate = (Date) other;
        return this.date.equals(otherDate.date);
    }
}