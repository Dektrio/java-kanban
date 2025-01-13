package taskManager;

import java.util.*;

import tasksProperties.*;

public class InMemoryTaskManager implements TaskManager {
    private int idCounter = 1;

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private LinkedList<Task> history = new LinkedList<>(); //решил использовать такой тип списка
    //для ускорения удаления устаревших элементов истории

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public void addTask(Task task) {
        int id = task.getId();
        while (tasks.containsKey(id) || epics.containsKey(id) || subtasks.containsKey(id)) {
            id = generateId();
        }
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void addEpic(Epic epic) {
        int id = epic.getId();
        while (tasks.containsKey(id) || epics.containsKey(id) || subtasks.containsKey(id)) {
            id = generateId();
        }
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        int id = subtask.getId();

        while (tasks.containsKey(id) || epics.containsKey(id) || subtasks.containsKey(id)) {
            id = generateId();
        }
        subtask.setId(id);
        subtasks.put(id, subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubtask(subtask);
            epic.updateStatus();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            epic.updateStatus();
        }
    }

    @Override
    public Task getTaskById(int id) {

        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }
    //не был уверен, но, вроде бы, формулировка ТЗ подразумевает добавление в историю только этих 3 действий

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic neededEpic = epics.get(epic.getId());
            neededEpic.setName(epic.getName());
            neededEpic.setDescription(epic.getDescription());
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.updateStatus();
            }
        }
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
        }
    }

    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.removeSubtask(subtask);
                epic.updateStatus();
            }
        }
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpicId(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return new ArrayList<>();
        }
        return epic.getSubtasks();
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
        //надеюсь, что верно понял работу такой конструкции и конфликтов возникать не должно

    }

    private void addToHistory(Task task) {
        if (task != null) {
            history.add(task);
            if (history.size() > 10) {
                history.removeFirst();
            }
        }
    }

    private int generateId() {
        int id = idCounter;
        idCounter = id + 1;
        return id;
    }
}

