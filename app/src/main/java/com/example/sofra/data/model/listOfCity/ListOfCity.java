
package com.example.sofra.data.model.listOfCity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfCity {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ListOfCityPagination data;

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

    public ListOfCityPagination getData() {
        return data;
    }

    public void setData(ListOfCityPagination data) {
        this.data = data;
    }

}
