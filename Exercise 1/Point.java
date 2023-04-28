class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Calculate the midpoint between two consecutive given points
    public Point midPoint(Point another) {
        return new Point((this.x + another.x) / 2, (this.y + another.y) / 2);
    }

    //getters; used in a way that dun violate effect free and tell dont ask
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    //Calculate the angle (in radians) of the given line
    public double angleTo(Point another) {
        return Math.atan2(another.y - this.y, another.x - this.x); 
    }

    //Move a point by an angle of 0 and a distance of d
    public Point moveTo(double angle, double distance) {
        return new Point(this.x + distance * Math.cos(angle), this.y + distance * Math.sin(angle));
    }

    //Calculate the distance between two points
    public double distanceBetween(Point another) {
        double distX = this.x - another.x;
        double distY = this.y - another.y;
        return Math.sqrt(distX * distX + distY * distY);
    }

    @Override
    public String toString() {
        return "point (" + String.format("%.03f", this.x) + ", " 
            + String.format("%.03f", this.y) + ")";
    }
}

