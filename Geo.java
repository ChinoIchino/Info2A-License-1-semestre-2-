//everything in the main can be deleted without impacting the program, its only for testing purpose
//needed to import scanner to rewrite incorrect values
import java.util.Scanner;
class Geo{
    private String display, coord1S, coord2S, rayon, milieu, distance;
    Scanner scanner = new Scanner(System.in);
    //There is a lot of prints, most of them are mainly to verify if the code work (can be deleted)
    
    //Constructors
    //Creation of a point
    Geo(String coord1S, String coord2S){
        System.out.println("Creation d'un point");
        this.coord1S = coord1S;
        this.coord2S = coord2S;
        this.display = coord1S + "," + coord2S;
        //used in the cloning constructor to know if its a point of a disk
        this.rayon = "-1";
    }
    //Creation of a disk
    //with 3 Strings
    Geo(String coord1S, String coord2S, String rayon){
        System.out.println("Creation d'un disque a base de coord");
        this.coord1S = coord1S;
        this.coord2S = coord2S;
        //the radius of a disk must be at least 1
        while(Double.parseDouble(rayon) < 1){
            System.out.println("Valeur non conforme, Veuillez saisir une autre: ");
            rayon = scanner.nextLine();
        }
        this.rayon = rayon;
        this.display = coord1S + "," + coord2S + " ; " + rayon;
    }
    //with a defined point and a String
    Geo(Geo point, String rayon){
        System.out.println("Creation d'un disque a base d'un point");
        this.coord1S = point.coord1S;
        this.coord2S = point.coord2S;
        while(Double.parseDouble(rayon) < 1){
            System.out.println("Valeur non conforme, Veuillez saisir une autre: ");
            rayon = scanner.nextLine();
        }
        this.rayon = rayon;
        this.display = point.coord1S + "," + point.coord2S + " ; " + rayon;
    }

    //Creation of a segment
    //with 4 Strings
    Geo(String coord1S, String coord2S, String coord3S, String coord4S){
        this.coord1S = coord1S + "," + coord2S;
        this.coord2S = coord3S + "," + coord4S;
        this.display = this.coord1S + " ; " + this.coord2S;
    }
    //with 2 defined points
    Geo(Geo point1, Geo point2){
        this.coord1S = point1.coord1S + "," + point1.coord2S;
        this.coord2S = point2.coord1S + "," + point2.coord2S;
        this.display = this.coord1S + " ; " + this.coord2S;
    }
    
    //Cloning Constructor
        Geo(Geo shape){
            System.out.println("Clonage d'une forme");
            this.coord1S = shape.coord1S;
            this.coord2S = shape.coord2S;
            this.display = shape.coord1S + "," + shape.coord2S;
            if(shape.rayon == null){
                System.out.println("Segment detecter !!");
                this.display = shape.coord1S + " ; " + shape.coord2S;
            }
            //points have a value of -1 which mean its isnt a disk
            else if(shape.rayon.equals("-1")){
                System.out.println("Point detecter !!");
                //give to the new point the value -1 to know in the future that its a point
                this.rayon = "-1";
            }
            else if(Double.parseDouble(shape.rayon) >= 1){
                System.out.println("disque detecter !!");
                //the radius of a disk must be at least 1
                while(Double.parseDouble(shape.rayon) < 1){
                    System.out.println("Valeur non conforme, Veuillez saisir une autre: ");
                    rayon = scanner.nextLine();
                }
                this.rayon = shape.rayon;
                this.display = shape.coord1S + "," + shape.coord2S + " ; " + shape.rayon;   
            }
        }
    

    //Get
    double getPerimetre(){
        return Double.parseDouble(this.rayon) * 2 * Math.PI; 
    }
    double getAire(){
        return Math.pow(Double.parseDouble(this.rayon), 2) * Math.PI;
    }
    String getMilieu() {
        return this.milieu;
    }
    String getDistance() {
        return this.distance;
    }

    //Set
    void setMilieu(double coord1, double coord2){
        this.milieu = String.valueOf(coord1) + " , " + String.valueOf(coord2);
    }
    void setDistance(double distance){
        this.distance = String.valueOf(distance);
    }

    //simple function to skip a line
    void saut_ligne(){
        System.out.println("\n");
    }

    //set information about a segment
    void milieu_segment(){
        String[] list_temp = unpackingSegment();
        setMilieu((Double.parseDouble(list_temp[0]) + Double.parseDouble(list_temp[2])) / 2, (Double.parseDouble(list_temp[1]) + Double.parseDouble(list_temp[3])) / 2);
    }
    void distance_segment(){
        String[] list_temp = unpackingSegment();
        setDistance(Math.sqrt(Math.pow(Double.parseDouble(list_temp[2]) - Double.parseDouble(list_temp[0]), 2) + Math.pow(Double.parseDouble(list_temp[3]) - Double.parseDouble(list_temp[1]), 2)));
    }
    //unpack a segment into a String list
    String[] unpackingSegment(){
        //create a string list of 4 elements
        String[] list_temp = new String[4];
        //split coord1S where there is a "," and put those elements into the first and second index
        list_temp[0] = this.coord1S.split(",")[0];
        list_temp[1] = this.coord1S.split(",")[1];
        //same as above but with coord2S
        list_temp[2] = this.coord2S.split(",")[0];
        list_temp[3] = this.coord2S.split(",")[1];
        return list_temp;

    }

    boolean isInDisk(Geo point){
        //Create 2 temporary segment
        //this segment represent the distance between the center of the disk and the point in the input
        Geo segmentPointTemp = new Geo(point.coord1S, point.coord2S, this.coord1S, this.coord2S);
        //this segment represent the distance between the center of the disk to the border of the disk (setting coord1S and coord2S as strings was a BIG mistake)
        Geo segmentDisqueTemp = new Geo(this.coord1S, this.coord2S, String.valueOf(Double.parseDouble(this.coord1S) + Double.parseDouble(this.rayon)), this.coord2S);
        //set the distance into private String distance of both segments
        segmentDisqueTemp.distance_segment();
        segmentPointTemp.distance_segment();

        //see if the distance of segmentDisqueTemp is greater or equal than the distance of segmentPointTemp, and send a boolean
        return Double.parseDouble(segmentDisqueTemp.distance) >= Double.parseDouble(segmentPointTemp.getDistance()); 
    }
    //return a boolean if 2 shapes are equal (works on points, disks and segments)
    boolean isEqual(Geo shape){
        System.out.print("isEqual: ");
        //the segment must be verified first because his radius is null so it can create problems if i ask if a null value is equal to a string
        //if radius = null that mean its a segment        
        if(this.rayon == null && shape.rayon == null){
            //unpack values into a list to facilitate the return statement
            String[] list_temp_this = this.unpackingSegment();
            String[] list_temp_shape = shape.unpackingSegment(); 
            //the return is big, i will probably try to compact it later
            return 
                (list_temp_this[0].equals(list_temp_shape[0]) && list_temp_this[1].equals(list_temp_shape[1]))
                && (list_temp_this[2].equals(list_temp_shape[2]) && list_temp_this[3].equals(list_temp_shape[3]))
                || (list_temp_this[0].equals(list_temp_shape[2]) && list_temp_this[1].equals(list_temp_shape[3]))
                && (list_temp_this[2].equals(list_temp_shape[0]) && list_temp_this[3].equals(list_temp_shape[1]));
        }

        //point have a radius = -1
        else if(shape.rayon.equals("-1") && this.rayon.equals("-1")){
            //return if both points are the same
            return (this.coord1S.equals(shape.coord1S) && this.coord2S.equals(shape.coord2S)) || (this.coord1S.equals(shape.coord2S) && this.coord2S.equals(shape.coord1S));
        }
        
        //disk have a value greater than 0
        else if(Double.parseDouble(shape.rayon) > 0 && Double.parseDouble(this.rayon) > 0){
            return 
                (this.coord1S.equals(shape.coord1S) && this.coord2S.equals(shape.coord2S) && this.rayon.equals(shape.rayon)) 
                || (this.coord2S.equals(shape.coord1S) && this.coord1S.equals(shape.coord2S) && this.rayon.equals(shape.rayon));
        }
        return false;
    }

    
    //display the radius of a shape, most of the time this variable is hidden because points have a -1 value on it, the reason for that is to differentiate between a disk and a point in the cloning constructor
    void verification(){
        System.out.println(this.rayon);
    }
    
    //for testing and display of values
    void display(){
        System.out.println(this.display);
    }
    void display_disque_information(){
        System.out.println("Permimetre du cercle: " + getPerimetre() + "\nAire du cercle: " + getAire());
    }
    void display_segment_information(){
        System.out.println("Le milieu du segment est: " + getMilieu() + "\nDistance du segment: " + getDistance());
    }    

    //Give every information about a segment
    void all_segment(){
        display();
        milieu_segment();
        distance_segment();
        display_segment_information();
        saut_ligne();
    }
    //give every information about a disk
    void all_disque(){
        display();
        verification();
        display_disque_information();
        saut_ligne();
    }

    public static void main(String []args){

    }
}
