class CustomerLeave extends Events  {
    private final Customer customer;
    private final double timeStamp;
    
    //constructor
    public CustomerLeave(double timeStamp, Customer customer, ImList<Server> serverList) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.timeStamp = timeStamp;
    }

    @Override
    //Simulate a customer leaving
    public Pair<Events, ImList<Server>> execute(ImList<Server> serverList) { 
        return new Pair<Events, ImList<Server>>(this, serverList);
    }
    
    //add one to leave every time a customer leaves
    public int numOfLeave() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format(String.format("%.03f", 
            this.timeStamp) 
                + " " + this.customer.getID() + " leaves" + "\n");
    }
}
