package com.littlepay.models.states;

import com.littlepay.enums.TripStatus;
import com.littlepay.models.Tap;

public interface ITripState {
    public TripStatus getStatus();

    public ITripState process(Tap nextTap);

    public void charge();

    public String toString();
}
