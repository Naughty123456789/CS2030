abstract class Events {

    private final double timeStamp;
    private final Customer customer;
    private final ImList<Server> serverList;
    
    //Constructor
    public Events(double timeStamp, Customer customer, ImList<Server> serverList) {
        this.customer = customer;
        this.timeStamp = timeStamp;
        this.serverList = serverList;     
    }

    //To transition the phase
    public abstract Events execute(ImList<Server> serverList);

    //Determine which server is available
    public ImList<Server> update(ImList<Server> serverList) {
        return serverList;
    }
    
    //get customer id
    public int getCustomerID() {
        return this.customer.getID();
    }
    
    //To get time stamp
    public double getTimeStamp() {
        return this.timeStamp;
    }

    //To count the number of customers who leaves
    public int numOfLeave() {
        return 0;
    }
    
    //To calculate the average wait time 
    public double waitTime() {
        return 0;
    }
     
    //To be override by the 4 other classes
    public String toString() {
        return String.format("%3.f", this.timeStamp);
    }
}
