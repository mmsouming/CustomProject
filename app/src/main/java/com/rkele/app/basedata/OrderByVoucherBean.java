package com.rkele.app.basedata;

import java.io.Serializable;

/**
 * Created by ${mms} on 2017/3/2.
 */

public class OrderByVoucherBean implements Serializable{

    /**
     * orderCode : 201702261100459641
     * orderTime : 2017-02-26 11:00:45
     * vorcherCode : 248326108938
     * hxMoney : 10.0
     * totalMoney : 0.01
     * vorcherStatus : 10
     * voucherMoney : 10
     */

    private String orderCode;
    private String orderTime;
    private String vorcherCode;
    private String hxMoney;
    private String totalMoney;
    private String vorcherStatus;
    private String voucherMoney;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getVorcherCode() {
        return vorcherCode;
    }

    public void setVorcherCode(String vorcherCode) {
        this.vorcherCode = vorcherCode;
    }

    public String getHxMoney() {
        return hxMoney;
    }

    public void setHxMoney(String hxMoney) {
        this.hxMoney = hxMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getVorcherStatus() {
        return vorcherStatus;
    }

    public void setVorcherStatus(String vorcherStatus) {
        this.vorcherStatus = vorcherStatus;
    }

    public String getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(String voucherMoney) {
        this.voucherMoney = voucherMoney;
    }
}
