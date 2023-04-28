

class Cuboid implements Shape3D {
    private final double height;
    private final double width;
    private final double length;

    //Constructor
    public Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }
    
    @Override
    //Compute the volume of the cuboid
    public double volume() {
        return Math.round(height * width * length);     
    }
    
    @Override
    //return the string
    public String toString() {
        return ("cuboid [" + String.format("%.2f",this.height) + " x " 
            + String.format("%.2f", this.width) 
                + " x " + String.format("%.2f", this.length) + "]");
    }
       
}
