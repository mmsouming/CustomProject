package com.rkele.app.bean;

/**
 * Created by mingshanmo on 2017/3/2.
 */

public class VoucherBean {
    private String voucherId;
    private String code;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "VoucherBean{" +
                "voucherId='" + voucherId + '\'' +
                ", code='" + code + '\'' +
                ", position=" + position +
                '}';
    }
}
