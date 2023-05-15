
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class ShipType {

    @Expose
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ShipType{" +
                "type='" + type + '\'' +
                '}';
    }
}
