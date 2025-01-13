package tests;

import taskManager.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    //пока не до конца усвоилась система построениия тестов, буду честен, мне помогали местами
    @Test
    void getDefaultShouldReturnInitializedTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "getDefault() должен возвращать проинициализированный экземпляр TaskManager.");
    }

    @Test
    void getDefaultHistoryShouldReturnInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "getDefaultHistory() должен возвращать проинициализированный экземпляр HistoryManager.");
    }
}
