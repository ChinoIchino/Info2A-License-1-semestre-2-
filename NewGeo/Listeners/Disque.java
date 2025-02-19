package NewGeo.Listeners;

import java.util.Scanner;

public class Disque {
    Scanner scanner = new Scanner(System.in);
    private double coord1, coord2, rayon;

    //Constructor
    //With 3 double
    public Disque(double coord1, double coord2, double rayon){
        boolean i = false;
        this.coord1 = coord1;
        this.coord2 = coord2;

        while(rayon <= 0){
            //exit out the program if there is a second mistake in the radius scanner
            if(i == true)
                System.exit(1);
            System.out.println("Valeur pour le rayon est invalid");
            rayon = scanner.nextDouble();
            i = true;
        }
        this.rayon = rayon;
        scanner.close();
    }
    //With a Point and a double
    public Disque(Point point, double rayon){
        boolean i = false;
        this.coord1 = point.getCoord1();
        this.coord2 = point.getCoord2();

        while(rayon <= 0){
            if(i == true)
                System.exit(1);
            System.out.println("Valeur pour le rayon est invalid");
            rayon = scanner.nextDouble();
            i = true;
        }
        this.rayon = rayon;
        scanner.close();
    }
    //By cloning
    public Disque(Disque disque){
        this.coord1 = disque.coord1;
        this.coord2 = disque.coord2;
        this.rayon = disque.rayon;
    }

    //get
    public double getCoord1(){
        return this.coord1;
    }
    public double getCoord2(){
        return this.coord2;
    }
    public double getRayon(){
        return this.rayon;
    }

    //for display purpose
    public String toString(){
        return "C'est un disque avec comme centre " + this.coord1 + " , " + this.coord2 + " et de rayon " + this.rayon;
    }

}
