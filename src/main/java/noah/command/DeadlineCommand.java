package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.storage.Storage;
import noah.exception.NoahException;
import noah.task.Task;
import noah.task.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        Task newDl = new Deadline(this.description, this.by);
        try {
            storage.addTask(tasks.taskToFormatString(newDl));
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }
        tasks.addTask(newDl);
        ui.printAddTask(newDl, tasks.size());
    }
}
