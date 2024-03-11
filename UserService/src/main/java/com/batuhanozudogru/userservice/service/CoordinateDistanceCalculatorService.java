package com.batuhanozudogru.userservice.service;

import java.math.BigDecimal;

public class CoordinateDistanceCalculatorService {
    public static double calculateDistance(BigDecimal latitude1, BigDecimal longitude1, String latitude2, String longitude2) {

        double userLatitude = latitude1.doubleValue();

        double userLongitude = longitude1.doubleValue();

        double restaurantLatitude = Double.parseDouble(latitude2);

        double restaurantLongitude = Double.parseDouble(longitude2);



        double theta = userLongitude - restaurantLongitude;

        double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(

                Math.sin(userLatitude * (Math.PI/180)) * Math.sin(restaurantLatitude * (Math.PI/180)) +

                        Math.cos(userLatitude * (Math.PI/180)) * Math.cos(restaurantLatitude * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
        );

        return Math.round(distance * 1.609344*100.0)/100.0;

    }
}
