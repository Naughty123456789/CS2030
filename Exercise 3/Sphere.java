class Sphere implements Shape3D {
    private final double radius;
    private final double constant = 4.0 / 3;

    //Constructor
    public Sphere(double radius) {
        this.radius = radius;
    }
    
    @Override
    //Calculate the volume of the sphere
    public double volume() {
        return  constant * Math.PI * this.radius * this.radius * this.radius;
    }
    
    @Override
    //Return the string 
    public String toString() {
        return "sphere [" + String.format("%.2f", this.radius) + "]";    
    }
}





