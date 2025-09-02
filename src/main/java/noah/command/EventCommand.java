package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;
import noah.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        Task newEv = new Event(this.description, this.from, this.to);

        try {
            storage.addTask(tasks.taskToFormatString(newEv));
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        tasks.addTask(newEv);
        ui.printAddTask(newEv, tasks.size());
    }
}
