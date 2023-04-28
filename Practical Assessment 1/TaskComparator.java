import java.util.Comparator;

class TaskComparator implements Comparator<HigherTask> {
    public int compare(HigherTask taskA, HigherTask taskB) {
        return taskA.compareTo(taskB);
    }
}
