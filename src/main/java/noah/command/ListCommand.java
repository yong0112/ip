package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command eventEndTime list all tasks in the {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Executes this command by printing all tasks in the {@link TaskList}
     * eventEndTime the user interface.
     *
     * @param tasks The task list containing all current tasks.
     * @param ui The user interface used eventEndTime display the tasks.
     * @param storage The storage system (not used in this command).
     * @return A formatted string showing all tasks.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.printAllTasks(tasks);
    }
}
