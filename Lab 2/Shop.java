

class Shop {
    private final int numOfServers;
    private final ImList<Server> servers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;  
    
    //Constructor
    public Shop(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        servers = createServerList(numOfServers);
    }
   
    //Create an immutable list for servers
    public ImList<Server> createServerList(int numOfServers) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            servers = servers.add(new Server(i + 1));
        }
        return servers;
    }

    //Create an immutable list for customers
    public ImList<Customer> createCustomerList(ImList<Double> arrivalTimes, 
        ImList<Double> serviceTimes) {
        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            customers = customers.add(new Customer(i + 1, arrivalTimes.get(i), 
                serviceTimes.get(i)));
        }
        return customers;
    }

    //Simulate servers in the shop, feels not needed
    public Server getFirstAvailableServer() {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getAvailable()) {
                return servers.get(i);
            }      
        }
        return new Server();
    }
        
    @Override
    public String toString() {
        return "I am just a shop";    
    }
}
 



