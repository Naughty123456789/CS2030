class ShareARide implements Services {
    private final int lower = 600;
    private final int upper = 900;
    private final int rate = 50;
    private final int peak = 500;

    @Override
    //Compute the given fare
    public int computeFare(int distance, int passengers, int timeOfService) {
        int dummy = 0;
        dummy = rate * distance;
        if (timeOfService >= lower && timeOfService <=  upper) {
            dummy += peak;
        }
        return dummy / passengers;
    }

    //Return String
    public String toString() {
        return "ShareARide";
    }
}


