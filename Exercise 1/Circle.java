class Circle {
    private final Point centre;
    private final double radius;
         
     
    Circle(Point centre, double radius) {
        this.centre = centre;
     
        if (radius >= 1) {
            this.radius = radius;
        } else {
            this.radius = 1;
        }
    }
     
    //getters; used in such a way such that it does not violate effect-free and tell dont ask
    public Point getCentre() {
        return this.centre;
    }

    public double getRadius() {
        return this.radius;
    }

    //Check whether Point P lies in Circle C
    public boolean contains(Point p) {
        final double EPSILON = 1E-15;
        return this.centre.distanceBetween(p) < this.radius + EPSILON;
    }

    @Override
    public String toString() {
        return "circle of radius " + this.radius + " centred at " + this.centre;
    }
}
