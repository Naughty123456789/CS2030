import java.util.function.Supplier;

class Counter extends Server {
    private final int id;
    private final double timeStamp;
    private final int qmax;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTime;
    private final int actualQueue;

    //Constructor
    public Counter(int id,  double timeStamp, int qmax, 
        Supplier<Double> serviceTime, int actualQueue, 
        Supplier<Double> restTime) { 
        super(id, timeStamp, qmax,
                serviceTime, actualQueue, restTime);
        this.id = id; 
        this.timeStamp = timeStamp;
        this.qmax = qmax;
        this.serviceTime = serviceTime;
        this.restTime = restTime;
        this.actualQueue = actualQueue; 

    }

    public Server updateServer(Customer customer) { 
        return new Counter(this.id, customer.getArrivalTime(), this.qmax, this.serviceTime, 
        this.actualQueue, restTime);
    }

    //customer join server queue
    public Server joinQueue(Customer customer) {
        if (this.actualQueue != qmax) {
            return new Counter(this.id, this.timeStamp, this.qmax, this.serviceTime,
            this.actualQueue + 1, this.restTime);
        } 
        return this;    
    }
    
    //customer leave server queue
    public Server leaveQueue() {
        if (this.actualQueue > 0) {
            return new Counter(this.id, this.timeStamp, this.qmax, this.serviceTime,
            this.actualQueue - 1, this.restTime);
        } 
        return this;  
    }

    
    //update TimeStamp
    public Server updateTimeStamp(double updatedTimeStamp) {
        return new Counter(this.id, updatedTimeStamp,
        this.qmax, serviceTime, this.actualQueue, this.restTime);
    }

    @Override
    public int checkType() {
        return 0;
    }

    public String toString() {
        return "Counter " + this.id;
    }
    
}
