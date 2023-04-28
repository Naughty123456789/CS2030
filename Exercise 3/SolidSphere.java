class SolidSphere extends Sphere implements Solid, Shape3D {
    private final double density;
    private final SolidImpl impl;
   
    //Constructor
    public SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
        impl = new SolidImpl(this, this.density);
    }
    
    //Compute the volume of the SolidSphere
    public double volume() {
        return super.volume();
    }
    
    //Compute the mass of the SolidSphere
    public double mass() {
        return impl.mass();
    }
    
    //return String
    public String toString() { 
        return "solid-" + super.toString() 
            + " with a mass of " + String.format("%.2f", this.mass());
    }
}
