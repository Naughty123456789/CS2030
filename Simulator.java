import java.util.function.Supplier;

class Simulator  {
    
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTimes;
    private final int qmax;
     
    //Constructor; to get all initial events 
    public Simulator(int numOfServers, int qmax, 
        ImList<Double> arrivalTimes, Supplier<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.qmax = qmax;      
    }

    //Each server has a blank queue
    public ImList<Server> createServerList(int numOfServers) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            servers = servers.add(new Server(i + 1, 0.0, 
            qmax, serviceTimes, 0));
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
            events = events.add(new CustomerArrival(customerList.get(i).getArrivalTime(), 
                            customerList.get(i), serverList, false, true));
        }
        return events;
    }
     
    //Process the events
    public String simulate() {
        
        ImList<Customer> customerList = createCustomerList(arrivalTimes);
        ImList<Server> serverList = createServerList(numOfServers);
        PQ<Events> events = createEvents(customerList, serverList);

        String toReturn = "";
        int numOfServed = 0;
        int numLeft = 0;
        double total = 0;
        while (!events.isEmpty()) {
            Pair<Events, PQ<Events>> eventPair = events.poll();
            toReturn += eventPair.first();
            total += eventPair.first().waitTime();
            events = eventPair.second();
            Events curEvent = eventPair.first().execute(serverList);
            serverList = curEvent.update(serverList);
            if (eventPair.first() != curEvent) {
                events = events.add(curEvent);
                serverList = curEvent.update(serverList);          
            } else {
                numLeft += curEvent.numOfLeave();
            }
            serverList = curEvent.update(serverList);       
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









 

       
       
    
   
