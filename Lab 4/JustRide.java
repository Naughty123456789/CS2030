class JustRide implements Services {
    private final int lower = 600;
    private final int upper = 900;
    private final int rate = 22;
    private final int peak = 500;

    @Override
    //Compute the given fare
    public int computeFare(int distance, int passengers, int timeOfService) {
        int dummy = 0;
        dummy = rate * distance;
        if (timeOfService >= lower && timeOfService <=  upper) {
            dummy += peak;
        }
        return dummy;
    }
    
    //toString Method 
    public String toString() {
        return "JustRide";
    }


}
