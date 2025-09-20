package noah.util;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import noah.command.Command;
import noah.command.DeadlineCommand;
import noah.command.EditCommand;
import noah.command.EventCommand;
import noah.command.TodoCommand;
import noah.exception.NoahException;

public class ParserTest {
    @Test
    public void testParseToDoCommand() throws NoahException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void testParseDeadlineCommand() throws NoahException {
        Command command = Parser.parse("deadline submit report /by 2025-10-20 1800");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseEventCommand() throws NoahException {
        Command command = Parser.parse("event eat dinner /from 2025-10-20 1800 /to 2025-10-20 2000");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseEditCommand() throws NoahException {
        Command command = Parser.parse("edit 1 /desc read book");
        assertInstanceOf(EditCommand.class, command);
    }

    @Test
    public void testInvalidCommand_throwsNoahException() {
        try {
            Parser.parse("blablabla");
        } catch (NoahException e) {
            assertInstanceOf(NoahException.class, e);
        }
    }
}
