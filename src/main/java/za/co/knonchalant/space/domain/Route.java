
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;

import java.util.Date;


@SuppressWarnings("unused")
public class Route {

    @Expose
    private Date arrival;
    @Expose
    private Departure departure;
    @Expose
    private Date departureTime;
    @Expose
    private Destination destination;

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Route{" +
                "arrival='" + arrival + '\'' +
                ", departure=" + departure +
                ", departureTime='" + departureTime + '\'' +
                ", destination=" + destination +
                '}';
    }
}
