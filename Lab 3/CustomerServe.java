import java.util.function.Supplier;

class CustomerServe extends Events  {
    private final Customer customer;
    private final Server server;
    private final double timeStamp;
  
    //Constructor
    public CustomerServe(double timeStamp, Customer customer, Server server, 
        ImList<Server> serverList) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.server = server; 
        this.timeStamp = timeStamp;        
    }
    
    @Override
    //execute serving of customers
    public Events execute(ImList<Server> serverList) { 
        Supplier<Double> serviceTime = server.getServiceTime();
        double updatedTimeStamp = this.timeStamp + serviceTime.get();
        return new CustomerDone(updatedTimeStamp,
        customer, server.updateTimeStamp(updatedTimeStamp), serverList);       
    }
    
    @Override
    //update serverList
    public ImList<Server> update(ImList<Server> serverList) {  
        serverList = serverList.set(this.server.getID() - 1,
        this.server.updateTimeStamp(timeStamp).leaveQueue(customer));
        return serverList;
    }

    @Override
    //to calculate average wait time
    public double waitTime() {
        return (this.getTimeStamp() - customer.getArrivalTime());   
    }
  
    @Override
    //return String
    public String toString() {
        return String.format(String.format("%.03f", 
            this.timeStamp) + " " + this.customer.getID()
               + " serves by " + this.server.getID() + "\n");
    }   
}
