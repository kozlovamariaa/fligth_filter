package com.gridnine.testing.dao;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FlightFilterBuilderTest {
    static LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    static LocalDateTime tomorrowPlusThreeHours = LocalDateTime.now().plusHours(3);

    private List<Flight> flightsAfterCurrentTime;
    private List<Flight> flightsDeparturesBeforeNow;
    private List<Flight> moreThanTwoHoursOnGroundFlightsList;
    private List<Flight> arrivalBeforeDeparture;
    private List<Flight> testFlights;


    public void initFlightsInAfterCurrentTime(){
        flightsAfterCurrentTime = new ArrayList<>();

        //1) init single segment flight in the future
        List<Segment> singleSegment = new ArrayList<>();
        singleSegment.add(new Segment(tomorrowPlusThreeHours, tomorrowPlusThreeHours.plusHours(4)));
        Flight singleSegment_flight = new Flight(singleSegment);

        //2) init 3-segment flight in future (1.30 on ground)
        List<Segment> segmentsList = new ArrayList<>();
        segmentsList.add(new Segment(tomorrow, tomorrow.plusHours(3)));
        segmentsList.add(new Segment(tomorrow.plusHours(4), tomorrow.plusHours(10)));
        segmentsList.add(new Segment(tomorrow.plusHours(10).plusMinutes(30), tomorrow.plusHours(12)));
        Flight segments_flight = new Flight(segmentsList);

        //3) init 3-segment flight in future (2.1 on ground)
        List<Segment> threeSegmentsList = new ArrayList<>();
        threeSegmentsList.add(new Segment(tomorrow, tomorrow.plusHours(3)));
        threeSegmentsList.add(new Segment(tomorrow.plusHours(4), tomorrow.plusHours(10)));
        threeSegmentsList.add(new Segment(tomorrow.plusHours(11).plusMinutes(1), tomorrow.plusHours(12)));
        Flight segments_flightWithTwoHoursOneMinuteTransfer = new Flight(segmentsList);
        // add
        flightsAfterCurrentTime.add(singleSegment_flight);
        flightsAfterCurrentTime.add(segments_flight);
        flightsAfterCurrentTime.add(segments_flightWithTwoHoursOneMinuteTransfer);
    }

    public void initNormalFlightsDeparturesBeforeNow(){
        flightsDeparturesBeforeNow = new ArrayList<>();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(2);

        //1) init segment flight in the past
        List<Segment> singleSegmentList = new ArrayList<>();
        singleSegmentList.add(new Segment(twoDaysAgo, yesterday.plusHours(4)));
        Flight singleSegment_flight = new Flight(singleSegmentList);

        //2) init segment flight in the past
        List<Segment> singleSegmentFromPastToFuture = new ArrayList<>();
        singleSegmentFromPastToFuture.add(new Segment(LocalDateTime.now().minusHours(10), LocalDateTime.now().plusHours(5)));
        Flight singleSegmentFromYesterdayToToday_flight = new Flight(singleSegmentFromPastToFuture);

        //3) init 2-segment flight in the past
        List<Segment> segmentsList = new ArrayList<>();
        segmentsList.add(new Segment(twoDaysAgo, twoDaysAgo.plusHours(7)));
        segmentsList.add(new Segment(twoDaysAgo.plusHours(8).plusMinutes(59), twoDaysAgo.plusHours(10)));
        Flight segments_flight = new Flight(segmentsList);

        // add
        flightsDeparturesBeforeNow.add(singleSegment_flight);
        flightsDeparturesBeforeNow.add(singleSegmentFromYesterdayToToday_flight);
        flightsDeparturesBeforeNow.add(segments_flight);
    }

    public void initMoreThanTwoHoursOnGroundFlights(){
        moreThanTwoHoursOnGroundFlightsList = new ArrayList<>();

        //1) init  2-segment flight in the past (2.1 on the ground)
        List<Segment> segmentsMoreThanOneHourList = new ArrayList<>();
        segmentsMoreThanOneHourList.add(new Segment(tomorrowPlusThreeHours, tomorrowPlusThreeHours.plusHours(6)));
        segmentsMoreThanOneHourList.add(new Segment(tomorrowPlusThreeHours.plusHours(8).plusMinutes(1), tomorrowPlusThreeHours.plusHours(12)));
        Flight singleSegment_flight = new Flight(segmentsMoreThanOneHourList);

        //2)init  3-segment flight in the past (2.1 hours on the ground)
        List<Segment> segmentsThreeHourList = new ArrayList<>();
        segmentsThreeHourList.add(new Segment(tomorrow, tomorrow.plusHours(5)));
        segmentsThreeHourList.add(new Segment(tomorrow.plusHours(6), tomorrow.plusHours(7)));
        segmentsThreeHourList.add(new Segment(tomorrow.plusHours(8).plusMinutes(1), tomorrow.plusHours(12)));
        Flight segments_flight = new Flight(segmentsThreeHourList);

        moreThanTwoHoursOnGroundFlightsList.add(singleSegment_flight);
        moreThanTwoHoursOnGroundFlightsList.add(segments_flight);
    }

    public void initArrivalBeforeDeparture() {
        arrivalBeforeDeparture = new ArrayList<>();

        //1)init current date flight arrival before departure
        List<Segment> singleSegmentList = new ArrayList<>();
        singleSegmentList.add(new Segment(tomorrow.minusHours(4), tomorrow.minusHours(8)));
        Flight singleSegment_Flight = new Flight(singleSegmentList);

        //2) init 2-segments flight in the future
        List<Segment> segmentsList = new ArrayList<>();
        segmentsList.add(new Segment(tomorrow, tomorrow.minusHours(5)));
        segmentsList.add(new Segment(tomorrow.minusHours(7), tomorrow.minusHours(9)));
        Flight segments_flight = new Flight(segmentsList);

        //3) init 2-segments flight in the future(arrival is earlier then departure)
        List<Segment> threeSegmentsList = new ArrayList<>();
        threeSegmentsList.add(new Segment(tomorrow, tomorrow.minusHours(5)));
        threeSegmentsList.add(new Segment(tomorrow.minusHours(4), tomorrow.minusHours(9)));
        Flight segments_flightWithLessThenTwoHoursTransfer = new Flight(segmentsList);

        arrivalBeforeDeparture.add(singleSegment_Flight);
        arrivalBeforeDeparture.add(segments_flight);
        arrivalBeforeDeparture.add(segments_flightWithLessThenTwoHoursTransfer);
    }

    @Before
    public void initTestFlights() {
        testFlights = new ArrayList<>();

        initFlightsInAfterCurrentTime();
        initNormalFlightsDeparturesBeforeNow();
        initMoreThanTwoHoursOnGroundFlights();
        initArrivalBeforeDeparture();

        testFlights.addAll(flightsAfterCurrentTime);
        testFlights.addAll(flightsDeparturesBeforeNow);
        testFlights.addAll(moreThanTwoHoursOnGroundFlightsList);
        testFlights.addAll(arrivalBeforeDeparture);
    }

    @Test
    public void addAllFiltersToFlights() {
        List<Flight> expectedFlights = flightsAfterCurrentTime;
        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(testFlights);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterDepartureBeforeNow()
                .filterArrivalBeforeDeparture()
                .filterSumTimeOnGroundMoreThanTwoHours();

        assertEquals(expectedFlights, filteredFlights);
    }

    @Test
    public void verifyFilteredDepartureBeforeNow() {
        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(flightsDeparturesBeforeNow);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterDepartureBeforeNow()
                .build();
        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void verifyFilteredDepartureBeforeNow_allFlights() {
        List<Flight> expectedFlights = new ArrayList<>(testFlights);
        expectedFlights.removeAll(flightsDeparturesBeforeNow);

        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(testFlights);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterDepartureBeforeNow()
                .build();

        assertEquals(expectedFlights, filteredFlights);
    }

    @Test
    public void verifyFilteredArrivalBeforeDeparture() {
        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(arrivalBeforeDeparture);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterArrivalBeforeDeparture()
                .build();
        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void verifyFilteredArrivalBeforeDeparture_allFlights() {
        List<Flight> expectedFlights = new ArrayList<>(testFlights);
        expectedFlights.removeAll(arrivalBeforeDeparture);
        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(testFlights);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterArrivalBeforeDeparture()
                .build();

        assertEquals(expectedFlights, filteredFlights);
    }

    @Test
    public void verifyFilteredTimeOnGroundMoreThanTwoHours() {
        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(moreThanTwoHoursOnGroundFlightsList);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterSumTimeOnGroundMoreThanTwoHours();

        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void verifyFilteredTimeOnGroundMoreThanTwoHours_allFlights() {
        List<Flight> expectedFlights = new ArrayList<>(testFlights);
        expectedFlights.removeAll(moreThanTwoHoursOnGroundFlightsList);

        FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilderImp(testFlights);

        List<Flight> filteredFlights = flightFilterBuilder
                .filterSumTimeOnGroundMoreThanTwoHours();

        assertEquals(expectedFlights, filteredFlights);
    }

}
