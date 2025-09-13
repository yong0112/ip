package noah.command;

import java.io.IOException;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.task.Todo;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command eventEndTime add a {@link Todo} task eventEndTime the task list.
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
