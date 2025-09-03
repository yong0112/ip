package noah.util;

import noah.exception.NoahException;

import noah.command.*;

import java.time.LocalDateTime;

/**
 * Parses user input strings into corresponding {@link Command} objects.
 */
public class Parser {
    /**
     * Parses a full command string entered by the user and returns the corresponding {@link Command} object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A {@link Command} object corresponding to the parsed command.
     * @throws NoahException If the command is unknown or the format is invalid.
     */
    public static Command parse(String fullCommand) throws NoahException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String command = parts[0].toUpperCase();

        switch (command) {
            case "BYE":
                return new ByeCommand();

            case "LIST":
                return new ListCommand();

            case "MARK":
                if (parts.length < 2) {
                    throw new NoahException("Please specify a task number to mark.");
                }
                int markIndex = parseInt(parts[1]);
                return new MarkCommand(markIndex);

            case "UNMARK":
                if (parts.length < 2) {
                    throw new NoahException("Please specify a task number to mark.");
                }
                int unmarkIndex = parseInt(parts[1]);
                return new UnmarkCommand(unmarkIndex);

            case "TODO":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new NoahException("The description of a todo cannot be empty.");
                }
                return new TodoCommand(parts[1].trim());

            case "DELETE":
                if (parts.length < 2) {
                    throw new NoahException("Please specify a task number to delete.");
                }
                int deleteIndex = parseInt(parts[1]) - 1;
                return new DeleteCommand(deleteIndex);

            case "DEADLINE":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new NoahException("The description of a deadline cannot be empty.");
                }
                if (!parts[1].contains("/by")) {
                    throw new NoahException("The deadline format must include /by");
                }
                String[] dl = parts[1].split(" /by", 2);
                if (dl.length < 2 || dl[0].trim().isEmpty()) {
                    throw new NoahException("The description of a deadline cannot be empty.");
                }
                if (dl[1].trim().isEmpty()) {
                    throw new NoahException("Please specify a valid deadline");
                }
                LocalDateTime by = DateTime.parseDate(dl[1].trim());
                return new DeadlineCommand(dl[0].trim(), by);

            case "EVENT":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new NoahException("The description of a event cannot be empty.");
                }
                if (!parts[1].contains("/from") || !parts[1].contains("/to")) {
                    throw new NoahException("The event format must include /from and /to");
                }
                String[] ev = parts[1].split(" /from | /to");
                if (ev.length < 2 || ev[0].trim().isEmpty()) {
                    throw new NoahException("The description of a event cannot be empty.");
                }
                if (ev[1].trim().isEmpty() || ev[2].trim().isEmpty()) {
                    throw new NoahException("Please specify valid /from and /to dates.");
                }
                LocalDateTime from = DateTime.parseDate(ev[1].trim());
                LocalDateTime to = DateTime.parseDate(ev[2].trim());
                return new EventCommand(ev[0].trim(), from, to);

            case "FIND":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new NoahException("Please provide a keyword to search for.");
                }
                return new FindCommand(parts[1].trim());

            default:
                throw new NoahException("Sorry~ I don't know what that means.");
        }
    }

    /**
     * Parses a string representing a task number into an integer index.
     *
     * @param numberStr The string to convert into an integer index.
     * @return The zero-based task index.
     * @throws NoahException If the string is not a valid integer.
     */
    public static int parseInt(String numberStr) throws NoahException {
        try {
            return Integer.parseInt(numberStr) - 1;
        } catch (NumberFormatException e) {
            throw new NoahException("OOPS! noah.task.Task number must be an integer.");
        }
    }
}
