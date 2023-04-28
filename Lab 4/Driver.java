abstract class Driver {
    private final String plateNo;
    private final int passengerWaitTime;

    public Driver(String plateNo, int passengertWaitTime) {
        this.plateNo = plateNo;
        this.passengerWaitTime = passengertWaitTime;
    }
    
    //To be override by the two other classes
    public String toString() {
        return "hi i am a car";
    }
   
    //return the first service
    abstract Services service1();
   

    //return the second service
    abstract Services service2();

 
    //get plateNo
    public String getPlateNo() {
        return this.plateNo;
    }

    //get waiting time 
    public int getPassengerWaitTime() {
        return this.passengerWaitTime;
    }    
}
