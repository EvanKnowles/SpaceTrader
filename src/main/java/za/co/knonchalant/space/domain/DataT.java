
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class DataT {

    @Expose
    private Agent data;

    public Agent getData() {
        return data;
    }

    public void setData(Agent data) {
        this.data = data;
    }

}
