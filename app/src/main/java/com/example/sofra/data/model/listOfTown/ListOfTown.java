
package com.example.sofra.data.model.listOfTown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfTown {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ListOfTownPagination data;

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

    public ListOfTownPagination getData() {
        return data;
    }

    public void setData(ListOfTownPagination data) {
        this.data = data;
    }

}
