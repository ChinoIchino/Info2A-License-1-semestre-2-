package NewGeo.Listeners;

public class Segment {
    private double[] coord;
    //Constructors 
    //With 4 double
    public Segment(double coord1, double coord2, double coord3, double coord4){
        this.coord[0] = coord1;
        this.coord[1] = coord2;
        this.coord[2] = coord3;
        this.coord[3] = coord4;
    }
    //With 2 points
    public Segment(Point p1, Point p2){
        this.coord[0] = p1.getCoord1();
        this.coord[1] = p1.getCoord2();
        this.coord[2] = p2.getCoord1();
        this.coord[3] = p2.getCoord2();
    }
    //By cloning
    public Segment(Segment segment){
        this.coord[0] = segment.coord[0];
        this.coord[1] = segment.coord[1];
        this.coord[2] = segment.coord[2];
        this.coord[3] = segment.coord[3];
    }
}
