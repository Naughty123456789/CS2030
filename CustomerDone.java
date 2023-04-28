class CustomerDone extends Events { 
    private final Customer customer;
    private final Server server;
    private final double timeStamp;
  
    //Constructor
    public CustomerDone(double timeStamp, Customer customer, Server server, 
        ImList<Server> serverList) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.server = server;
        this.timeStamp = timeStamp;     
    }
    
    @Override
    //execute when server is free after serving
    public Events execute(ImList<Server> serverList) {
        return this;
    }
    
    @Override
    //update the server list
    public ImList<Server> update(ImList<Server> serverList) {
    
        serverList = serverList.set(server.getID() - 1, server);   
        return serverList;
    }
    
    @Override
    //return String
    public String toString() {
        return String.format(String.format("%.03f", 
            this.timeStamp) 
                + " " + this.customer.getID() + " done serving by " +  this.server.getID() + "\n");
    }
}



