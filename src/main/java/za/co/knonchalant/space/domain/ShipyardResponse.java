
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class ShipyardResponse {

    @Expose
    private Shipyard data;

    public Shipyard getData() {
        return data;
    }

    public void setData(Shipyard data) {
        this.data = data;
    }

}
