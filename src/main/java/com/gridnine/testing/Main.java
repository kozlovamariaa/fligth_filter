package com.gridnine.testing;


import com.gridnine.testing.dao.FlightFilterBuilder;
import com.gridnine.testing.dao.FlightFilterBuilderImp;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.services.FlightBuilder;

import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        System.out.println("Unfiltered flights: " + flightList);
        System.out.println("_______________________________________________");

        List<Flight> flightsDepartureBeforeNow = new FlightFilterBuilderImp(flightList)
                .filterDepartureBeforeNow()
                .build();
        System.out.println("Filtered flights without departure before now: " + flightsDepartureBeforeNow);

        List<Flight> flightsArrivalBeforeDeparture = new FlightFilterBuilderImp(flightList)
                .filterArrivalBeforeDeparture()
                .build();
        System.out.println("Filtered flights without arrival before departure: " + flightsArrivalBeforeDeparture);

        List<Flight> flightsTimeOnGroundMoreThanTwoHours = new FlightFilterBuilderImp(flightList)
                .filterSumTimeOnGroundMoreThanTwoHours();
        System.out.println("Filtered flights without time on the ground is more than two hours: " + flightsTimeOnGroundMoreThanTwoHours);


    }
}