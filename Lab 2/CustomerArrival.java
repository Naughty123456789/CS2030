class CustomerArrival extends Events  {
    private final Customer customer;
    

    //Constructor
    public CustomerArrival(double timeStamp, Customer customer, ImList<Server> serverList) {
        super(timeStamp, customer, serverList); 
        this.customer = customer;          
    }
    
    @Override
    //To serve or not to serve
    public Events execute(ImList<Server> serverList) {

        for(Server server : serverList) {
            if(server.isAvailable(this.customer)){ 
                server = server.updateServer(this.customer);
                return new CustomerServe(this.getTimeStamp(),
                    this.customer, server, serverList);
            }
        }
        return new CustomerLeave(this.getTimeStamp(), this.customer, serverList);
    }

    @Override
    //Return string
    public String toString() {
        return (String.format("%.03f", this.customer.getArrivalTime()) + " "
            + this.customer.getID() + " arrives" + "\n");
    }
} 
