package tests;

import org.junit.jupiter.api.Test;
import tasksProperties.*;
import taskManager.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    //пока не до конца усвоилась система построениия тестов, буду честен, мне помогали местами
    @Test
    void shouldAddAndRetrieveTasksById() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task("Task1", "Description1");
        manager.addTask(task);

        assertEquals(task, manager.getTaskById(task.getId()), "Задача должна находиться по её ID.");
    }

    @Test
    void shouldHandleIdConflicts() {
        TaskManager manager = Managers.getDefault();
        Task task1 = new Task("Task1", "Description1");
        manager.addTask(task1);

        Task task2 = new Task("Task2", "Description2");
        manager.addTask(task2);

        assertNotEquals(task1.getId(), task2.getId(), "Задачи с конфликтующими ID должны получить разные ID.");
    }

    @Test
    void tasksShouldNotChangeWhenAddedToManager() {
        TaskManager manager = Managers.getDefault();
        Task originalTask = new Task("Task1", "Description1");
        manager.addTask(originalTask);

        Task retrievedTask = manager.getTaskById(originalTask.getId());
        assertEquals(originalTask.getName(), retrievedTask.getName(), "Имя задачи не должно меняться при добавлении в менеджер.");
        assertEquals(originalTask.getDescription(), retrievedTask.getDescription(), "Описание задачи не должно меняться при добавлении в менеджер.");
        assertEquals(originalTask.getStatus(), retrievedTask.getStatus(),
                "Статус задачи не должен меняться при добавлении в менеджер.");
    }

    @Test
    void historyManagerShouldRetainTaskData() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task("Task1", "Description1");
        manager.addTask(task);

        manager.getTaskById(task.getId());
        List<Task> history = manager.getHistory(); // Используйте historyManager

        assertEquals(1, history.size(), "История должна содержать одну задачу.");
        assertEquals(task, history.get(0), "История должна сохранять ту же самую задачу.");
    }

}
