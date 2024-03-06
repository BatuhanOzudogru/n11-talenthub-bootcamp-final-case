package com.batuhanozudogru.userservice.service;

public class CoordinateDistanceCalculatorService {
    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {

        double theta = longitude1 - longitude2;

        double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(

                Math.sin(latitude1 * (Math.PI/180)) * Math.sin(latitude2 * (Math.PI/180)) +

                        Math.cos(latitude1 * (Math.PI/180)) * Math.cos(latitude2 * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
        );

        return Math.round(distance * 1.609344*100.0)/100.0;

    }
}
