class BufferWait extends Events {
    private final Customer customer;
    private final double timeStamp;
    private final Server server;
    private final boolean canPrint;
 
    //Constructor
    public BufferWait(double timeStamp, Customer customer, Server server, 
        ImList<Server> serverList, boolean canPrint) {
        super(timeStamp, customer, serverList);
        this.customer = customer;
        this.timeStamp = timeStamp;
        this.server = server;
        this.canPrint = canPrint;
    }

    @Override
    public Pair<Events, ImList<Server>>  execute(ImList<Server> serverList) {
       
        int index = server.getID() - 1;
        Server s =  serverList.get(index);
        if (server.checkType() == 1) {
            if (this.timeStamp == s.getTimeStamp()) { 
                Server waiter1 = s.leaveQueue();
                Events serveEvent = new CustomerServe(waiter1.getTimeStamp(), this.customer, 
                    waiter1, serverList);
                return new Pair<Events,ImList<Server>>(serveEvent, serverList);
            } else {  
                
                Events bufferWait =  new BufferWait(s.getTimeStamp(), customer,
                    s, serverList, false);
                return new Pair<Events,ImList<Server>>(bufferWait, serverList);       
            } 
        } else {
            
               
           
            double lowestTimeStamp = 0;
            int lowestID = 0;
            for (Server waiter : serverList) {

                if (waiter.checkType() == 0) {

                    lowestTimeStamp = waiter.getTimeStamp();
                    lowestID = waiter.getID();
                    break;
                }
            }

            for (Server waiter : serverList) {

                if (waiter.checkType() == 0 && waiter.getTimeStamp() < lowestTimeStamp) {

                    lowestTimeStamp = waiter.getTimeStamp();
                    lowestID = waiter.getID();
                }
            }

            Server lowestServer = serverList.get(lowestID - 1);
            serverList = serverList.set(lowestID - 1, lowestServer);
            
            for (Server waiter : serverList) {

                if (waiter.checkType() == 0 && this.timeStamp == waiter.getTimeStamp()) {
                    
                    serverList = waiter.allLeave(serverList, customer);
                    Events serveEvent = new CustomerServe(waiter.getTimeStamp(), this.customer, 
                        waiter, serverList);
                    return new Pair<Events,ImList<Server>>(serveEvent, serverList);
                }
            }
                
            Events bufferWait =  new BufferWait(lowestTimeStamp, customer,
                lowestServer, serverList, false);
            return new Pair<Events,ImList<Server>>(bufferWait, serverList);
        }
    }
    
    public String toString() {

        if (server.checkType() == 1 && canPrint) {
            return String.format(String.format("%.03f", 
            this.timeStamp) + " " + this.customer.getID()
            + " waits at " + this.server.getID() + "\n");
        } else if (server.checkType() == 0 && canPrint) {
            return String.format(String.format("%.03f", 
            this.timeStamp) + " " + this.customer.getID()
            + " waits at self-check " + this.server.getID() + "\n");
        } else {
            return "";
        }
    }
}


 