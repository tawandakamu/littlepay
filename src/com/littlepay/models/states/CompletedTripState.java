package com.littlepay.models.states;

import com.littlepay.enums.TripStatus;
import com.littlepay.factories.TripChargeFactory;
import com.littlepay.models.Tap;

import java.time.Duration;
import java.time.LocalDateTime;

public class CompletedTripState implements ITripState {

    private LocalDateTime started;
    private LocalDateTime finished;
    private int durationInSecs;
    private String FromStopID;
    private String ToStopID;
    private Double chargeAmount;
    private String companyId;
    private String busID;
    private String PAN;
    private TripStatus status = TripStatus.COMPLETED;

    @Override
    public TripStatus getStatus() {
        return this.status;
    }

    private void setStatus(String status) {
        this.status = TripStatus.valueOf(status);
    }

    private String getFromStopId() {
        return FromStopID;
    }

    private void setDurationInSecs(LocalDateTime finished) {
        this.durationInSecs = (int) Duration.between(started, finished).toSeconds();
    }

    private CompletedTripState() {}

    public CompletedTripState(IncompleteTripState previousState) {
        this.started = previousState.getStarted();
        this.FromStopID = previousState.getFromStopId();
        this.companyId = previousState.finalTap.getCompanyId();
        this.busID = previousState.finalTap.getBusId();
        this.PAN = previousState.finalTap.getPan();
        this.finished = previousState.finalTap.getDateTime();
        this.setDurationInSecs(previousState.finalTap.getDateTime());
        this.ToStopID = previousState.finalTap.getStopId();
    }

    @Override
    public ITripState process(Tap nextTap) {
        // do nothing - final state
        return this;
    }

    @Override
    public void charge() {
        chargeAmount = TripChargeFactory.calculateCostCompleteTrip(FromStopID, ToStopID, status);
    }

    @Override
    public String toString() {
        String formatTemplate = String.format(
                "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                this.started, finished, durationInSecs, FromStopID, ToStopID,
                chargeAmount, companyId, busID, PAN, status
        );
        return formatTemplate;
    }

}
