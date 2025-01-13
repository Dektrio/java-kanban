package taskManager;

import tasksProperties.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10; // Максимальный размер истории
    private final LinkedList<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            history.add(task);
            if (history.size() > MAX_HISTORY_SIZE) {
                history.removeFirst(); // Удаляем самый старый элемент, если превышен размер
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(history); // Возвращаем копию списка
    }
}
