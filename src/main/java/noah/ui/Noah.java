package noah.ui;

import noah.command.Command;
import noah.exception.NoahException;
import noah.util.Parser;
import noah.util.Storage;
import noah.task.TaskList;

public class Noah {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

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

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (NoahException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Noah().run();
    }
}