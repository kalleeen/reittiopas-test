/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kalleeen.reittiopas.test;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author kalle
 */
public class ReittiopasTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReittiopasAPI api = new ReittiopasAPI();
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        
        System.out.println("Anna lähtopiste:");
        String startStr = scanner.nextLine();
        
        Location start = api.getLocation(startStr);
        if (start == null){
            System.out.println("Hakusanalla ei löytynyt lähtöpistettä!");
            System.exit(1);
        }
        double[] coordinatesStart = start.getCoordinates();
        System.out.println("Lon: "+coordinatesStart[0]+", Lat: "+coordinatesStart[1]+", sijainti: "+start.getDescription());
        
        System.out.println("Anna kohteesi:");
        String finishStr = scanner.nextLine();
        
        Location finish = api.getLocation(finishStr);
        if (finish == null){
            System.out.println("Hakusanalla ei löytynyt kohdetta!");
            System.exit(2);
        }
        double[] coordinatesFinish = finish.getCoordinates();
        System.out.println("Lon: "+coordinatesFinish[0]+", Lat: "+coordinatesFinish[1]+", sijainti: "+finish.getDescription());
        
        System.out.println();
        
        int i = 1;
        for(Leg leg : api.getRoute(start, finish)){
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            System.out.println(i+". ["+format.format(leg.getStartTime())+"] "+leg.getStartLocation().getDescription()+" ("+leg.getStartLocation().getCode()+") "+
                    "--> "+leg.getMode()+" (Line: "+leg.getLine()+") -->"+
                    " ["+format.format(leg.getFinishTime())+"] "+leg.getFinishLocation().getDescription()+" ("+leg.getFinishLocation().getCode()+")");
            i++;
        }
    }
    
}
