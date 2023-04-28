class CustomerServe extends Events  {
    private final Customer customer;
    private final Server server;
    
     
    //Constructor
    public CustomerServe(double timeStamp, Customer customer, Server server, ImList<Server> serverList) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.server = server;
        
    }

    @Override
    //execute serving of customers
    public Events execute(ImList<Server> serverList) {   
        return new DoneFree(this.customer.nextFreeTime(), customer, server, serverList);   
    }
    
    @Override
    //Update the availability of customers accordingly
    public ImList<Server> update(ImList<Server> serverList) {
        for(Server waiter : serverList) {
            if(this.server.getID() == waiter.getID()) {
                serverList = serverList.set(waiter.getID() - 1 , this.server);
            }
        }
        return serverList;
    }
      
    @Override
    public String toString() {
        return String.format(String.format("%.03f", 
            this.customer.getArrivalTime()) + " " + this.customer.getID()
               + " serves by " + this.server.getID() + "\n");
    }   
}
