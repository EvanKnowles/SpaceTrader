
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class ContractResponse {

    @Expose
    private Contract data;

    public Contract getData() {
        return data;
    }

    public void setData(Contract data) {
        this.data = data;
    }

}
