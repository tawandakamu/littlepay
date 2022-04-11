package com.littlepay.models.states;

import com.littlepay.enums.TripStatus;
import com.littlepay.factories.TripChargeFactory;
import com.littlepay.models.Tap;

import java.time.Duration;
import java.time.LocalDateTime;

public class CancelledTripState implements ITripState {

    private LocalDateTime started;
    private LocalDateTime finished;
    private int durationInSecs;
    private String FromStopID;
    private String ToStopID;
    private Double chargeAmount;
    private String companyId;
    private String busID;
    private String PAN;
    private TripStatus status = TripStatus.CANCELLED;

    @Override
    public TripStatus getStatus() {
        return this.status;
    }

    private void setDurationInSecs() {
        this.durationInSecs = (int)Duration.between(started, finished).toSeconds();
    }

    private CancelledTripState() { }

    public CancelledTripState(IncompleteTripState previousState) {
        this.started = previousState.getStarted();
        this.FromStopID = previousState.getFromStopId();
        this.companyId = previousState.finalTap.getCompanyId();
        this.busID = previousState.finalTap.getBusId();
        this.PAN = previousState.finalTap.getPan();
        this.finished = previousState.getFinished();
        this.setDurationInSecs();
        this.ToStopID = previousState.finalTap.getStopId();
    }

    @Override
    public ITripState process(Tap nextTap) {
        return this;
    }

    @Override
    public void charge() {
        chargeAmount = TripChargeFactory.calculateCostCancelledTrip(status);
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
