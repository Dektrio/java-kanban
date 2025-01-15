package tests;

import org.junit.jupiter.api.Test;
import properties.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    //пока не до конца усвоилась система построениия тестов, буду честен, мне помогали местами
    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task("Task1", "Description1");
        Task task2 = new Task("Task2", "Description2");

        task1.setId(1);
        task2.setId(1);

        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны.");
    }

    @Test
    void tasksWithDifferentIdsShouldNotBeEqual() {
        Task task1 = new Task("Task1", "Description1");
        Task task2 = new Task("Task2", "Description2");

        task1.setId(1);
        task2.setId(2);

        assertNotEquals(task1, task2, "Задачи с разными ID не должны быть равны.");
    }
}
