package noah.util;

import noah.exception.NoahException;
import noah.task.Deadline;
import noah.task.Event;
import noah.task.Task;
import noah.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private Path tempFile;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("noahTemp", ".txt");
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void loadTasks_validTask_success() throws IOException, NoahException {
        String content = "T | 1 | read book\n" +
                "D | 0 | submit report | 2025-09-02 1800\n" +
                "E | 0 | project meeting | Sep 01 2028 0000-Oct 01 2028 1600\n";
        Files.write(tempFile, content.getBytes());

        List<Task> tasks = storage.loadTasks();
        assertEquals(3, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertInstanceOf(Event.class, tasks.get(2));
    }

    @Test
    public void loadTasks_corruptedLine_skipped() throws Exception {
        String content = "X | 0 | invalid line\n" +  // unknown type
                "T | 1 | good task\n";
        Files.write(tempFile, content.getBytes());

        List<Task> tasks = storage.loadTasks();
        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
    }

    @Test
    public void addTask_writesToFile_success() throws Exception {
        storage.addTask("T | 0 | new task");
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size());
        assertEquals("T | 0 | new task", lines.get(0));
    }

    @Test
    public void updateTask_changesCorrectLine_success() throws Exception {
        Files.write(tempFile, List.of("T | 0 | old task"));
        storage.updateTask("T | 1 | updated task", 0);
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals("T | 1 | updated task", lines.get(0));
    }

    @Test
    public void deleteTask_removesCorrectLine_success() throws Exception {
        Files.write(tempFile, List.of("line1", "line2", "line3"));
        storage.deleteTask(1);
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(2, lines.size());
        assertFalse(lines.contains("line2"));
    }
}
