import java.util.List;

public class ServiceLevel5 {
    private final Request request;
    private final List<Driver> carList;
    

    public ServiceLevel5(Request request, List<Driver> carList) {
        this.request = request;
        this.carList = carList;     
    }
    
    /**return the accumulated String
    *  method creates a pseudo Hashmap to represent and replace the values given.
    *  my senior guided me along the way when i was doing level5 
    *  as i was struggling for quite a while. He suggested many different approaches 
    *  but finally he guided me on how to use this psuedo hashmap to solve this.
    *  I am still kinda confused so i would like to clarify about this.
    */
    public String toReturn() {
        final int HASH = 999999;
        ImList<Services> serviceList = new ImList<>();
        for (Driver car : carList) {
            serviceList = serviceList.add(car.service1());
            serviceList = serviceList.add(car.service2());
        }
        String returnValue = "";
        // while there is services
        // we pick the cheapest service 
        ImList<Integer> hashmap = new ImList<>();
        for (int i = 0; i < carList.size() * 2; i++) {
            int mincarj = 0;
            int minservicek = 0;
            int minserviceIn = 0;
            int mincost = Integer.MAX_VALUE;
            int mintime = Integer.MAX_VALUE;
            for (int j = 0; j < carList.size(); j++) {
                for (int k = 0; k < 2; k++) {
                    if (hashmap.indexOf(HASH * j + k) == -1) {
                        if (request.computeFare(serviceList.get(j * 2 + k)) < mincost) {
                            mincost = request.computeFare(serviceList.get(j * 2 + k));
                            mintime = carList.get(j).getPassengerWaitTime();
                            mincarj = j;
                            minservicek = j * 2 + k;
                            minserviceIn = k;
                        } else if (request.computeFare(serviceList.get(j * 2 + k)) 
                            == mincost && mintime > carList.get(j).getPassengerWaitTime()) {
                            mincost = request.computeFare(serviceList.get(j * 2 + k));
                            mintime = carList.get(j).getPassengerWaitTime();
                            mincarj = j;
                            minservicek = j * 2 + k;
                            minserviceIn = k;
                        }
                    }
                }
            }
            returnValue += String.format("$" + String.format("%.2f",(mincost / 100.0)) + 
                " using " + carList.get(mincarj) + 
                    " (" + serviceList.get(minservicek) + ")" + "\n");
            hashmap = hashmap.add(HASH * mincarj + minserviceIn);
        }

        // car 1, car 2
        // serivces list = [3 justride, normalcab]
        // while(!serviceList.isEmpty()) {
        //     int index1 = 0;
        //     int index2 = 0;
        //     int min = request.computeFare(serviceList.get(0));
        //     int minDistance = carList.get(0).getPassengerWaitTime();
        //     for(int j = 0; j < carList.size(); j++){
        //         for(int i = 0; i < serviceList.size(); i++){
        //             int dummy = request.computeFare(serviceList.get(i));
        //             if(dummy < min) {
        //                 min = dummy;
        //                 index1 = i;
        //             } else if(dummy == min && minDistance 
        // <  carList.get(j).getPassengerWaitTime()){
        //                 min = dummy;
        //                 index1 = i;
        //                 index2 = j;
        //             }
        //         }
        //     }
        //  returnValue += String.format("$" + min + " using " + carList.get(index2) 
        //      + " (" + serviceList.get(index1) + ")" + "\n");
        //  serviceList = serviceList.remove(index1);
        // }
        // your code does not workdue to the fact that given [car1, car2], 
        // services [justride, justride, justride...]
        // if car1 and justride gives the best result first
        // then removing justide from the service list 
        //will still result in [justride, justride, ...]
        //picking the next best cost will still be car1 and justride
        
        return returnValue;
    }
    
}
// imlist<pair<car, service>> loop through this and just keep getting the min cost
