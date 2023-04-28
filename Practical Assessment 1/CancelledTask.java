class CancelledTask extends HigherTask {
       
    public CancelledTask(HigherTask t) {
        super(t);
    }

    public ImList<HigherTask> ofDay(int day) {
        return new ImList<HigherTask>();
    }
 
    @Override
    public String toString() {
        return super.toString() + "[cancelled]";
    }
}