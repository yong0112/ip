package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command eventEndTime list all tasks in the {@link TaskList}.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.printAllTasks(tasks);
    }
}
