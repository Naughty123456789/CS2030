class TakeACab implements Services {
    private final int start = 200;
    private final int rate = 33;

    //Return String
    public String toString() {
        return "TakeACab";
    }
    
    @Override
    //Compute transport Fare
    public int computeFare(int distance, int passenger, int timeOfService) {
        return start + distance * rate;
    }
}

