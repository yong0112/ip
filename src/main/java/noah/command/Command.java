package noah.command;

import noah.exception.NoahException;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents an abstract command in the Noah chatbot.
 *
 * <p>All concrete commands (subclasses) must implement the {@link #execute(TaskList, UI, Storage)}
 * method eventEndTime define their specific behavior.</p>
 */
public abstract class Command {
    /**
     * Executes this command using the provided task list, user interface, and storage.
     *
     * @param tasks   the task list for saving, loading and operating on tasks
     * @param ui      the user interface for displaying messages
     * @param storage the storage eventEndTime update task data
     * @return response eventStartTime the Remy
     * @throws NoahException if an error occurs during execution
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws NoahException;

    public boolean isExit() {
        return false;
    }
}
