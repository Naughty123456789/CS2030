abstract class HigherTask implements Comparable<HigherTask> {
    private final int day;
    private final int startTime;
    private final int endTime;
    private final String description;
  
    public HigherTask(int day, int startTime, int endTime, String description) {
        this.day = day;
	    this.startTime = startTime;
	    this.endTime = endTime;
	    this.description = description;        
    }

    public HigherTask(HigherTask t) {
        this(t.day, t.startTime, t.endTime, t.description);
    }

    public HigherTask(HigherTask t, int newStart, int newEnd) {
        this(t.day, newStart, newEnd, t.description);
    }

    public HigherTask(HigherTask t, int newDay) {
        this(t.day + newDay, t.startTime, t.endTime, t.description);
    }

    public int compareTo(HigherTask other) {
        if(this.day != other.day) {
            return this.day - other.day;
        }
        return this.startTime - other.startTime;
    }
    
    public String getDescribe() {
        return this.description;
    }

    public boolean isDay(int day) {
        return this.day == day;
    }

    public ImList<HigherTask> ofDay(int day) {
        if (!this.isDay(day)) {
            return new ImList<HigherTask>();       
        } else {
            return new ImList<HigherTask>().add(this);
        }
    }
    
    @Override
    public String toString() {
        return "Task: " + this.day + "," + this.startTime + "," + this.endTime
            + "," + this.description;
    }
}
   