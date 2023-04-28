import java.util.function.Supplier;

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
    public Pair<Events, ImList<Server>> execute(ImList<Server> serverList) {
        int index = this.server.getID() - 1;
        if (server.checkType() == 1) {
            
            double restTime = server.getRestTime().get();
            double updatedRestTime = restTime + serverList.get(index).getTimeStamp();
            Server waiter = serverList.get(index).updateTimeStamp(updatedRestTime);
            serverList = serverList.set(index, waiter);
            return new Pair<Events, ImList<Server>>(this, serverList);
        }  else {

            return new Pair<Events, ImList<Server>>(this, serverList);
        }         
    }
 
    @Override
    //return String
    public String toString() {
        if (server.checkType() == 1) {
            return String.format(String.format("%.03f", 
            this.timeStamp) 
            + " " + this.customer.getID() + " done serving by " +  this.server.getID() + "\n");
        } else {
            return String.format(String.format("%.03f", 
            this.timeStamp) 
            + " " + this.customer.getID() + " done serving by self-check "
            +  this.server.getID() + "\n");
        }
        
        
    }
}



