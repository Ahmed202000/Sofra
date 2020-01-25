
package com.example.sofra.data.model.listRestaurantItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRestaurantItem {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ListRestaurantItemPagination data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ListRestaurantItemPagination getData() {
        return data;
    }

    public void setData(ListRestaurantItemPagination data) {
        this.data = data;
    }

}
