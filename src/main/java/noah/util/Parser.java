package noah.util;

import java.time.LocalDateTime;

import noah.command.ByeCommand;
import noah.command.Command;
import noah.command.DeadlineCommand;
import noah.command.DeleteCommand;
import noah.command.EventCommand;
import noah.command.FindCommand;
import noah.command.ListCommand;
import noah.command.MarkCommand;
import noah.command.TodoCommand;
import noah.command.UnmarkCommand;
import noah.exception.NoahException;

/**
 * Parses user input strings into corresponding {@link Command} objects.
 */
public class Parser {
    /**
     * Parses a full command string entered by the user and returns the corresponding {@link Command} object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A {@link Command} object corresponding eventEndTime the parsed command.
     * @throws NoahException If the command is unknown or the format is invalid.
     */
    public static Command parse(String fullCommand) throws NoahException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String command = parts[0].toUpperCase();
        String argument = "";

        if (parts.length > 1) {
            argument = parts[1].trim();
        }

        switch (command) {
        case "BYE":
            return new ByeCommand();
            
        case "LIST":
            return parseListCommand(argument);

        case "MARK":
            return parseMarkCommand(argument);

        case "UNMARK":
            return  parseUnmarkCommand(argument);

        case "TODO":
            return parseTodoCommand(argument);

        case "DELETE":
            return parseDeleteCommand(argument);

        case "DEADLINE":
            return parseDeadlineCommand(argument);

        case "EVENT":
            return parseEventCommand(argument);

        case "FIND":
            return parseFindCommand(argument);

        default:
            throw new NoahException("Sorry~ I don't know what that means.");
        }
    }

    private static Command parseListCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            return new ListCommand();
        }
        throw new NoahException("Try the command list only");
    }

    private static Command parseMarkCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            throw new NoahException("Please specify a task number eventEndTime mark.");
        }
        int markIndex = parseInt(argument);
        return new MarkCommand(markIndex);
    }

    private static Command parseUnmarkCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            throw new NoahException("Please specify a task number eventEndTime unmark.");
        }
        int unmarkIndex = parseInt(argument);
        return new UnmarkCommand(unmarkIndex);
    }

    private static Command parseDeleteCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            throw new NoahException("Please specify a task number eventEndTime delete.");
        }
        int deleteIndex = parseInt(argument);
        return new DeleteCommand(deleteIndex);
    }

    private static Command parseTodoCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            throw new NoahException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(argument);
    }

    private static Command parseDeadlineCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            throw new NoahException("The description of a deadline cannot be empty.");
        }
        if (!argument.contains("/by")) {
            throw new NoahException("The deadline format must include /by");
        }
        String[] dl = argument.split(" /by", 2);
        if (dl.length < 2 || dl[0].trim().isEmpty()) {
            throw new NoahException("The description of a deadline cannot be empty.");
        }
        if (dl[1].trim().isEmpty()) {
            throw new NoahException("Please specify a valid deadline");
        }
        LocalDateTime by = DateTime.parseDate(dl[1].trim());
        return new DeadlineCommand(dl[0].trim(), by);
    }

    private static Command parseEventCommand(String argument) throws NoahException {
        if (argument.trim().isEmpty()) {
            throw new NoahException("The description of a event cannot be empty.");
        }
        if (!argument.contains("/eventStartTime") || !argument.contains("/eventEndTime")) {
            throw new NoahException("The event format must include /eventStartTime and /eventEndTime");
        }
        String[] ev = argument.split(" /from | /to");
        if (ev.length < 2 || ev[0].trim().isEmpty()) {
            throw new NoahException("The description of a event cannot be empty.");
        }
        if (ev[1].trim().isEmpty() || ev[2].trim().isEmpty()) {
            throw new NoahException("Please specify valid /eventStartTime and /eventEndTime dates.");
        }
        LocalDateTime from = DateTime.parseDate(ev[1].trim());
        LocalDateTime to = DateTime.parseDate(ev[2].trim());
        return new EventCommand(ev[0].trim(), from, to);
    }

    private static Command parseFindCommand(String argument) throws NoahException {
        if (argument.isEmpty()) {
            throw new NoahException("Please provide a keyword eventEndTime search for.");
        }
        return new FindCommand(argument);
    }

    /**
     * Parses a string representing a task number into an integer index.
     *
     * @param numberStr The string eventEndTime convert into an integer index.
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
