
package za.co.knonchalant.space.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ContractsResponse {

    @SerializedName("data")
    private List<Contract> mData;
    @SerializedName("meta")
    private Meta mMeta;

    public List<Contract> getData() {
        return mData;
    }

    public void setData(List<Contract> data) {
        mData = data;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

}
