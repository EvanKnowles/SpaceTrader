
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Extraction {

    @Expose
    private String shipSymbol;
    @Expose
    private Yield yield;

    public String getShipSymbol() {
        return shipSymbol;
    }

    public void setShipSymbol(String shipSymbol) {
        this.shipSymbol = shipSymbol;
    }

    public Yield getYield() {
        return yield;
    }

    public void setYield(Yield yield) {
        this.yield = yield;
    }

}
