class Customer {
    private final int id;
    private final double arrivalTime;
    private final double serviceTime;

    //Constructor
    public Customer(int id, double arrivalTime, double serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
     
    //Get the id of the given customer
    public int getID() { 
        return this.id;
    }

    //Get the arrivalTime of the given customer
    public double getArrivalTime() {
        return this.arrivalTime;
    }
     
    //Get the serviceTime of the given customer
    public double getServiceTime() {
        return this.serviceTime;
    }

    //Get the required nextFreeTime of the given customer
    public double nextFreeTime() {
        return this.arrivalTime + this.serviceTime;
    }
    
    @Override
    //toString method for Customer class
    public String toString() { 
        return "Customer " + this.id; 
    }
}
