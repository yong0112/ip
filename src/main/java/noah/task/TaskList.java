package noah.task;

import noah.datetime.DateTime;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public String taskToFormatString (Task task) {
        String status = task.isDone ? "1": "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            String by = DateTime.dateToString(dl.by);
            return "D | " + status + " | " + task.description + " | " + by;
        } else if (task instanceof Event) {
            Event ev = (Event) task;
            String from = DateTime.dateToString(ev.from);
            String to = DateTime.dateToString(ev.to);
            return "E | " + status + " | " + task.description + " | " + from + "-" + to;
        } else {
            return "";
        }
    }
}
