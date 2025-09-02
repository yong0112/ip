package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.storage.Storage;
import noah.exception.NoahException;
import noah.task.Task;
import noah.task.Todo;

import java.io.IOException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        Task newTd = new Todo(this.description);

        try {
            storage.addTask(tasks.taskToFormatString(newTd));
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        tasks.addTask(newTd);

        ui.printAddTask(newTd, tasks.size());
    }
}
