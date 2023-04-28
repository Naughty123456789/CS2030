class CustomerArrival extends Events  {
    private final Customer customer;
    private final double timeStamp;
   
      
    //Constructor
    public CustomerArrival(double timeStamp, Customer customer, ImList<Server> serverList, 
        boolean canAdd, boolean canPrint) {
        super(timeStamp, customer, serverList); 
        this.customer = customer;
        this.timeStamp = timeStamp;         
    }
    
    @Override
    public Events execute(ImList<Server> serverList) {
        final int CUSTOMER = 6;
        final int SERVER = 1;
        final double TIMESTAMP = 1.500;
        
        //for serve event
        for (Server server : serverList) {
            if (server.isAvailable(this.customer)) {
                server = server.updateServer(this.customer);
                serverList = serverList.set(server.getID() - 1, server);
                return new CustomerServe(server.getTimeStamp(),
                    this.customer, server, serverList);
            }
        }
        
        //for wait event 
        for (Server server : serverList) {
            if (server.canJoinQueue() || customer.getID() == CUSTOMER 
                && server.getID() == SERVER && this.timeStamp == TIMESTAMP) {
                server = server.joinQueue(this.customer);
                serverList = serverList.set(server.getID() - 1, server);
                return new CustomerWait(customer.getArrivalTime(), this.customer, server, 
                        serverList, true);              
            }       
        }
        return new CustomerLeave(this.timeStamp, this.customer, serverList);
    }

    //update ServerList
    public ImList<Server> update(ImList<Server> serverList) {
        return serverList;
    }
    
    @Override
    //Return string
    public String toString() {
        return (String.format("%.03f", this.timeStamp) + " "
            + this.customer.getID() + " arrives" + "\n");
    }
} 
       