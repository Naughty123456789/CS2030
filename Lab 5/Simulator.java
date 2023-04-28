import java.util.function.Supplier;

class Simulator  {
    
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTimes;
    private final Supplier<Double> restTimes;
    private final int qmax;
    private final int numOfSelfChecks;
     
    //Constructor; to get all initial events 
    public Simulator(int numOfServers, int numOfSelfChecks,  int qmax, 
        ImList<Double> arrivalTimes, Supplier<Double> serviceTimes, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.qmax = qmax;
        this.restTimes = restTimes;
        this.numOfSelfChecks = numOfSelfChecks;      
    }

    //Each server has a blank queue
    public ImList<Server> createServerList(int numOfServers, int numOfSelfChecks) {
      
        ImList<Server> servers = new ImList<Server>();

        for (int i = 0; i < numOfServers; i++) {
            servers = servers.add(new Human(i + 1, 0.0, 
            qmax, serviceTimes, 0, restTimes));
        }

        for (int j = 1; j <= numOfSelfChecks; j++) {
            servers = servers.add(new Counter(numOfServers + j, 0.0, 
            qmax, serviceTimes, 0, restTimes));
        }

        return servers;
    }

    //Create an immutable list for customers
    public ImList<Customer> createCustomerList(ImList<Double> arrivalTimes) {
        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            customers = customers.add(new Customer(i + 1, arrivalTimes.get(i)));
        }
        return customers;
    }

    //To create a priority queue of events
    public PQ<Events> createEvents(ImList<Customer> customerList, ImList<Server> serverList) {
        PQ<Events> events = new PQ<Events>(new EventComparator());  
        for (int i = 0; i < arrivalTimes.size(); i++) {   
            CustomerArrival a = new CustomerArrival(customerList.get(i).getArrivalTime(), 
                            customerList.get(i), serverList);
            events = events.add(a);
        }
        return events;
    }
     
    //Process the events
    public String simulate() {
        
        ImList<Customer> customerList = createCustomerList(arrivalTimes);
        ImList<Server> serverList = createServerList(numOfServers, numOfSelfChecks);
        PQ<Events> events = createEvents(customerList, serverList);

        String toReturn = "";
        int numOfServed = 0;
        int numLeft = 0;
        double total = 0;
        while (!events.isEmpty()) {
            Pair<Events, PQ<Events>> eventPair = events.poll();
            
            total += eventPair.first().waitTime();
            events = eventPair.second();
            toReturn += eventPair.first();
            Pair<Events, ImList<Server>> curEvent = eventPair.first().execute(serverList);
            if (curEvent.first() != eventPair.first()) {
                serverList = curEvent.second();
                events = events.add(curEvent.first());             
            } else {
                serverList = curEvent.second();
                numLeft += eventPair.first().numOfLeave();
            }
        }
        numOfServed = arrivalTimes.size() - numLeft;
        
        double average = total / (arrivalTimes.size() - numLeft);
        if (total != 0) {
            toReturn += ("[" + String.format("%.3f", average) + " "
                + numOfServed + " " + numLeft + "]");
        } else {
            average = 0;
            toReturn += ("[" + String.format("%.3f", average) + " "
                + numOfServed + " " + numLeft + "]");
        }
        return toReturn;
    }
}









 

       
       
    
   
