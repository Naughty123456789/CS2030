class CustomerArrival extends Events  {
    private final Customer customer;
    private final double timeStamp;
      
    //Constructor
    public CustomerArrival(double timeStamp, Customer customer, ImList<Server> serverList) {
        super(timeStamp, customer, serverList); 
        this.customer = customer;
        this.timeStamp = timeStamp;         
    }
    
    @Override
    public Pair<Events, ImList<Server>> execute(ImList<Server> serverList) {
        
        for (Server server : serverList) {
            
            if (server.isAvailable(this.customer) && server.getQueue() == 0) {
                server = server.updateServer(this.customer);
                serverList = serverList.set(server.getID() - 1, server);
                Events serveEvent = new CustomerServe(server.getTimeStamp(),
                    this.customer, server, serverList);
                return new Pair<Events, ImList<Server>>(serveEvent, serverList);
               
            }
        }

        int tracker = -1;

        //wait event for Human 
        for (Server server : serverList) {
            if (server.canJoinQueue() && server.checkType() == 1) {
                            
                server = server.joinQueue(this.customer);
                serverList = serverList.set(server.getID() - 1, server);
                Events waitEvent = new CustomerWait(customer.getArrivalTime(), 
                    this.customer, server, serverList);
                return new Pair<Events, ImList<Server>>(waitEvent, serverList);
            }
        }


        //how can i incur a leave event
        //one possible reason is im not working with the updated server list 
        //wait event for Counter
        for (Server server : serverList) {
            if (server.canJoinQueue() && server.checkType() == 0) {
  
                serverList = server.allJoin(serverList, customer);

                for  (Server waiter1 : serverList) {

                    if (waiter1.checkType() == 0 && server.getID() == waiter1.getID()) {
                        tracker = server.getID();
                        Server temp = serverList.get(tracker - 1);

                        Events waitEvent = new CustomerWait(customer.getArrivalTime(), 
                            this.customer, temp, serverList);
                        return new Pair<Events, ImList<Server>>(waitEvent, serverList);  
                    }  
                }  
                
            }

        }
        //for leaveEvent
        Events leaveEvent = new CustomerLeave(this.timeStamp, this.customer, serverList);
        return new Pair<Events, ImList<Server>>(leaveEvent, serverList);  
    }          
            

    @Override
    //Return string
    public String toString() {
        return (String.format("%.03f", this.timeStamp) + " "
            + this.customer.getID() + " arrives" + "\n");
    }
} 

//it looks like my serverList is not updated accordingly 
       