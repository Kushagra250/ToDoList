package view;

import controller.TodoListController;
import model.Priority;
import model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TodoListView {
    private TodoListController controller;
    private Scanner scanner;

    public TodoListView(TodoListController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("To-Do List Application");
        System.out.println("1. Add Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Mark Task as Completed");
        System.out.println("5. View All Tasks");
        System.out.println("6. Search Tasks");
        System.out.println("7. Sort Tasks by Due Date");
        System.out.println("8. Sort Tasks by Priority");
        System.out.println("9. Save Tasks");
        System.out.println("10. Load Tasks");
        System.out.println("11. Exit");
    }

    public void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date (YYYY-MM-DD) or leave blank: ");
        String dueDateInput = scanner.nextLine();
        LocalDate dueDate = null;
        if (!dueDateInput.isEmpty()) {
            try {
                dueDate = LocalDate.parse(dueDateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
                return;
            }
        }
        System.out.print("Enter task priority (HIGH, MEDIUM, LOW): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());
        if (dueDate == null) {
            controller.addTask(description, priority);
        } else {
            controller.addTask(description, dueDate, priority);
        }
    }

    public void editTask() {
        System.out.print("Enter task ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter new task due date (YYYY-MM-DD) or leave blank: ");
        String newDueDateInput = scanner.nextLine();
        LocalDate newDueDate = null;
        if (!newDueDateInput.isEmpty()) {
            try {
                newDueDate = LocalDate.parse(newDueDateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
                return;
            }
        }
        System.out.print("Enter new task priority (HIGH, MEDIUM, LOW): ");
        Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
        controller.editTask(id, newDescription, newDueDate, newPriority);
    }

    public void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        controller.deleteTask(id);
    }

    public void markTaskAsCompleted() {
        System.out.print("Enter task ID to mark as completed: ");
        int id = Integer.parseInt(scanner.nextLine());
        controller.markTaskAsCompleted(id);
    }

    public void viewAllTasks() {
        List<Task> tasks = controller.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void searchTasks() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        List<Task> tasks = controller.searchTasks(keyword);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void sortTasksByDueDate() {
        List<Task> tasks = controller.sortTasksByDueDate();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void sortTasksByPriority() {
        List<Task> tasks = controller.sortTasksByPriority();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void saveTasks() {
        // Code to save tasks to a file
    }

    public void loadTasks() {
        // Code to load tasks from a file
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    markTaskAsCompleted();
                    break;
                case 5:
                    viewAllTasks();
                    break;
                case 6:
                    searchTasks();
                    break;
                case 7:
                    sortTasksByDueDate();
                    break;
                case 8:
                    sortTasksByPriority();
                    break;
                case 9:
                    saveTasks();
                    break;
                case 10:
                    loadTasks();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
