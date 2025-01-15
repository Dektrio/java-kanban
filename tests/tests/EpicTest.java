package tests;
//надеюсь помещать тесты в пакет не является ошибкой
import org.junit.jupiter.api.*;
import properties.*;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void differentTypesWithSameIdShouldNotBeEqual() {
        Subtask subtask = new Subtask("Subtask1", "Description1", 1);
        Epic epic = new Epic("Epic1", "Description1");

        subtask.setId(1);
        epic.setId(1);

        assertNotEquals(subtask, epic, "Объекты разных типов не должны быть равны, даже если их ID совпадают.");
    }

    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Subtask subtask1 = new Subtask("Subtask1", "Description1", 1);
        Subtask subtask2 = new Subtask("Subtask2", "Description2", 2);

        subtask1.setId(1);
        subtask2.setId(1);

        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым ID должны быть равны.");
    }

    @Test
    void epicsWithSameIdShouldBeEqual() {
        Epic epic1 = new Epic("Epic1", "Description1");
        Epic epic2 = new Epic("Epic2", "Description2");

        epic1.setId(1);
        epic2.setId(1);

        assertEquals(epic1, epic2, "Эпики с одинаковым ID должны быть равны.");
    }

    @Test
    void subtasksWithDifferentIdsShouldNotBeEqual() {
        Subtask subtask1 = new Subtask("Subtask1", "Description1", 1);
        Subtask subtask2 = new Subtask("Subtask2", "Description2", 2);

        subtask1.setId(1);
        subtask2.setId(2);

        assertNotEquals(subtask1, subtask2, "Подзадачи с разными ID не должны быть равны.");
    }

    @Test
    void epicsWithDifferentIdsShouldNotBeEqual() {
        Epic epic1 = new Epic("Epic1", "Description1");
        Epic epic2 = new Epic("Epic2", "Description2");

        epic1.setId(1);
        epic2.setId(2);

        assertNotEquals(epic1, epic2, "Эпики с разными ID не должны быть равны.");
    }
}
