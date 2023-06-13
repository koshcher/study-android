package rk.retrofiter.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("ccy")
    @Expose
    private String ccy;
    @SerializedName("base_ccy")
    @Expose
    private String baseCcy;
    @SerializedName("buy")
    @Expose
    private String buy;
    @SerializedName("sale")
    @Expose
    private String sale;

    public String getCcy() {
        return ccy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }
}