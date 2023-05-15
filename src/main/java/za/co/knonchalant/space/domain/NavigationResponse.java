package za.co.knonchalant.space.domain;

public class NavigationResponse {
    private Fuel fuel;
    private Nav nav;

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Nav getNav() {
        return nav;
    }

    public void setNav(Nav nav) {
        this.nav = nav;
    }
}
