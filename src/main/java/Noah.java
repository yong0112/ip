import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Noah {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

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
                c.isExit();
            } catch (NoahException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Noah().run();
    }
}