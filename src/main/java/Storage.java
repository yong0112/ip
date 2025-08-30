import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get(".", "data", "duke.txt");
    }

    public List<Task> loadTasks() throws NoahException {
        List<Task> tasks = new ArrayList<>();
        boolean directoryExists = Files.exists(this.filePath);
        boolean fileExists = Files.exists(this.filePath);

        if (!directoryExists) {
            try {
                Files.createDirectories(this.filePath.getParent());
            } catch (IOException e) {
                throw new NoahException(e.getMessage());
            }
        }

        if (!fileExists) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (NoahException e) {
                    System.out.println("Skipping corrupted line");
                }
            }
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        return tasks;
    }

    public void updateTask(String updatedContent, int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(this.filePath);

        if (lineNumber < 0 || lineNumber >= lines.size()) {
            throw new IllegalArgumentException("Invalid line number");
        }

        lines.set(lineNumber, updatedContent);
        Files.write(this.filePath, lines);
    }

    public void addTask(String newContent) throws IOException {
        List<String> line = Collections.singletonList(newContent);
        Files.write(this.filePath, line,  StandardOpenOption.APPEND,  StandardOpenOption.CREATE);
    }

    public void deleteTask(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(this.filePath);

        if (lineNumber < 0 || lineNumber >= lines.size()) {
            throw new IllegalArgumentException("Invalid line number");
        }

        lines.remove(lineNumber);
        Files.write(this.filePath, lines);
    }

    private Task parseTask(String line) throws NoahException {
       String[] parts = line.split(" \\| ");
       String type = parts[0].trim();
       boolean isDone = parts[1].trim().equals("1");
       String desc = parts[2].trim();

        Task task;
        switch (type) {
            case "T":
                task = new Todo(desc);
                break;
            case "D":
                task = new Deadline(desc, parts[3].trim());
                break;
            case "E":
                task = new Event(desc, parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new NoahException("Unknown task type");
        }

        if (isDone) {
            task.isDone = true;
        }

        return task;
    }
}

