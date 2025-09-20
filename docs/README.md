# Noah User Guide
Welcome to the Noah User Guide! 
Noah is your personal task management application designed to help you keep track of your tasks efficiently. 
This guide will walk you through the features of Noah and how to use them effectively.

## Before getting started
### Prerequisites
* Java 17 (Windows)
* Java 17.0.14.fx-zulu (Mac)

###Installation
1. Download the latest version of Noah JAR file from the repository.
3. Open your terminal and navigate to the directory where you downloaded the JAR file.
4. Run the application using the command:
    ```
    java -jar noah.jar
    ```
5. Start managing your tasks with your best buddy Noah!

## Features
### 1. Adding tasks
Add a task to be completed.

Format: `todo <description>`

Example: `todo read book`

```
Expected output:

Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### 2. Adding deadlines
Add a task with a deadline.

Format: `deadline <description> /by <deadline>`

Example: `deadline return book /by 20-10-2024 18:00`

```
Expected output: 

Got it. I've added this task:
  [D][ ] return book (by: Oct 20 2024 18:00)
Now you have 2 tasks in the list.
```

> Tips: The date format is `MMM dd yyyy` or `dd-MM-yyyy` (There are other formats supported too).
> Time is optional in `HH:MM` or `HH:mma` format.

### 3. Adding events
Add an event with a starting and ending time.

Format: `event <description> /from <start time> /to <end time>`

Example: `event project meeting /from  /to `

```
Expected output:

Got it. I've added this task:
  [E][ ] project meeting (from: Oct 20 2024 14:00 to: Oct 20 2024 16:00)
Now you have 3 tasks in the list.
```

> Tips: The date format is `MMM dd yyyy` or `dd-MM-yyyy` (There are other formats supported too).
> Time is optional in `HH:MM` or `HH:mma` format.

### 4. Listing tasks
List all tasks in your task list.

Format: `list`

Example: `list`

```
Expected output:

Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Oct 20 2024 18:00)
3.[E][ ] project meeting (from: Oct 20 2024 14:00 to: Oct 20 2024 16:00)
```

### 5. Marking tasks 
Mark a task as completed.

Format: `mark <task number>`

Example: `mark 2`

```
Expected output:    

Nice! I've marked this task as done:
  [D][X] return book (by: Oct 20 2024 18:00)
```

### 6. Unmarking tasks
Unmark a task as not completed.

Format: `unmark <task index>`

Example: `unmark 2`

```
Expected output:    

OK, I've marked this task as not done yet:
  [D][ ] return book (by: Oct 20 2024 18:00)
``` 

### 7. Deleting tasks
Delete a task from your task list.

Format: `delete <task index>`

Example: `delete 2`
```
Expected output:

Yes sir! I've removed this task:
  [D][ ] return book (by: Oct 20 2024 18:00)
Now you have 2 tasks in the list.
``` 

### 8. Finding tasks
Find tasks that contain a specific keyword.

Format: `find <keyword>`

Example: `find book`

```
Expected output:

Here are the matching tasks in your list:
1.[T][ ] read book
``` 

### 9. Editing tasks
Edit the description or date/time of a task.

Format: `edit <task index> /<field> <new value>`

Example: `edit 1 /desc read novel`

```
Expected output:

Nice! I've updated this task:
  [T][ ] read novel
```

>Tips: The field can be `desc`, `by`, `from`, or `to` depending on the task type.

### 9. Exiting the application
Exit the application.

Format: `bye`

Example: `bye`

```
Expected output:

Bye. Hope to see you again soon!
```

## Command Summary
| Command                                                  | Description                            |
|----------------------------------------------------------|----------------------------------------|
| `todo <description>`                                     | Add a ToDo task                        |
| `deadline <description> /by <deadline>`                  | Add a Deadline task                    |
| `event <description> /from <start time> /to <end time>`  | Add an Event task                      |
| `list`                                                   | List all tasks                         |
| `mark <task index>`                                      | Mark a task as done                    |
| `unmark <task index>`                                    | Unmark a task as not done              |
| `delete <task index>`                                    | Delete a task                          |
| `find <keyword>`                                         | Find tasks containing the keyword      |
| `edit <task index> /<field> <new value>`                 | Edit a task's description or date/time |
| `bye`                                                    | Exit the application                   |

Enjoy using Noah to manage your tasks efficiently!
If you encounter any issues or have suggestions for improvement, feel free to open an issue on the repository.