package com.littlepay.factories;

import com.littlepay.enums.TripStatus;

public class TripChargeFactory {
    static final String STOP_1 = "Stop1";
    static final String STOP_2 = "Stop2";
    static final String STOP_3 = "Stop3";

    public static Double calculateCostIncompleteTrip(String fromStopID, TripStatus tripStatus) {
        double STOP_1_MAX = 7.30d;
        double STOP_2_MAX = 5.50d;
        double STOP_3_MAX = 5.50d;

        if(tripStatus == TripStatus.INCOMPLETE){
            switch(fromStopID) {
                case STOP_1: return STOP_1_MAX;
                case STOP_2: return STOP_2_MAX;
                case STOP_3: return STOP_3_MAX;
            }
        }
        return 0.0d;
    }

    public static Double calculateCostCancelledTrip(TripStatus tripStatus) {
        return 0.0d;
    }

     public static Double calculateCostCompleteTrip(String fromStopID, String toStopId, TripStatus tripStatus) {
         if(
                 fromStopID.equalsIgnoreCase(STOP_1)  &&
                 toStopId.equalsIgnoreCase(STOP_2) &&
                 tripStatus == TripStatus.COMPLETED ||
                 fromStopID.equalsIgnoreCase(STOP_2)  &&
                 toStopId.equalsIgnoreCase(STOP_1) &&
                 tripStatus == TripStatus.COMPLETED
         ) {
             return 3.2d;
         }
         if(
                 fromStopID.equalsIgnoreCase(STOP_2)  &&
                 toStopId.equalsIgnoreCase(STOP_3) &&
                 tripStatus == TripStatus.COMPLETED ||
                 fromStopID.equalsIgnoreCase(STOP_3)  &&
                 toStopId.equalsIgnoreCase(STOP_2) &&
                 tripStatus == TripStatus.COMPLETED
         ) {
            return 5.50d;
         }
         if(
                 fromStopID.equalsIgnoreCase(STOP_3)  &&
                 toStopId.equalsIgnoreCase(STOP_1) &&
                 tripStatus == TripStatus.COMPLETED ||
                 fromStopID.equalsIgnoreCase(STOP_1)  &&
                 toStopId.equalsIgnoreCase(STOP_3) &&
                 tripStatus == TripStatus.COMPLETED
         ) {
            return 7.30d;
         }

         return 0.0d;
     }



}
