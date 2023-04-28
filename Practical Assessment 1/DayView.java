class DayView implements View {
    private final int day;

    public DayView(int day) {
       this.day = day;
    }

    public int getDay() {
        return this.day;
    }
    
    @Override
    public void view(ImList<HigherTask> list) {
        
        ImList<HigherTask> newList = new ImList<HigherTask>();
        for(int i  = 0; i < list.size(); i++) {
            if(list.get(i).isDay(this.day)){
                ImList<HigherTask> dayList = list.get(i).ofDay(this.day);    
                newList = newList.addAll(dayList);
            }
        }
        newList = newList.sort(new TaskComparator());
        for(int i = 0; i < newList.size(); i++){
            System.out.println(newList.get(i));
        }    
    }
    public String toString() {
        return "lol";
    } 
}

