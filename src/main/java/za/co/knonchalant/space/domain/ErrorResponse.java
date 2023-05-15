package za.co.knonchalant.space.domain;

import com.google.gson.Gson;

public class ErrorResponse {
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static ErrorResponse from(String json) {
        return new Gson().fromJson(json, ErrorResponse.class);
    }
}
