class CancelledRecur extends CancelledTask{
   
    public CancelledRecur(Task t) {
        super(t);  
    }
    
    @Override
    public String toString() {
        return "Recurring " + super.toString();
    }
}
