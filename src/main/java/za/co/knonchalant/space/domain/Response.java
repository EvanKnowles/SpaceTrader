
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Response<T> {

    @Expose
    private RegisterResponse data;

    public RegisterResponse getData() {
        return data;
    }

    public void setData(RegisterResponse data) {
        this.data = data;
    }

}
