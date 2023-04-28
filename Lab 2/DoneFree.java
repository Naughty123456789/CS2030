class DoneFree extends Events {
    
    private final Customer customer;
    private final Server server;
   
    
    //Constructor; take in an immutable list 
    public DoneFree(double timeStamp, Customer customer, Server server, ImList<Server> serverList) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.server = server;

    }
    @Override
    //execute when server is free after serving; take in an immutable list 
    public Events execute(ImList<Server> serverList) {
        return this;
    }

    @Override
    public String toString() {
        return String.format(String.format("%.03f", 
            this.customer.nextFreeTime()) 
                + " " + this.customer.getID() + " done serving by " +  this.server.getID() + "\n");
    }
}
