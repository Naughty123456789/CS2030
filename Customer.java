class Customer {
    private final int id;
    private final double arrivalTime;

    //Constructor
    public Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public Customer() {
        this.id = -1;
        this.arrivalTime = 0;
    }
     
    //Get the id of the given customer
    public int getID() { 
        return this.id;
    }

    //Get the arrivalTime of the given customer
    public double getArrivalTime() {
        return this.arrivalTime;
    }
      
    @Override
    //toString method for Customer class
    public String toString() { 
        return "Customer " + this.id; 
    }
}
