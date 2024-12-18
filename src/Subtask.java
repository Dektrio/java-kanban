public class Subtask extends Task {

    private final int epicId;

    public Subtask(String taskName, String description, TaskStatus status, int epicId) {
        super(taskName, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
