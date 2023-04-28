class SolidCuboid extends Cuboid implements Solid, Shape3D {
    private final SolidImpl impl;
    private final double density;
    
    //Constructor
    public SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
        impl = new SolidImpl(this, this.density);
    }
     
    @Override
    //Compute the volume of the solid cuboid
    public double volume() {
        return super.volume();
    }

    //Calculate the mass of the cuboid
    public double mass() {     
        return impl.mass(); 
    }

    //Return a string
    public String toString() {
        return "solid-" + super.toString() + " with a mass of "
            + String.format("%.2f", this.mass());
    }
}
