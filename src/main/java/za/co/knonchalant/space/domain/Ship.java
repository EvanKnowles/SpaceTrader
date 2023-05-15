
package za.co.knonchalant.space.domain;

import java.util.List;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Ship {

    @Expose
    private Cargo cargo;
    @Expose
    private Crew crew;
    @Expose
    private Engine engine;
    @Expose
    private Frame frame;
    @Expose
    private Fuel fuel;
    @Expose
    private List<Module> modules;
    @Expose
    private List<Mount> mounts;
    @Expose
    private Nav nav;
    @Expose
    private Reactor reactor;
    @Expose
    private Registration registration;
    @Expose
    private String symbol;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Mount> getMounts() {
        return mounts;
    }

    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }

    public Nav getNav() {
        return nav;
    }

    public void setNav(Nav nav) {
        this.nav = nav;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "cargo=" + cargo +
                ", crew=" + crew +
                ", engine=" + engine +
                ", frame=" + frame +
                ", fuel=" + fuel +
                ", modules=" + modules +
                ", mounts=" + mounts +
                ", nav=" + nav +
                ", reactor=" + reactor +
                ", registration=" + registration +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
