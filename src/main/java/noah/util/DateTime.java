package noah.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import noah.exception.InvalidDateFormatException;

/**
 * Represents an utility class for date time formatting"
 */
public class DateTime {
    private static final List<DateTimeFormatter> FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"),
            DateTimeFormatter.ofPattern("HH:mm d-M-yyyy"),
            DateTimeFormatter.ofPattern("HH:mm y/M/yyyy"),
            DateTimeFormatter.ofPattern("HH:mm yyyy-M-d"),
            DateTimeFormatter.ofPattern("HH:mm yyyy/M/d"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy hh:mma"),
            DateTimeFormatter.ofPattern("yyyy-M-d hh:mma"),
            DateTimeFormatter.ofPattern("yyyy/M/d hh:mma"),
            DateTimeFormatter.ofPattern("HHmm d-M-yyyy"),
            DateTimeFormatter.ofPattern("HHmm y/M/yyyy"),
            DateTimeFormatter.ofPattern("HHmm yyyy-M-d"),
            DateTimeFormatter.ofPattern("HHmm yyyy/M/d"),
            DateTimeFormatter.ofPattern("hh:mma d-M-yyyy"),
            DateTimeFormatter.ofPattern("hh:mma y/M/yyyy"),
            DateTimeFormatter.ofPattern("hh:mma yyyy-M-d"),
            DateTimeFormatter.ofPattern("hh:mma yyyy/M/d"),
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"),
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy hh:mma"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy hh:mma"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy")
    );

    /**
     * Parses string input to LocalDateTime instance.
     *
     * @param date String date input
     * @return LocalDateTime instances that matches the format.
     * @throws InvalidDateFormatException Throws exception if no matched format.
     */
    public static LocalDateTime parseDate(String date) throws InvalidDateFormatException {
        for (DateTimeFormatter format: FORMATS) {
            try {
                if (format.toString().contains("H")) {
                    return LocalDateTime.parse(date, format);
                } else {
                    return LocalDate.parse(date, format).atTime(LocalTime.of(23, 59));
                }
            } catch (DateTimeParseException ignored) {
                continue;
            }
        }
        throw new InvalidDateFormatException("Invalid date format: " + date);
    }

    /**
     * Converts a LocalDateTime instance to string.
     *
     * @param date LocalDateTime instance.
     * @return Formatted string date.
     */
    public static String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return date.format(formatter);
    }
}
