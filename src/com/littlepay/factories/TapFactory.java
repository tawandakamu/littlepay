package com.littlepay.factories;

import com.littlepay.models.Tap;

public class TapFactory {

    public static Tap create (String line) {

        String[] values = line.split(", ");
        Tap tap = new Tap(
                values[0],
                values[1],
                values[2],
                values[3],
                values[4],
                values[5],
                values[6]
        );

        return tap;
    }


}
