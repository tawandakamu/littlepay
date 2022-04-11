package com.littlepay;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.littlepay.factories.TapFactory;
import com.littlepay.models.Tap;
import com.littlepay.models.states.TripMachine;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("data/taps.csv");

        ArrayList<Tap> allTaps;

        try(Stream<String> lines = Files.lines(path);) {

            // Get all taps
            allTaps = lines.skip(1)
                    .map(TapFactory::create)
                    .collect(Collectors
                            .toCollection(ArrayList::new));

            // Map trips && charge user
            Map<String, List<Tap>> completedTaps = allTaps.stream()
                    //.sorted(tap -> tap.getDateTime())
                    .collect(
                            Collectors.groupingBy(Tap::getPan, Collectors.toList())
                    );

            var tripMachine = new TripMachine();
            completedTaps.forEach((k,v) -> tripMachine.processTripStates(k,v));

            // Save output to file
            tripMachine.saveToFile();

        } catch (NoSuchFileException ex) {

            String message = String.format(
                    "File not found '%s'. Please make sure to create it first.",
                    ex.getMessage()
            );
            System.out.println(message);

        } catch (IOException ioe) {

            System.out.println(ioe.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
