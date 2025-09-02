package noah.util;

import noah.task.Deadline;
import noah.task.Event;
import noah.task.Todo;
import noah.task.Task;
import noah.exception.NoahException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get(".", "data", "noah.txt");
    }

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<Task> loadTasks() throws NoahException {
        List<Task> tasks = new ArrayList<>();
        boolean directoryExists = Files.exists(this.filePath.getParent());
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
                LocalDateTime by = DateTime.parseDate(parts[3].trim());
                task = new Deadline(desc, by);
                break;
            case "E":
                String[] parts2 = parts[3].trim().split("-");
                LocalDateTime from = DateTime.parseDate(parts2[0].trim());
                LocalDateTime to = DateTime.parseDate(parts2[1].trim());
                task = new Event(desc, from, to);
                break;
            default:
                throw new NoahException("Unknown task type");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}

