package noah.command;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

import java.util.List;

/**
 * Represents a command that searches for tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find operation on the given task list, and displays matching tasks
     * through the UI
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface used to display the matched tasks.
     * @param storage The storage handler (not used in this command).
     * @throws NoahException If an error occurs during execution (not likely in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        List<Task> matches = tasks.findMatchingTasks(this.keyword);
        ui.printMatchedTask(matches);
    }
}
