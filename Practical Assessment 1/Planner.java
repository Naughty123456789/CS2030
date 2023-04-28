class Planner {
    private final ImList<HigherTask> list;
    
    Planner(){
        this.list = createList();
    }

    public ImList<HigherTask> createList() {
        ImList<HigherTask> dummy = new ImList<HigherTask>();
        return dummy;
    }

    public void cancel() {
       System.out.println("i am just here for the similarity");
    }

    public Planner(ImList<HigherTask> list) {
        this.list = list;
    }

    public Planner add(HigherTask task) {
        
        return new Planner(this.list.add(task));
    }

    public void view(View seeView) {

       seeView.view(this.list);
    }
    
    @Override
    public String toString(){
        
       String dummy1 = "";

        for(int i  = 0; i < list.size(); i++) {
           if(i == 0) {
            dummy1 += "\n";
           } if(i != list.size() - 1) {
            dummy1 += (list.get(i).toString() + "\n");
           } else {
            dummy1 += (list.get(i).toString());
           }       
        }
        return dummy1;
    }








}
