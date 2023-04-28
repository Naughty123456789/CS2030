class RecurringTask extends Task { 
    private final Task t;
    private final int frequency;
    private final int occurs;
    private final ImList<HigherTask> recurs;
   
    
    public RecurringTask(Task t, int frequency, int occurs) {
        super(t);
        this.t = t;
        this.frequency = frequency;
        this.occurs = occurs;
        this.recurs = createRecurring();              
    }

    public RecurringTask(Task task, ImList<HigherTask> recurs, int frequency , int occurs) {
        super(task);
        this.t = task;
        this.recurs = recurs;
        this.frequency = frequency;
        this.occurs = occurs;     
    }

    public ImList<HigherTask> createRecurring() {
        ImList<HigherTask> recurs = new ImList<HigherTask>();
        for(int i = 0; i < this.occurs; i++){
            recurs = recurs.add(t.repeat(frequency * i));
        }
        return recurs;
    }

    public ImList<HigherTask> ofDay(int day) {
        ImList<HigherTask> dayList = new ImList<HigherTask>();
        for(int i = 0 ; i < recurs.size(); i++) {
            if (recurs.get(i).isDay(day)) {
                dayList = dayList.addAll(recurs.get(i).ofDay(day));
            }
        }
        return dayList;
    }

    public CancelledRecur cancel() {
       return new CancelledRecur(this.t);
    }

    public MultipleCancel cancels(int num) {
        return new MultipleCancel();
    }

    public RecurringTask edit(int startTime, int endTime) {
       return new RecurringTask(t.edit(startTime, endTime),
                this.frequency, this.occurs);
    }
    
    public RecurringTask cancel(int num) {
       CancelledTask cancelled = new CancelledTask(recurs.get(num-1));
       return new RecurringTask(t, recurs.set(num - 1 , cancelled), 
       this.frequency , this.occurs);
    }

     public RecurringTask edit(int num, int newDay, int newStart, int newEnd) {
        Task newTask = new Task(newDay, newStart , newEnd, this.getDescribe());
        ImList<HigherTask> updatedTaskList = recurs.set(num - 1, newTask);
        ImList<HigherTask> sortedRecurs = updatedTaskList.sort(new TaskComparator());
        return new RecurringTask(this, sortedRecurs , this.frequency, this.occurs);   
    }
      
    @Override
    public String toString() { 
        ImList<HigherTask> sortedRecurs = recurs.sort(new TaskComparator());
        String dummy2 = ("Recurring " + super.toString() + "\n");
        for(int i = 0; i < recurs.size(); i++) {
            if(i != recurs.size() - 1) {
                dummy2 += (String.format("#" + (i + 1) + ":" + sortedRecurs.get(i) + "\n"));
            } else{
                dummy2 += (String.format("#" + (i + 1) + ":" + sortedRecurs.get(i)));
            } 
        }
        
        return dummy2;
    }



}
