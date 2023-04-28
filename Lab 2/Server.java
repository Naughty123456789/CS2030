class Server {
    
    private final int id;
    private final double timeStamp;
     
    //Constructor
    public Server(int id,  double timeStamp) { 
        this.id = id; 
        this.timeStamp = timeStamp;                    
    }

    //ServerID
    public int getID() {
        return this.id;
    }
    //Return timestamp
    public double getTimeStamp() {
        return timeStamp;
    }
    
    //To check whether Server is available
    public boolean isAvailable(Customer customer) {
        return customer.getArrivalTime() >= this.timeStamp;
    }

    //To get the server next available timing
    public double getNextFreeTime(Customer customer) {

        return customer.nextFreeTime();
    }
 
    //To adjust the timestamp accordingly
    public Server updateServer(Customer customer){
      return new Server(this.id, this.getNextFreeTime(customer));
    }

    @Override
    //toString method for Server class  
    public String toString() {
        return "server " + this.id;
    }
}
