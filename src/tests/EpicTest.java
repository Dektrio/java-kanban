package tests;

import tasksProperties.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
//пока не до конца усвоилась система построениия тестов, буду честен, мне помогали местами
    @Test
    void subtaskAndEpicWithSameIdShouldBeEqual() {
        Subtask subtask = new Subtask("Subtask1", "Description1", 1);
        Epic epic = new Epic("Epic1", "Description1");

        subtask.setId(1);
        epic.setId(1);

        assertEquals(subtask, epic, "Подзадача и эпик с одинаковым ID должны быть равны.");
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Epic epic = new Epic("Epic1", "Description1");
        epic.setId(1);
        Subtask subtask = new Subtask("Subtask1", "Description1", epic.getId());
        subtask.setId(1);

        assertNotEquals(subtask.getEpicId(), subtask.getId(), "Подзадача не может быть своим же эпиком.");
    }

    @Test
    void epicCannotContainItselfAsSubtask() {
        Epic epic = new Epic("Epic1", "Description1");
        epic.setId(1);

        assertThrows(IllegalArgumentException.class, () -> epic.addSubtask(new Subtask("Subtask", "Desc", 1)),
                "Эпик не может содержать самого себя в качестве подзадачи.");
    }
}
