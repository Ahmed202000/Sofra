
package com.example.sofra.data.model.userListOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListOffer {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private UserListOfferPagination data;

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

    public UserListOfferPagination getData() {
        return data;
    }

    public void setData(UserListOfferPagination data) {
        this.data = data;
    }

}
