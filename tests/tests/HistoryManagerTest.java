package tests;

import org.junit.jupiter.api.*;
import manager.*;
import properties.*;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    public void testAddTaskToHistory() {
        Task task = new Task("Task 1", "Task Description");
        historyManager.add(task);

        assertEquals(1, historyManager.getHistory().size(), "История должна содержать одну задачу.");
        assertEquals(task, historyManager.getHistory().get(0), "Первая задача в истории должна быть добавленной задачей.");
    }

    @Test
    public void testHistoryCapacity() {
        for (int i = 1; i <= 15; i++) {
            historyManager.add(new Task("Task " + i, "Description " + i));
        }

        assertEquals(10, historyManager.getHistory().size(), "История должна содержать не более 10 задач.");
        assertEquals("Task 6", historyManager.getHistory().get(0).getName(), "Первая задача в истории должна быть 6-й по порядку.");
    }

    @Test
    public void testNullTaskNotAddedToHistory() {
        historyManager.add(null);

        assertTrue(historyManager.getHistory().isEmpty(), "История не должна содержать null-задач.");
    }
}
