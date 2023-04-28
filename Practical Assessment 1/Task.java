class Task extends HigherTask {
   
    public Task(int day, int startTime, int endTime, String description) {
        super(day, startTime, endTime, description);    
    }

    public Task(Task task, int day) {
        super(task, day);
    }

    public Task(Task task) {
        super(task);
    }

    public Task(Task task, int startTime, int newEnd) {
        super(task, startTime,newEnd);
    }

    public int getStart() {
        return -1;
    }

    public int getEnd() {
        return -1;
    }

    public CancelledTask cancel() {
        return new CancelledTask(this);
    }

    public Task repeat(int num) {
        return new Task(this, num);
    }

    public Task edit(int newStart, int newEnd) {
        return new Task(this, newStart, newEnd);   
    }

    public String toString() { 
        return super.toString();
    }    
}
