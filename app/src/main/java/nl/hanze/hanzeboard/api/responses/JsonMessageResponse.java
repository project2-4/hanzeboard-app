package nl.hanze.hanzeboard.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class JsonMessageResponse<T> {

    @SerializedName("code")
    private String code;

    public abstract List<T> getObjectList();

    public String getCode() {
        return code;
    }
}
