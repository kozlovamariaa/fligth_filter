package com.gridnine.testing.dao;

import com.gridnine.testing.models.Flight;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterBuilderImp implements FlightFilterBuilder{
    private List<Flight> flights;

    public FlightFilterBuilderImp(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> build() {
        return flights;
    }

    @Override
    public FlightFilterBuilder filterDepartureBeforeNow() {
        flights.removeIf(flight ->
                flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
        );
        return this;
    }

    @Override
    public FlightFilterBuilder filterArrivalBeforeDeparture() {
        flights.removeIf(flight ->
                flight.getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
        );
        return this;
    }

    @Override
    public List<Flight> filterSumTimeOnGroundMoreThanTwoHours() {
        List<Flight> result = new ArrayList<>();
        flights.forEach(flight -> {
            int size = flight.getSegments().size();
            if(size > 1){
                int minutes = 0;
                for (int i = 1; i < size; i++){
                    minutes += countTransferMinutes(flight.getSegments().get(i - 1).getArrivalDate(),
                            flight.getSegments().get(i).getDepartureDate());
                }
                if(minutes <= 120){
                    result.add(flight);
                }
            }
            else {
                result.add(flight);
            }
        });
        return result;
    }

    public int countTransferMinutes(LocalDateTime arrival, LocalDateTime departure){
        return (int) ChronoUnit.MINUTES.between(arrival, departure);
    }
}
