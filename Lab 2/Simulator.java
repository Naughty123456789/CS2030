
import java.util.Comparator;
import java.util.PriorityQueue;

class Simulator  {
    
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;

    //Constructor; to get all initial events 
    public Simulator(int numOfServers,ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;      
    }

    //Create an immutable list for Servers
    public ImList<Server> createServerList(int numOfServers) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            servers = servers.add(new Server(i + 1,0));
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

    //To create a priority queue of events
    public PQ<Events> createEvents(ImList<Customer> customerList, ImList<Server> serverList) {
        PQ<Events> events = new PQ<Events>(new EventComparator());
        
        for (int i = 0; i < arrivalTimes.size(); i++) {
           
            events = events.add(new CustomerArrival(customerList.get(i).getArrivalTime(), 
                            customerList.get(i), serverList));
        }
        return events;
    }
     
     //Process the events
    public String simulate() {
        ImList<Customer> customerList = createCustomerList(arrivalTimes, serviceTimes);
        ImList<Server> serverList = createServerList(numOfServers);
        PQ<Events> events = createEvents(customerList, serverList);

        String toReturn = "";
        int numOfServed = 0;
        int numLeft = 0;
        while (!events.isEmpty()) {
            Pair<Events, PQ<Events>> eventPair = events.poll();
            toReturn += eventPair.first();
            events = eventPair.second();
            Events curEvent = eventPair.first().execute(serverList);
            if (eventPair.first() != curEvent) {
                events = events.add(curEvent);
                serverList = curEvent.update(serverList);     
            } else {
                numLeft += curEvent.numOfLeave();
            }
        }
        numOfServed = arrivalTimes.size() - numLeft;
        toReturn += ("[" + numOfServed + " " + numLeft + "]");
        return toReturn;
    }
}









 

       
       
    
   
