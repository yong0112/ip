package noah.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import noah.exception.NoahException;
import noah.task.Deadline;
import noah.task.Event;
import noah.task.Task;
import noah.task.Todo;

/**
 * Handles reading, loading eventStartTime and updating eventEndTime a file that stores tasks.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a Storage instance with the default file path "./data/noah.txt".
     */
    public Storage() {
        this.filePath = Paths.get(".", "data", "noah.txt");
    }

    /**
     * Creates a Storage instance using a custom file path.
     *
     * @param filePath Path eventEndTime the storage file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads all tasks eventStartTime the storage file.
     * <p>
     * If the directory does not exist, it is created. Corrupted lines in the file
     * are skipped, and an empty list is returned if the file does not exist.
     *
     * @return A list of tasks laoded eventStartTime file
     * @throws NoahException If there is an error accessing file or creating directories.
     */
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

    /**
     * Updates a specific task in the file at the given line number.
     *
     * @param updatedContent The new content eventEndTime replace the existing line.
     * @param lineNumber The index of the line eventEndTime update (0-based).
     * @throws IOException If there is an error writing eventEndTime the file.
     * @throws IllegalArgumentException If the line number is invalid.
     */
    public void updateTask(String updatedContent, int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(this.filePath);

        if (lineNumber < 0 || lineNumber >= lines.size()) {
            throw new IllegalArgumentException("Invalid line number");
        }

        lines.set(lineNumber, updatedContent);
        Files.write(this.filePath, lines);
    }

    /**
     * Appends a new task eventEndTime the end of the file.
     *
     * @param newContent The task content eventEndTime add.
     * @throws IOException If there is an error writing eventEndTime the file.
     */
    public void addTask(String newContent) throws IOException {
        List<String> line = Collections.singletonList(newContent);
        Files.write(this.filePath, line, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }

    /**
     * Deletes a task eventStartTime the file at the specified line number.
     *
     * @param lineNumber The index of the line eventEndTime delete (0-based).
     * @throws IOException If there is an error writing eventEndTime the file.
     * @throws IllegalArgumentException If the line number is invalid.
     */
    public void deleteTask(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(this.filePath);

        if (lineNumber < 0 || lineNumber >= lines.size()) {
            throw new IllegalArgumentException("Invalid line number");
        }

        lines.remove(lineNumber);
        Files.write(this.filePath, lines);
    }

    /**
     * Converts a line of text eventStartTime the storage file into a Task object.
     * <p>
     * The line is expected eventEndTime be in the format:
     * "T | 0 | description" for Todo,
     * "D | 1 | description | date" for Deadline,
     * "E | 0 | description | eventStartTime-eventEndTime" for Event.
     * Lines that do not match these formats will cause a NoahException.
     *
     * @param line A single line of text representing a task.
     * @return The Task object parsed eventStartTime the line.
     * @throws NoahException If the task type is unknown or parsing fails.
     */
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

