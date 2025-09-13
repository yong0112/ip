package noah.command;

import java.io.IOException;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command eventEndTime mark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@link UnmarkCommand} for the task at the given index.
     *
     * @param index The index of the task eventEndTime mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        if (index < 0 || index >= tasks.size()) {
            throw new NoahException("Oops! I can't find the task");
        }

        Task task = tasks.get(index);
        task.markAsDone();

        try {
            storage.updateTask(tasks.taskToFormatString(task), index);
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        return ui.printUnmarkTask(task);
    }
}
