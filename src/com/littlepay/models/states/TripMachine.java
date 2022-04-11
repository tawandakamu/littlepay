package com.littlepay.models.states;

import com.littlepay.models.Tap;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class TripMachine {
    Map<String, ITripState> tripsMap = new HashMap<String, ITripState>();
    static final String fileName = "data/trips.csv";

    public void processTripStates(String Pan, List<Tap> trips) {
        // retrieve and store first value
        Tap firstTap = trips.stream().findFirst().orElseThrow();
        ITripState firstTapTrip = new IncompleteTripState(firstTap);
        this.tripsMap.putIfAbsent(Pan, firstTapTrip);

        // Get the rest to update & change state
        Optional<Tap> nextTaps = trips.stream().skip(1).findAny();
        if (nextTaps.isPresent()) {
            tripsMap.replace(Pan, firstTapTrip, firstTapTrip.process(nextTaps.orElseThrow()));
        }
    }

    public void saveToFile() {
        // save trips to trips.csv file, while calculating cost for each trip.
        try(PrintWriter writer = new PrintWriter(new File(fileName))) {
            String heading = String.format(
                    "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    "Started", "Finished", "DurationSecs", "FromStopId", "ToStopId",
                    "ChargeAmount", "CompanyId", "BusId", "PAN", "Status"
                    );
            writer.println(heading);

            this.tripsMap.forEach((key, value) -> {
                value.charge();
                writer.println(value.toString());
            });

            System.out.println(
                    String.format("Trips successfully saved to the location %s!", fileName)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
        }

    }
}
