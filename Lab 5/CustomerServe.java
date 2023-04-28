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
    public Pair<Events, ImList<Server>> execute(ImList<Server> serverList) {
        int index = this.server.getID() - 1;
        Supplier<Double> serviceTime = server.getServiceTime();
        double updatedTimeStamp = this.timeStamp + serviceTime.get();

        if (server.checkType() == 1) {//human
    
            Server waiter = server.updateTimeStamp(updatedTimeStamp);
            Events doneEvent = new CustomerDone(updatedTimeStamp,
                customer, waiter, serverList);
            serverList = serverList.set(index, waiter);

            return new Pair<Events, ImList<Server>>(doneEvent, serverList);
        } else {//counter
            
            Server temp = serverList.get(index).updateTimeStamp(updatedTimeStamp);
            serverList = serverList.set(index, temp);
            Events doneEvent = new CustomerDone(updatedTimeStamp,
                    customer, temp, serverList);
                    
            return new Pair<Events, ImList<Server>>(doneEvent, serverList);

        }      
    }
    
    @Override
    //to calculate average wait time
    public double waitTime() {
        return (this.timeStamp - customer.getArrivalTime());   
    }
  
    @Override
    //return String
    public String toString() {
        if (server.checkType() == 1) {
            return String.format(String.format("%.03f", 
                this.timeStamp) + " " + this.customer.getID()
                + " serves by " + this.server.getID() + "\n");
        }  else {
            return String.format(String.format("%.03f", 
                this.timeStamp) + " " + this.customer.getID()
                + " serves by self-check " + this.server.getID() + "\n");
        }
    }

}
