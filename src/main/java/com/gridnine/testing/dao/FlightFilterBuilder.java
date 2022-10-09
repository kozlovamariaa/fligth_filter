package com.gridnine.testing.dao;

import com.gridnine.testing.models.Flight;

import java.util.List;

public interface FlightFilterBuilder {
    List<Flight> build();

    FlightFilterBuilder filterDepartureBeforeNow();

    FlightFilterBuilder filterArrivalBeforeDeparture();

    List<Flight> filterSumTimeOnGroundMoreThanTwoHours();
}
