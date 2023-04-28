class NormalCab extends Driver  {
    private final String plateNo;
    private final int passengerWaitTime;
    
    //Constructor
    public NormalCab(String plateNo, int passengertWaitTime) {
        super(plateNo, passengertWaitTime);
        this.plateNo = plateNo;
        this.passengerWaitTime = passengertWaitTime;
    }
    
    @Override
    //provide JustRide services
    public Services service1() {
        return new JustRide();
    }
     
    @Override
    //provide TakeaCab services
    public Services service2() {
        return new TakeACab();
    }
    
    @Override
    //Return String
    public String toString() {
        return this.plateNo + " (" + this.passengerWaitTime + " mins away) NormalCab";
    }
   
} 


