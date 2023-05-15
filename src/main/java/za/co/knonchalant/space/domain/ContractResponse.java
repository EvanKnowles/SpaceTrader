
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class ContractResponse {

    @Expose
    private List<Contract> data;

    public List<Contract> getData() {
        return data;
    }

    public void setData(List<Contract> data) {
        this.data = data;
    }

}
