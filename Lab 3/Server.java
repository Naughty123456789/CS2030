import java.util.function.Supplier;

class Server {
    private final int id;
    private final double timeStamp;
    private final int qmax;
    private final Supplier<Double> serviceTime;
    private final int actualQueue;

    //Constructor
    public Server(int id,  double timeStamp, int qmax, 
        Supplier<Double> serviceTime, int actualQueue) { 
        this.id = id; 
        this.timeStamp = timeStamp;
        this.qmax = qmax;
        this.serviceTime = serviceTime;
        this.actualQueue = actualQueue;
    }

    //ServerID
    public int getID() {
        return this.id;
    }

    //Return timestamp
    public double getTimeStamp() {
        return this.timeStamp;
    }

    //To check whether Server is available
    public boolean isAvailable(Customer customer) {
        return customer.getArrivalTime() >= this.timeStamp;
    }

    public Server updateServer(Customer customer) {
        return new Server(this.id, customer.getArrivalTime(), this.qmax, this.serviceTime, 
        this.actualQueue);
    }

    public int getQueue() {
        return this.actualQueue;
    }
     
    //check whether customer can join queue
    public boolean canJoinQueue() {
        return this.actualQueue < this.qmax;
    }
    
    //customer join server queue
    public Server joinQueue(Customer customer) { 
        return new Server(this.id, this.timeStamp, this.qmax, this.serviceTime,
        this.actualQueue + 1);     
    }
    
    //customer leave server queue
    public Server leaveQueue(Customer customer) {    
        return new Server(this.id, this.timeStamp, this.qmax, this.serviceTime,
        this.actualQueue - 1);    
    }
    
    //update TimeStamp
    public Server updateTimeStamp(double updatedTimeStamp) {
        return new Server(this.id, updatedTimeStamp,
        this.qmax, serviceTime, this.actualQueue);
    }
    
    //get this serviceTime
    public Supplier<Double> getServiceTime() {
        return this.serviceTime;
    }
    
    @Override
    //toString method for Server class  
    public String toString() {
        return "server " + this.id;
    }
}
