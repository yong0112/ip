package noah.task;

import noah.util.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TaskListTest {
    private TaskList tasks;
    private Task task;

    @BeforeEach
    public void setup(){
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
    public void taskToFormatString_correctFormat() {
        assertEquals("T | 0 | read", tasks.taskToFormatString(task));

        LocalDateTime date = LocalDateTime.of(2025,9, 2, 15, 0);
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
