package tasksProperties;

public class Subtask extends Task {

    private final int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
