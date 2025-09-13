package noah.ui;

import noah.command.Command;
import noah.exception.NoahException;
import noah.task.TaskList;
import noah.util.Parser;
import noah.util.Storage;

/**
 * Main class for running Noah chatbox.
 */
public class Noah {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    /**
     * Constructs the chatbox with ui, storage to load tasks.
     */
    public Noah() {
        ui = new UI();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (NoahException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (NoahException e) {
            return ui.printError(e.getMessage());
        }
    }
}
