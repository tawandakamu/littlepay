package com.littlepay.models.states;

import com.littlepay.enums.TripStatus;
import com.littlepay.factories.TripChargeFactory;
import com.littlepay.models.Tap;

import java.time.Duration;
import java.time.LocalDateTime;

public class IncompleteTripState implements ITripState {

    private LocalDateTime started;
    private LocalDateTime finished;
    private int durationInSecs;
    private String FromStopID;
    private String ToStopID;
    private Double chargeAmount;
    private String companyId;
    private String busID;
    private String PAN;
    private TripStatus status = TripStatus.INCOMPLETE;

    public Tap finalTap;

    public LocalDateTime getStarted() {
        return started;
    }

    public String getFromStopId() {
        return FromStopID;
    }

    @Override
    public TripStatus getStatus() {
        return this.status;
    }

    private void setDurationInSecs(LocalDateTime finished) {
        this.durationInSecs = (int)Duration.between(started, finished).toSeconds();
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    private IncompleteTripState() { }

    public IncompleteTripState(Tap tap) {
        this.started = tap.getDateTime();
        this.FromStopID = tap.getStopId();
        this.companyId = tap.getCompanyId();
        this.busID = tap.getBusId();
        this.PAN = tap.getPan();
    }

    @Override
    public ITripState process(Tap nextTap) {
        this.finished = nextTap.getDateTime();
        this.setDurationInSecs(nextTap.getDateTime());
        this.ToStopID = nextTap.getStopId();
        this.finalTap = nextTap;

        return (this.FromStopID.equalsIgnoreCase(this.ToStopID)) ?
                new CancelledTripState(this) :
                new CompletedTripState(this);
    }

    @Override
    public void charge() {
        chargeAmount = TripChargeFactory.calculateCostIncompleteTrip(FromStopID, status);
    }

    @Override
    public String toString() {
        String formatTemplate = String.format(
                "%s, N/A, N/A, %s, N/A, %s, %s, %s, %s, %s",
                started, FromStopID, chargeAmount, companyId, busID, PAN, status
        );
        return formatTemplate;
    }
}
