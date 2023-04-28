class Request {
    private final int distance;
    private final int passengers;
    private final int timeOfService;

    public Request(int distance, int passengers, int timeOfService) {
        this.distance = distance;
        this.passengers = passengers;
        this.timeOfService = timeOfService;
    }

    //return the String equivalent
    public String toString() {
        return this.distance + "km for " + this.passengers + "pax @ " + this.timeOfService + "hrs";
    }

    //Compute the transport fares
    public int computeFare(Services service) {
        return service.computeFare(this.distance, this.passengers, this.timeOfService);
    }

    //return distance 
    public int getDistance() {
        return this.distance;
    }
   
    //return number of passengers
    public int getPassengers() {
        return this.passengers;
    }
   
    //return get time of service
    public int getTimeOfService() {
        return this.timeOfService;
    }
}
