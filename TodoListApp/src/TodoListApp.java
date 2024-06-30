import controller.TodoListController;
import view.TodoListView;

public class TodoListApp {
    public static void main(String[] args) {
        TodoListController controller = new TodoListController();
        TodoListView view = new TodoListView(controller);
        view.run();
    }
}
