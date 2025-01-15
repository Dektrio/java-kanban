package tests;

import org.junit.jupiter.api.*;
import properties.*;
import manager.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = Managers.getDefault();
    }

    @Test
    void shouldAddAndRetrieveTaskById() {
        Task task = new Task("Task1", "Description1");
        manager.addTask(task);

        assertEquals(task, manager.getTaskById(task.getId()), "Задача должна находиться по её ID.");
    }

    @Test
    void shouldAddAndRetrieveEpicById() {
        Epic epic = new Epic("Epic1", "Description1");
        manager.addEpic(epic);

        assertEquals(epic, manager.getEpicById(epic.getId()), "Эпик должен находиться по его ID.");
    }

    @Test
    void shouldAddAndRetrieveSubtaskById() {
        Epic epic = new Epic("Epic1", "Description1");
        manager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask1", "Description1", epic.getId());
        manager.addSubtask(subtask);

        assertEquals(subtask, manager.getSubtaskById(subtask.getId()), "Подзадача должна находиться по её ID.");
    }

    @Test
    void shouldUpdateTask() {
        Task task = new Task("Task1", "Description1");
        manager.addTask(task);

        Task updatedTask = new Task("UpdatedTask", "UpdatedDescription");
        updatedTask.setId(task.getId());
        manager.updateTask(updatedTask);

        Task retrievedTask = manager.getTaskById(task.getId());
        assertEquals("UpdatedTask", retrievedTask.getName(), "Имя задачи должно обновиться.");
        assertEquals("UpdatedDescription", retrievedTask.getDescription(), "Описание задачи должно обновиться.");
    }

    @Test
    void shouldDeleteTaskById() {
        Task task = new Task("Task1", "Description1");
        manager.addTask(task);
        manager.deleteTaskById(task.getId());

        assertNull(manager.getTaskById(task.getId()), "Задача должна быть удалена.");
    }

    @Test
    void shouldDeleteEpicByIdAndItsSubtasks() {
        Epic epic = new Epic("Epic1", "Description1");
        manager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask1", "Description1", epic.getId());
        manager.addSubtask(subtask);

        manager.deleteEpicById(epic.getId());

        assertNull(manager.getEpicById(epic.getId()), "Эпик должен быть удален.");
        assertNull(manager.getSubtaskById(subtask.getId()), "Подзадачи эпика должны быть удалены.");
    }

    @Test
    void shouldReturnAllTasks() {
        Task task1 = new Task("Task1", "Description1");
        Task task2 = new Task("Task2", "Description2");
        manager.addTask(task1);
        manager.addTask(task2);

        List<Task> tasks = manager.getAllTasks();

        assertEquals(2, tasks.size(), "Должны быть возвращены все задачи.");
        assertTrue(tasks.contains(task1), "Список задач должен содержать первую задачу.");
        assertTrue(tasks.contains(task2), "Список задач должен содержать вторую задачу.");
    }

    @Test
    void shouldReturnAllEpics() {
        Epic epic1 = new Epic("Epic1", "Description1");
        Epic epic2 = new Epic("Epic2", "Description2");
        manager.addEpic(epic1);
        manager.addEpic(epic2);

        List<Epic> epics = manager.getAllEpics();

        assertEquals(2, epics.size(), "Должны быть возвращены все эпики.");
        assertTrue(epics.contains(epic1), "Список эпиков должен содержать первый эпик.");
        assertTrue(epics.contains(epic2), "Список эпиков должен содержать второй эпик.");
    }

    @Test
    void shouldReturnAllSubtasks() {
        Epic epic = new Epic("Epic1", "Description1");
        manager.addEpic(epic);

        Subtask subtask1 = new Subtask("Subtask1", "Description1", epic.getId());
        Subtask subtask2 = new Subtask("Subtask2", "Description2", epic.getId());
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        List<Subtask> subtasks = manager.getAllSubtasks();

        assertEquals(2, subtasks.size(), "Должны быть возвращены все подзадачи.");
        assertTrue(subtasks.contains(subtask1), "Список подзадач должен содержать первую подзадачу.");
        assertTrue(subtasks.contains(subtask2), "Список подзадач должен содержать вторую подзадачу.");
    }

    @Test
    void shouldReturnEmptyListWhenNoTasks() {
        List<Task> tasks = manager.getAllTasks();
        assertTrue(tasks.isEmpty(), "Список задач должен быть пуст, если задачи не добавлены.");
    }
}
