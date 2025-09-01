import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

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
            DateTimeFormatter.ofPattern("HHmm d-M-yyyy"),
            DateTimeFormatter.ofPattern("HHmm y/M/yyyy"),
            DateTimeFormatter.ofPattern("HHmm yyyy-M-d"),
            DateTimeFormatter.ofPattern("HHmm yyyy/M/d"),
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("MMM/d/yyyy")
    ) ;

    private static LocalDateTime parseDate(String date) {
        for (DateTimeFormatter format: FORMATS) {
            try {
                if (format.toString().contains("H")) {
                    return LocalDateTime.parse(date, format);
                } else {
                    return LocalDate.parse(date, format).atStartOfDay();
                }
            } catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("Invalid date format: " + date);
    }
}
