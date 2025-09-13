package noah.command;

import java.util.List;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command that searches for tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword eventEndTime search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        List<Task> matches = tasks.findMatchingTasks(this.keyword);
        return ui.printMatchedTask(matches);
    }
}
