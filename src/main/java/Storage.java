import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get(".", "data", "duke.txt");
    }

    public List<Noah.Task> loadTasks() throws Noah.NoahException {
        List<Noah.Task> tasks = new ArrayList<>();
        boolean directoryExists = Files.exists(this.filePath);
        boolean fileExists = Files.exists(this.filePath);

        if (!directoryExists) {
            try {
                Files.createDirectories(this.filePath.getParent());
            } catch (IOException e) {
                throw new Noah.NoahException(e.getMessage());
            }
        }

        if (!fileExists) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new Noah.NoahException(e.getMessage());
        }


    }

    private Noah.Task parseTask(String line) throws Noah.NoahException {
       String[] parts = line.split(" \\| ");
       String type = parts[0].trim();
       boolean isDone = parts[1].trim().equals("1");
       String desc = parts[2].trim();

        Noah.Task task;
        switch (type) {
            case "T":
                task = new Noah.Todo(desc);
                break;
            case "D":
                task = new Noah.Deadline(desc, parts[3].trim());
                break;
            case "E":
                task = new Noah.Event(desc, parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new Noah.NoahException("Unknown task type");
        }

        if (isDone) {
            task.isDone = true;
        }

        return task;
        }
    }
}
