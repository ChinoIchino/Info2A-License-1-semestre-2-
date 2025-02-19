package NewGeo.Listeners;

public class Point {
    private double coord1, coord2;

    //Constructor
    //with 2 double
    public Point(double coord1, double coord2){
        this.coord1 = coord1;
        this.coord2 = coord2;
    }

    //by cloning
    public Point(Point point){
        this.coord1 = point.coord1;
        this.coord2 = point.coord2;
    }

    //get
    public double getCoord1(){
        return this.coord1;
    }
    public double getCoord2(){
        return this.coord2;
    }

    //change the cord to string for diplay purpose
    public String toString(){
        return String.valueOf(this.coord1) + " , " + String.valueOf(this.coord2);
    }
    //function
    public boolean isDans(Disque disque){
        return 
            Math.sqrt(Math.pow(this.coord1 - disque.getCoord1(), 2) + Math.pow(this.coord2 - disque.getCoord2(), 2)) 
            <= Math.sqrt(Math.pow((disque.getCoord1() + disque.getRayon()) - disque.getCoord1(), 2));
    }
}
