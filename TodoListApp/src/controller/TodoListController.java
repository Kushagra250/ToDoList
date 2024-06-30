package controller;

import model.Task;
import model.Priority;
import model.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TodoListController {
    private List<Task> tasks;
    private int nextId;

    public TodoListController() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
    }

    public void addTask(String description, LocalDate dueDate, Priority priority) {
        tasks.add(new Task(nextId++, description, dueDate, priority, TaskStatus.PENDING));
    }

    public void addTask(String description, Priority priority) {
        tasks.add(new Task(nextId++, description, priority));
    }

    public void editTask(int id, String newDescription, LocalDate newDueDate, Priority newPriority) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                task.setDueDate(newDueDate);
                task.setPriority(newPriority);
                return;
            }
        }
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void markTaskAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(TaskStatus.COMPLETED);
                return;
            }
        }
    }

    public List<Task> searchTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByDueDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }
}
