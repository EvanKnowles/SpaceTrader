package za.co.knonchalant.space.domain;

import com.google.gson.annotations.SerializedName;

public class DataResponse<T> {
    private T data;
    @SerializedName("code")
    private Long code;
    @SerializedName("message")
    private String message;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
