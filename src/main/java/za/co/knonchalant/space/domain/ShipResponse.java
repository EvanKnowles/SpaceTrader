
package za.co.knonchalant.space.domain;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class ShipResponse {

    @Expose
    private List<Ship> data;
    @Expose
    private Meta meta;

    public List<Ship> getData() {
        return data;
    }

    public void setData(List<Ship> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
