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
    private static final List<String> PATTERNS = Arrays.asList(
            "d-M-yyyy HH:mm",
            "d/M/yyyy HH:mm",
            "yyyy-M-d HH:mm",
            "yyyy/M/d HH:mm",
            "HH:mm d-M-yyyy",
            "HH:mm y/M/yyyy",
            "HH:mm yyyy-M-d",
            "HH:mm yyyy/M/d",
            "d-M-yyyy HHmm",
            "d/M/yyyy HHmm",
            "yyyy-M-d HHmm",
            "yyyy/M/d HHmm",
            "d/M/yyyy hh:mma",
            "yyyy-M-d hh:mma",
            "yyyy/M/d hh:mma",
            "HHmm d-M-yyyy",
            "HHmm y/M/yyyy",
            "HHmm yyyy-M-d",
            "HHmm yyyy/M/d",
            "hh:mma d-M-yyyy",
            "hh:mma y/M/yyyy",
            "hh:mma yyyy-M-d",
            "hh:mma yyyy/M/d",
            "MMM d yyyy HH:mm",
            "MMM d yyyy HHmm",
            "MMM d yyyy hh:mma",
            "MMM d yyyy hh:mma",
            "d-M-yyyy",
            "d/M/yyyy",
            "yyyy-M-d",
            "yyyy/M/d",
            "MMM d yyyy"
    );

    private static final List<DateTimeFormatter> FORMATTERS =
            PATTERNS.stream()
                    .map(DateTimeFormatter::ofPattern)
                    .toList();

    /**
     * Parses string input to LocalDateTime instance.
     *
     * @param date String date input
     * @return LocalDateTime instances that matches the format.
     * @throws InvalidDateFormatException Throws exception if no matched format.
     */
    public static LocalDateTime parseDate(String date) throws InvalidDateFormatException {
        for (int i = 0; i < FORMATTERS.size(); i++) {
            DateTimeFormatter format = FORMATTERS.get(i);
            try {
                if (PATTERNS.get(i).contains("H") || PATTERNS.get(i).contains("h")) {
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
