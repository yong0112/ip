package noah.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import noah.exception.NoahException;
import noah.util.DateTime;

public class TaskListTest {
    private TaskList tasks;
    private Task task;

    @BeforeEach
    public void setup() {
        tasks = new TaskList();
        task = new Todo("read");
    }

    @Test
    public void addTask_newTask_addedCorrectly() {
        assertEquals(0, tasks.size());
        tasks.addTask(task);
        assertEquals(1, tasks.size());
        assertSame(task, tasks.get(0));
    }

    @Test
    public void deleteTask_task_removedCorrectly() {
        tasks.addTask(task);
        Task removed = tasks.deleteTask(0);
        assertEquals(0, tasks.size());
        assertSame(removed, task);
    }

    @Test
    public void findMatchingTasks_keywordFound_returnsMatches() throws NoahException {
        Task task2 = new Deadline("read book", LocalDateTime.now());
        Task task3 = new Event("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        tasks.addTask(task);
        tasks.addTask(task2);
        tasks.addTask(task3);

        var matches = tasks.findMatchingTasks("read");
        assertEquals(2, matches.size());
        assertSame(task, matches.get(0));
        assertSame(task2, matches.get(1));
    }

    @Test
    public void editTask_descriptionUpdated() throws NoahException {
        tasks.addTask(task);
        tasks.editTask(0, "desc", "read book");
        assertEquals("read book", tasks.get(0).getDescription());
    }

    @Test
    public void taskToFormatString_correctFormat() throws NoahException {
        assertEquals("T | 0 | read", tasks.taskToFormatString(task));

        LocalDateTime date = LocalDateTime.of(2025, 9, 2, 15, 0);
        String dateStr = DateTime.dateToString(date);
        Task task2 = new Deadline("sleep", date);
        task2.markAsDone();
        assertEquals("D | 1 | sleep | " + dateStr, tasks.taskToFormatString(task2));

        LocalDateTime from = LocalDateTime.of(2025, 9, 2, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 2, 12, 0);
        Task task3 = new Event("meeting", from, to);
        String fromStr = DateTime.dateToString(from);
        String toStr = DateTime.dateToString(to);
        assertEquals("E | 0 | meeting | " + fromStr + "-" + toStr, tasks.taskToFormatString(task3));
    }
}
