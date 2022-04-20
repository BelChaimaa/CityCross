package com.example.myfirstapplication;

public class Calculs {

    public static double calculerDistanceEuclidienne(double[] point1, double[] point2){
        double x1 = point1[0];
        double y1 = point1[1];
        double x2 = point2[0];
        double y2 = point2[1];

        return Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2),2));
    }

    public static double getAngle(double[] coordsVille, double[] coordsUtilisateur){
        double[] coordsNord = {48.336847799006, 7.1807626240416};
        double angle = Math.atan2(coordsNord[1] - coordsUtilisateur[1], coordsNord[0] - coordsUtilisateur[0]) - Math.atan2(coordsVille[1] - coordsUtilisateur[1], coordsVille[0] - coordsUtilisateur[0]);

        return Math.toDegrees(angle);
    }

}
