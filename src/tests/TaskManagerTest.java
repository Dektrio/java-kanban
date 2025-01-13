package tests;

import org.junit.jupiter.api.*;
import taskManager.*;
import tasksProperties.*;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    //пока не до конца усвоилась система построениия тестов, буду честен, мне помогали местами
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = Managers.getDefault();
    }

    @Test
    public void testTaskManagerHistory() {
        Task task1 = new Task("Task 1", "TaskDescription 1");
        Task task2 = new Task("Task 2", "TaskDescription 2");

        Epic epic1 = new Epic("Epic 1", "EpicDescription 1");
        Epic epic2 = new Epic("Epic 2", "EpicDescription 2");

        Subtask subtask1 = new Subtask("Subtask 1", "SubtaskDescription 1", epic1.getId());
        Subtask subtask2 = new Subtask("Subtask 2", "SubtaskDescription 2", epic2.getId());
        Subtask subtask3 = new Subtask("Subtask 3", "SubtaskDescription 3", epic2.getId());
        Subtask subtask4 = new Subtask("Subtask 4", "SubtaskDescription 4", epic2.getId());

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);

        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task2.getId());
        taskManager.getEpicById(epic1.getId());
        taskManager.getEpicById(epic2.getId());
        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getSubtaskById(subtask2.getId());
        taskManager.getSubtaskById(subtask3.getId());
        taskManager.getSubtaskById(subtask4.getId());

        printHistory();

        assertEquals(8, taskManager.getHistory().size(), "История должна содержать 8 элементов");

        // Удаляем одну задачу и один эпик, проверяем историю
        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteEpicById(epic1.getId());

        printHistory();

        assertEquals(4, taskManager.getHistory().size(), "История должна содержать 4 элемента после удаления задач");
    }

    private void printHistory() {
        System.out.println("История просмотров:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}
