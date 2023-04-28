class PrivateCar extends Driver {
    private final String plateNo;
    private final int passengerWaitTime;
   
    //Constructor
    public PrivateCar(String plateNo, int passengerWaitTime) {
        super(plateNo, passengerWaitTime);
        this.plateNo = plateNo;
        this.passengerWaitTime = passengerWaitTime;
    }
       
    //provide JustRide services
    public Services provideJustRide() {
        return new JustRide();
    }

    //provide ShareARide services
    public Services shareRide() {
        return new ShareARide();
    }
    
    @Override
    //Return String
    public String toString() {
        return this.plateNo + " (" + this.passengerWaitTime + " mins away) PrivateCar";
    }
    
    @Override
    public Services service1() {
        return new JustRide();
    }

    @Override
    public Services service2() {
        return new ShareARide();
    }
}
