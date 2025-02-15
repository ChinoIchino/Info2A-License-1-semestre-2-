class Geo{
    private String display, coord1S, coord2S, rayon, milieu, distance;
    //There is a lot of prints, most of them are mainly to verify if the code work
    
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
    //Cloning of a point or a disk
    Geo(Geo shape){
        System.out.println("clonage de point/disque");
        this.coord1S = shape.coord1S;
        this.coord2S = shape.coord2S;
        this.display = shape.coord1S + "," + shape.coord2S;
        //points have a value of -1 which mean its isnt a disk
        if(shape.rayon.equals("-1")){
            System.out.println("Point detecter !!");
            //give to the new point the value -1 to know in the future that its a point
            this.rayon = "-1";
        }
        else{
            System.out.println("disque detecter !!");
            this.rayon = shape.rayon;
            this.display = shape.coord1S + "," + shape.coord2S + " ; " + shape.rayon;   
        }
    }

    //Creation of a disk
    //with 3 Strings
    Geo(String coord1S, String coord2S, String rayon){
        System.out.println("Creation d'un disque a base de coord");
        this.coord1S = coord1S;
        this.coord2S = coord2S;
        this.rayon = rayon;
        this.display = coord1S + "," + coord2S + " ; " + rayon;
    }
    //with a defined point and a String
    Geo(Geo point, String rayon){
        System.out.println("Creation d'un disque a base d'un point");
        this.coord1S = point.coord1S;
        this.coord2S = point.coord2S;
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

    //Get
    String getCoord1S(){
        return this.coord1S;
    }
    String getCoord2S(){
        return this.coord2S;
    }
    String getRayon(){
        return this.rayon;
    }
    String getDisplay(){
        return this.display;
    }
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
    void setCoord1S(String coord1S){
        this.coord1S = coord1S;
    }
    void setCoord2S(String coord2S){
        this.coord2S = coord2S;
    }
    void setRayon(String rayon){
        this.rayon = rayon;
    }
    void setDisplay(String display){
        this.display = display;
    }
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
        String[] list_temp =  this.coord1S.split(",");
        String[] list_temp2 = this.coord2S.split(",");
        setMilieu((Double.parseDouble(list_temp[0]) + Double.parseDouble(list_temp2[0])) / 2, (Double.parseDouble(list_temp[1]) + Double.parseDouble(list_temp2[1])) / 2);
    }
    void distance_segment(){
        String[] list_temp =  getCoord1S().split(",");
        String[] list_temp2 = getCoord2S().split(",");
        setDistance(Math.sqrt(Math.pow(Double.parseDouble(list_temp2[0]) - Double.parseDouble(list_temp[0]), 2) + Math.pow(Double.parseDouble(list_temp2[1]) - Double.parseDouble(list_temp[1]), 2)));
        //return getDistance();
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
    
    //display the radius of a shape, most of the time this variable is hidden because points have a -1 value on it, the reason for that is to differentiate between a disk and a point in the cloning constructor
    void verification(){
        System.out.println(this.rayon);
    }
    //gonna keep that function in case ill need to decompile the steps of a function
    void debugger(Geo segment){
        String[] list_temp =  getCoord1S().split(",");
        //setCoord1S(list_temp[1]);
        //System.out.println(getCoord1S());
        getDistance();
        //double distance = Double.valueOf(list_temp[1]);
        //setDistance(distance);
        //System.out.println(Double.valueOf(list_temp[1]));
        setMilieu(Double.valueOf(list_temp[0]), Double.valueOf(list_temp[1]));
        System.out.println(getMilieu());
    }




    public static void main(String []args){


        Geo p1 = new Geo("1","2");
        Geo p2 = new Geo("4.5", "3.8"); //P2=(4.5,3.8)
        Geo p3 = new Geo("3.5", "5.5");

        System.out.println("Segment1");
        Geo segment1 = new Geo(p1, p2);
        segment1.all_segment();

        Geo disk = new Geo("2", "2", "3");
        System.out.println(disk.isInDisk(p1));
        System.out.println(disk.isInDisk(p2));
        System.out.println(disk.isInDisk(p3));

        

        /*
        //all are for testing purpose
        System.out.println("Point1");
        Geo point1 = new Geo("2.3", "5.1");
        point1.display();
        point1.verification();
        point1.saut_ligne();

        System.out.println("Clonage Point2 a base de point1");
        Geo point2 = new Geo(point1);
        point2.display();
        point2.verification();
        point1.saut_ligne();

        System.out.println("Disque 1");
        Geo disque1 = new Geo("4.5", "9.5", "3");
        disque1.display();
        disque1.verification();
        point1.saut_ligne();

        System.out.println("Disque 2 a base de point1 et rayon");
        Geo disque2 = new Geo(point1, "7");
        disque2.display();
        disque2.verification();
        point1.saut_ligne();

        System.out.println("Clonage Disque 3 a base de Disque 2");
        Geo disque3 = new Geo(disque2);
        disque3.display();
        disque3.verification();
        point1.saut_ligne();

        System.out.println("Segment1");
        Geo segment1 = new Geo(point1, point2);
        //marche pas :(
        //segment1.all_segment(); 
        segment1.display();
        segment1.display_segment_information();

        System.out.println("Cercle info");
        disque1.all_disque();

        System.out.println("Test de TP");
        Geo disque_test1 = new Geo("3.5","2.5", "0.5");
        disque_test1.all_disque();
        */
    }
}