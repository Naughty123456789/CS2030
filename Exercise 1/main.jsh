double epsilon = 1E-15;

//To do the calculation for pythagoras theorem; to calculate the line mc
double pythagorasTheorem(double distance){
   return Math.sqrt(1.0 - Math.pow(distance , 2));
}

//Construct a unit circle whose parameter passes through two given points
Circle createUnitCircle(Point p , Point q){
    Point m = p.midPoint(q);
    double anglePQ = p.angleTo(q);
    double d = pythagorasTheorem(m.distanceBetween(q));
    return new Circle(m.moveTo(anglePQ + Math.PI/2 , d), 1.0);
}

//Finds the maximum coverage
int findMaxDiscCoverage(ImList<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            Circle c = createUnitCircle(p, q);
            if (p.distanceBetween(q) < 2.0 + epsilon) {
	        int coverage = 0;
                for (Point point : points) {
                    if (c.contains(point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}
