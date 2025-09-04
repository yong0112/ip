package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;
import noah.task.Todo;

import java.io.IOException;

/**
 * Represents a command to add a {@link Todo} task to the task list.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a {@link TodoCommand} with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes this command by creating a new {@link Todo} task, adding it to the {@link TaskList},
     * updating the {@link Storage}, and notifying the user via the {@link UI}.
     *
     * @param tasks The task list to which the new todo task will be added.
     * @param ui The user interface used to display messages.
     * @param storage The storage system used to persist the task.
     * @throws NoahException If there is an error writing to storage.
     * @return A formatted string confirming the addition.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        Task newTd = new Todo(this.description);

        try {
            storage.addTask(tasks.taskToFormatString(newTd));
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        tasks.addTask(newTd);

        return ui.printAddTask(newTd, tasks.size());
    }
}
