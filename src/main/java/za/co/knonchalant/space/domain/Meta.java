
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Meta {

    @SerializedName("limit")
    private Long mLimit;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("total")
    private Long mTotal;

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long limit) {
        mLimit = limit;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
