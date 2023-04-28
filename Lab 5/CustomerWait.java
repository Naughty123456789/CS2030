class CustomerWait extends Events {
    private final Customer customer;
    private final double timeStamp;
    private final Server server;
   
    //Constructor
    public CustomerWait(double timeStamp, Customer customer, Server server, 
        ImList<Server> serverList) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.timeStamp = timeStamp;
        this.server = server;
    }

    @Override
    public Pair<Events, ImList<Server>> execute(ImList<Server> serverList) {
        int index = server.getID() - 1; 
        double updatedTimeStamp = serverList.get(index).getTimeStamp();
        Server waiter = server.updateTimeStamp(updatedTimeStamp);
        
        serverList = serverList.set(server.getID() - 1, waiter); 
        Events bufferWait =  new BufferWait(this.timeStamp, customer,
            waiter, serverList, true);
        return new Pair<Events,ImList<Server>>(bufferWait, serverList);        
    }   

    @Override
    //Return string
    public String toString() {
        
        return "";
    }
}