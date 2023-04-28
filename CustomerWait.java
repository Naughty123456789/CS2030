class CustomerWait extends Events {
    private final Customer customer;
    private final double timeStamp;
    private final Server server;
    private final ImList<Server> serverList;
    private final boolean canPrint;

    //Constructor
    public CustomerWait(double timeStamp, Customer customer, Server server, 
        ImList<Server> serverList, boolean canPrint) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.timeStamp = timeStamp;
        this.server = server;
        this.serverList = serverList;
        this.canPrint = canPrint;
    }

    @Override
    public Events execute(ImList<Server> serverList) {
        int index = server.getID() - 1;
        double updatedTimeStamp = serverList.get(index).getTimeStamp();
        Server waiter = server.updateTimeStamp(updatedTimeStamp).leaveQueue(customer);
       
        if (this.getTimeStamp() == updatedTimeStamp) {
            return new CustomerServe(updatedTimeStamp, this.customer, 
            waiter, this.serverList);      
        } else {  
            return new CustomerWait(updatedTimeStamp, customer,
            this.server.updateTimeStamp(updatedTimeStamp), 
            serverList, false);        
        }   
    }
    
    @Override
    public ImList<Server> update(ImList<Server> serverList) {

        serverList = serverList.set(server.getID() - 1, server);
        return serverList;
    }
  
    @Override
    //Return string
    public String toString() {
        if (canPrint) {
            return String.format(String.format("%.03f", 
                this.timeStamp) + " " + this.customer.getID()
                + " waits at " + this.server.getID() + "\n");
        } else {
            return "";
        }
    }
}
