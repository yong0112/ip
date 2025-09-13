package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;

import java.io.IOException;

/**
 * Command that deletes tasks from task list
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@link DeleteCommand} for the task at the given index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by deleting a task from {@link TaskList} at the specified index,
     * updating the {@link Storage}, and notifying the user via the {@link UI}.
     *
     * @param tasks The task list containing all current tasks
     * @param ui The user interface for displaying output messages.
     * @param storage The storage handler used to save and delete tasks.
     * @throws NoahException If the index is invalid or if deletion from storage fails.
     * @return A formatted string confirming the removal.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        if (index < 0 || index >= tasks.size()) {
            throw new NoahException("Oops! I can't find the task");
        }

        assert index >= 0 && index < tasks.size() : "Invalid index to delete";

        try {
            storage.deleteTask(index);
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        Task removed = tasks.deleteTask(index);
        return ui.printRemoveTask(removed, tasks.size());
    }
}
