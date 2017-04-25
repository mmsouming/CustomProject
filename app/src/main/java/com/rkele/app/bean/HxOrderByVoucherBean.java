package com.rkele.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${mms} on 2017/3/3.
 */

public class HxOrderByVoucherBean implements Serializable{

    /**
     * orderCode : 201702261100459641
     * orderTime : 2017-02-26 11:00:45
     * totalMoney : 0.01
     * phone : 13048064322
     * product : [{"proName":"金龙鱼爽滑挂面","number":"1"},{"proName":"金龙鱼丝苗米","number":"2"}]
     */

    private String orderCode;
    private String orderTime;
    private String totalMoney;
    private String phone;
    /**
     * proName : 金龙鱼爽滑挂面
     * number : 1
     */

    private List<ProductBean> product;

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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean implements Serializable{
        private String proName;
        private String number;

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
