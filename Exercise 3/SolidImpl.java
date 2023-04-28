class SolidImpl implements Solid, Shape3D {
    private final Shape3D shape3D;
    private final double density;

    public SolidImpl(Shape3D shape3D, double density) {
        this.shape3D = shape3D;
        this.density = density;
    }
  
    //Compute volume of solid
    public double volume() {
        return shape3D.volume();
    }
        
    //Compute mass of solid
    public double mass() {
        return shape3D.volume() * this.density; 
    }

    public String toString() {
        return "SolidImpl";
    }
}


