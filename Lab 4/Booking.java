import java.text.DecimalFormat;

class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final int fare1;
    private final int fare2; 
    
    
    //Constructor
    public Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
        this.fare1 = this.request.computeFare(this.driver.service1());
        this.fare2 = this.request.computeFare(this.driver.service2()); 
    }

    //Compute the lowest fare between the two services
    public int bestServices() {
        if (fare1 > fare2) {
            return this.fare2;
        }
        return fare1;   
    } 

    //Compute the service with the lowest fare
    public Services bestServiceName() {
        if (fare1 > fare2) {   
            return this.driver.service2();
        }
        return this.driver.service1();
    }


    @Override
    //Compare two bookings 
    public int compareTo(Booking other) {
       
        if (this.bestServices() != other.bestServices()) {
            return this.bestServices() - other.bestServices();
        } else if (this.driver.getPassengerWaitTime() != other.driver.getPassengerWaitTime()) {
            return this.driver.getPassengerWaitTime() - other.driver.getPassengerWaitTime();
        }
        return 0;
    }
    
    //Return a String
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        double dummy = (double)bestServices() / 100; 
        return "$" + df.format(dummy) + " using " + this.driver + " (" + bestServiceName() + ")";
    }
}
