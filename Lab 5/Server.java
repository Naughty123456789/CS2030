import java.util.function.Supplier;

abstract class Server {
    private final int id;
    private final double timeStamp;
    private final int qmax;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTime;
    private final int actualQueue;
    
    //Constructor
    public Server(int id,  double timeStamp, int qmax, 
        Supplier<Double> serviceTime, int actualQueue, 
        Supplier<Double> restTime) { 
        this.id = id; 
        this.timeStamp = timeStamp;
        this.qmax = qmax;
        this.serviceTime = serviceTime;
        this.restTime = restTime;
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

    public int getMax() {
        return this.qmax;
    }

    //To check whether Server is available
    public boolean isAvailable(Customer customer) {
        return customer.getArrivalTime() >= this.timeStamp;
    }

    public abstract Server updateServer(Customer customer); 
    
    public int getQueue() {
        return this.actualQueue;
    }
     
    //check whether customer can join queue
    public boolean canJoinQueue() {
        return this.actualQueue !=  this.qmax;
    }
     
    //customer join server queue
    public abstract Server joinQueue(Customer customer); 

    //customer leave server queue
    public abstract Server leaveQueue();
    
    //update TimeStamp
    public abstract Server updateTimeStamp(double updatedTimeStamp);
    
    
    //get this serviceTime
    public Supplier<Double> getServiceTime() {
        return this.serviceTime;
    }

    //get this restTime
    public Supplier<Double> getRestTime() {
        return this.restTime;
    }

    //to check whether Human or Counter
    //Human == 1 ; Counter == 0
    public abstract int checkType();


    //All counter's queue increase by 1 
    public ImList<Server> allJoin(ImList<Server> serverList, Customer customer) {

        for (Server waiter : serverList) {

            if (waiter.checkType() == 0) {
                Server waiter1 = waiter.joinQueue(customer);
                serverList = serverList.set(waiter.getID() - 1, waiter1);   
            }
        }
        return serverList;

    }

    //All counter's queue decrease by 1
    public ImList<Server> allLeave(ImList<Server> serverList, Customer customer) {

        int index1 = 0;
        for (Server waiter : serverList) {

            if (waiter.checkType() == 0) {
                Server waiter1 = waiter.leaveQueue();
                index1 = waiter.getID() - 1;
                serverList = serverList.set(index1, waiter1);
                  
            }
        }
        return serverList;
    }



    
    @Override
    //toString method for Server class  
    public abstract String toString();
}
