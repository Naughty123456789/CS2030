import java.util.Comparator;


class EventComparator implements Comparator<Events> {
    public int compare(Events eventA, Events eventB) {
        if (eventA.getTimeStamp() > eventB.getTimeStamp()) {
            return 1;
        } else if (eventA.getTimeStamp() == eventB.getTimeStamp()) {
            return eventA.getCustomerID() - eventB.getCustomerID();
        } else {
            return -1;
        }        
    }
}
    

  