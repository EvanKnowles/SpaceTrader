
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Route {

    @Expose
    private String arrival;
    @Expose
    private Departure departure;
    @Expose
    private String departureTime;
    @Expose
    private Destination destination;

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
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
